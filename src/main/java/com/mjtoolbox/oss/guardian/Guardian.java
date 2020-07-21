package com.mjtoolbox.oss.guardian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.student.Student;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "guardian", schema = "public")
public class Guardian implements Serializable {

    @Id
    @Column(name = "guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guardian_id;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "relationship")
    private String relationship;

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

    @OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Guardian(RegistrationInfo registrationInfo) {
        this.guardianName = registrationInfo.getGuardian_name();
        this.relationship = registrationInfo.getRelationship();
        this.cell_phone = registrationInfo.getCell_phone();
        this.email = registrationInfo.getEmail();
        this.home_phone = registrationInfo.getHome_phone();
        this.address = registrationInfo.getAddress();
        this.city = registrationInfo.getCity();
        this.province = registrationInfo.getProvince();
        this.postal_code = registrationInfo.getPostal_code();
    }

}
