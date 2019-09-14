package com.mjtoolbox.oss.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.program.Program;
import com.mjtoolbox.oss.reportcard.ReportCard;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "lesson", schema = "public")
public class Lesson implements Serializable {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lesson_id;

    // @JsonIgnore will not fetch Teacher object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Teacher teacher;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private long teacher_id;


    // @JsonIgnore will not fetch Program object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Program program;

    @Column(name = "program_id", insertable = false, updatable = false)
    private long program_id;


    @Column(name = "name")
    private String name;

    @Column(name = "season")
    private String season;

    @Column(name = "lesson_date")
    private Date lesson_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ReportCard> reportCards = new HashSet<>();

}
