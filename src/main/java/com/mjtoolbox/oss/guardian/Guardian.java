package com.mjtoolbox.oss.guardian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.guardiancontact.GuardianContact;
import com.mjtoolbox.oss.student.Student;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "guardian", schema = "public")
public class Guardian implements Serializable {

    @Id
    @Column(name = "guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guardian_id;

    @OneToOne(mappedBy = "guardian", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private GuardianContact guardianContact;

    @Column(name = "guardian_name")
    private String guardian_name;

    @Column(name = "relationship")
    private String relationship;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

}
