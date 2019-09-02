package com.mjtoolbox.oss.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.payroll.Payroll;
import com.mjtoolbox.oss.timesheet.Timesheet;
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
@Table(name = "teacher", schema = "public")
public class Teacher implements Serializable {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teacher_id;

    @Column(name = "teacher_name")
    private String teacher_name;

    @Column(name = "status")
    private String status;

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

    @Column(name = "subjects")
    private String subjects;

    @Column(name = "level")
    private int level;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Payroll> payrolls = new HashSet<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Timesheet> timesheets = new HashSet<>();


}
