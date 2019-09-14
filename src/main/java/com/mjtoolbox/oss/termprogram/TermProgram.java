package com.mjtoolbox.oss.termprogram;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.program.Program;
import com.mjtoolbox.oss.term.Term;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "termprogram", schema = "public")
public class TermProgram implements Serializable {

    @Id
    @Column(name = "termprogram_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long termprogram_id;

    // @JsonIgnore will not fetch Program object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Term term;

    @Column(name = "term_id", insertable = false, updatable = false)
    private long term_id;

    // @JsonIgnore will not fetch Program object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Program program;

    @Column(name = "program_id", insertable = false, updatable = false)
    private long program_id;
}
