package com.mjtoolbox.oss.reportcard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.lesson.Lesson;
import com.mjtoolbox.oss.student.Student;
import com.mjtoolbox.oss.teacher.Teacher;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "report_card", schema = "public")
public class ReportCard implements Serializable {

    @Id
    @Column(name = "report_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long report_card_id;

    // @JsonIgnore will not fetch Student object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Lesson lesson;

    @Column(name = "lesson_id", insertable = false, updatable = false)
    private long lesson_id;


    // @JsonIgnore will not fetch Student object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "membership_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Student student;

    @Column(name = "membership_id", insertable = false, updatable = false)
    private long membership_id;


    // @JsonIgnore will not fetch Teacher object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Teacher teacher;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private long teacher_id;

    @Column(name = "score")
    private int score;

//    private Json interim_report;

    @Column(name = "submitted_date")
    private Date submitted_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
