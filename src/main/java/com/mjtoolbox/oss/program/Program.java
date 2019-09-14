package com.mjtoolbox.oss.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.lesson.Lesson;
import com.mjtoolbox.oss.term.Term;
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
@Table(name = "program", schema = "public")
public class Program implements Serializable {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long program_id;


    // @JsonIgnore will not fetch Teacher object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Term term;

    @Column(name = "term_id", insertable = false, updatable = false)
    private long term_id;

    @Column(name = "program_type")
    private String program_type;

    @Column(name = "cost")
    private float cost;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Lesson> lessons = new HashSet<>();
}
