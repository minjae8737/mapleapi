package com.example.mapleapi.domain.Character;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class UserCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ocid;
    private String characterName;

    public UserCharacter() {
    }
}
