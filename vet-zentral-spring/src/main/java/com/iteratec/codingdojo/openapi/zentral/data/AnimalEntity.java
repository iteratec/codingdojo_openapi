package com.iteratec.codingdojo.openapi.zentral.data;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "animal")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer maxAge;

    @Column
    private boolean aggressive;

    @Column
    private boolean venomous;

    @Column
    private boolean indigenous;

    @Column
    private boolean mystical;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, nullable = false)
    private Calendar creation;


}
