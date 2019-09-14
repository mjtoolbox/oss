package com.mjtoolbox.oss.term;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "teacher", schema = "public")
public class Term implements Serializable {

    @Id
    @Column(name = "term_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long term_id;

    @Column(name = "total_cost")
    private double total_cost;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
