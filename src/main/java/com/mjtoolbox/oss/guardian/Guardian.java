package com.mjtoolbox.oss.guardian;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "guardian", schema = "public")
public class Guardian implements Serializable {

    @Id
    @Column(name = "guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guardian_id;

    @Column(name = "guardian_name")
    private String guardian_name;

    @Column(name = "relationship")
    private String relationship;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
