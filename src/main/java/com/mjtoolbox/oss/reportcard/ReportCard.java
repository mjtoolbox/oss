package com.mjtoolbox.oss.reportcard;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "report_card", schema = "public")
public class ReportCard implements Serializable {

    @Id
    @Column(name = "report_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long report_card_id;


//    private long lesson_id;

//    private long membership_id;

//    private long teacher_id;


    @Column(name = "score")
    private int score;

//    private Json interim_report;

    @Column(name = "submitted_date")
    private Date submitted_date;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
