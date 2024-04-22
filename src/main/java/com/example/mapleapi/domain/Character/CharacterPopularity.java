package com.example.mapleapi.domain.Character;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *   "date": "2023-12-21T00:00+09:00",
 *   "popularity": 0
 */

@Getter @Setter
public class CharacterPopularity {

    private LocalDateTime date;
    private int popularity;

    public void setDate(Timestamp dateTime) {
        date = dateTime.toLocalDateTime();
    }

    public String getDate() {
        return date.toString();
    }
}
