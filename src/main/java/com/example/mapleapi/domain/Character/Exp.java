package com.example.mapleapi.domain.Character;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Exp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double characterExp;
    private LocalDate date;
    private long characterId;

    public Exp() {
    }
}
