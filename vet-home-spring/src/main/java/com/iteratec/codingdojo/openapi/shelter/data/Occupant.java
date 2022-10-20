package com.iteratec.codingdojo.openapi.shelter.data;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "occupants")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Occupant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true, updatable = false, columnDefinition = "uuid")
    private UUID referenceId;

    @Column
    private Integer animalId;

    @Column
    private String animal;

    @Temporal(TemporalType.DATE)
    @Column
    private Date birthDay;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, nullable = false)
    private Calendar shelterDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar adoptionDate;
}
