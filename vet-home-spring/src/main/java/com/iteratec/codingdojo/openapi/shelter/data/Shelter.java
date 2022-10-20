package com.iteratec.codingdojo.openapi.shelter.data;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "shelter")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Shelter {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Integer id;

    @Column
    private String plz;

    @Column
    private String contact;

    @OneToMany
    private List<Box> boxes;

}
