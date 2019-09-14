package com.mjtoolbox.oss.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.paytransaction.PayTransaction;
import com.mjtoolbox.oss.term.Term;
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
@Table(name = "invoice", schema = "public")
public class Invoice implements Serializable {

    @Id
    @Column(name = "invoice_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice_number;

    @JoinColumn(name = "term_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Term term;

    @Column(name = "invoice_date")
    private Date invoice_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PayTransaction> payTransactions = new HashSet<>();

}
