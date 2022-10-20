package com.iteratec.codingdojo.openapi.shelter.data;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "boxes")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Box {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Integer id;

    private String label;

    private boolean strong;

    @ManyToOne
    private Shelter shelter;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private Occupant occupant;

}
