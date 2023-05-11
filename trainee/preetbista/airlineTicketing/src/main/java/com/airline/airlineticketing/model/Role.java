package com.airline.airlineticketing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /*@ManyToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = {CascadeType.ALL})
    private List<User> users;*/
}
