package com.example.mapleapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Exp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double characterExp;
    private LocalDate date;
    private Long characterId;

    public Exp() {
    }
}
