package com.mjtoolbox.oss.payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.teacher.Teacher;
import com.mjtoolbox.oss.timesheet.Timesheet;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "payroll", schema = "public")
public class Payroll implements Serializable {

    @Id
    @Column(name = "payroll_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long payroll_id;

    // @JsonIgnore will not fetch Teacher object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Teacher teacher;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private long teacher_id;

    @Column(name = "hourly_rate")
    private long hourly_rate;

    @Column(name = "payment")
    private long payment;

    @Column(name = "payment_date")
    private Date payment_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "payroll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Timesheet> timesheets = new HashSet<>();

}
