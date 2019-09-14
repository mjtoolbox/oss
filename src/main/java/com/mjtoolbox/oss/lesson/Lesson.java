package com.mjtoolbox.oss.lesson;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "lesson", schema = "public")
public class Lesson implements Serializable {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lesson_id;

//    private long teacher_id;

//    private long program_id;


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

}
