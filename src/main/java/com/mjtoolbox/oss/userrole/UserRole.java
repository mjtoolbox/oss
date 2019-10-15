package com.mjtoolbox.oss.userrole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.role.Role;
import com.mjtoolbox.oss.user.UserOss;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "userrole", schema = "public")
public class UserRole implements Serializable {

    @Id
    @Column(name = "userrole_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userrole_id;

    // @JsonIgnore will not fetch Program object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private UserOss user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private long user_id;

    // @JsonIgnore will not fetch Program object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Role role;

    @Column(name = "role_id", insertable = false, updatable = false)
    private long role_id;
}
