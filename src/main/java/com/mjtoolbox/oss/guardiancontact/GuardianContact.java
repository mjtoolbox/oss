package com.mjtoolbox.oss.guardiancontact;

import com.mjtoolbox.oss.guardian.Guardian;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "guardian_contact", schema = "public")
public class GuardianContact implements Serializable {

    @Id
    @Column(name = "guardian_contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guardian_contact_id;

    @JoinColumn(name = "guardian_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Guardian guardian;

    @Column(name = "cell_phone")
    private String cell_phone;

    @Column(name = "email")
    private String email;

    @Column(name = "home_phone")
    private String home_phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "postal_code")
    private String postal_code;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;
}
