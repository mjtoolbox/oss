package com.mjtoolbox.oss.student;

import com.mjtoolbox.oss.studentcontact.StudentContact;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "student", schema = "public")
public class Student implements Serializable {

    @Id
    @Column(name = "membership_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long membership_id;

    @OneToOne(mappedBy = "student")
    private StudentContact studentContact;

    @Column(name = "preferred_name")
    private String preferred_name;

    @Column(name = "legal_name")
    private String legal_name;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "grade")
    private int grade;

    @Column(name = "date_of_registration")
    private Date date_of_registration;

    @Column(name = "school")
    private String school;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
