package com.mjtoolbox.oss.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.lesson.Lesson;
import com.mjtoolbox.oss.teacher.Teacher;
import com.mjtoolbox.oss.termprogram.TermProgram;
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
@Table(name = "program", schema = "public")
public class Program implements Serializable {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long program_id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "cost")
    private float cost;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TermProgram> termprograms = new HashSet<>();
}
