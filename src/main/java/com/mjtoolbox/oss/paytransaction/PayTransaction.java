package com.mjtoolbox.oss.paytransaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjtoolbox.oss.invoice.Invoice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "pay_transaction", schema = "public")
public class PayTransaction {

    @Id
    @Column(name = "transactoin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactoin_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Invoice invoice;

    @Column(name = "invoice_number", insertable = false, updatable = false)
    private long invoice_number;

    @Column(name = "method")
    private String method;

    @Column(name = "result")
    private boolean result;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;
}
