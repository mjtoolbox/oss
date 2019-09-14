package com.mjtoolbox.oss.program;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "program", schema = "public")
public class Program implements Serializable {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long program_id;

//    private Term term;

//    private long term_id;

    @Column(name = "program_type")
    private String program_type;

    @Column(name = "cost")
    private float cost;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;
}
