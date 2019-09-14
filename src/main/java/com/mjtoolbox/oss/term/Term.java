package com.mjtoolbox.oss.term;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.invoice.Invoice;
import com.mjtoolbox.oss.student.Student;
import com.mjtoolbox.oss.termprogram.TermProgram;
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
@Table(name = "term", schema = "public")
public class Term implements Serializable {

    @Id
    @Column(name = "term_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long term_id;

    @OneToOne(mappedBy = "term", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Invoice invoice;

    // @JsonIgnore will not fetch Student object in response
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "membership_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Student student;

    @Column(name = "membership_id", insertable = false, updatable = false)
    private long membership_id;


    @Column(name = "total_cost")
    private double total_cost;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "term", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TermProgram> termprograms = new HashSet<>();

}
