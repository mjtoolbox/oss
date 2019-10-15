package com.mjtoolbox.oss.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185122326007488L;

    // We need a signing key, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.in.seconds}")
    private Long expiration;

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(now);
    }

    /**
     * Generate Token for the user. Email is ID.
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("userName", userDetails.getUsername());
        claims.put("role", "admin");

        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }


    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userEmailFromToken = getUserEmailFromToken(token);
        return (userEmailFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
