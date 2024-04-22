package com.example.mapleapi.repository;

import com.example.mapleapi.domain.Character.UserCharacter;
import com.example.mapleapi.domain.Character.Exp;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ApiRepository {

    private final EntityManager em;

    public UserCharacter characterSave(UserCharacter userCharacter) {
        em.persist(userCharacter);
        return userCharacter;
    }

    public Exp expSave(Exp exp) {
        em.persist(exp);
        return exp;
    }

    public Optional<UserCharacter> findByCharacterName(UserCharacter userCharacter) {
        String jpql = "select c from UserCharacter c where c.characterName = :characterName";

        List<UserCharacter> userCharacterList = em.createQuery(jpql, UserCharacter.class)
                .setParameter("characterName", userCharacter.getCharacterName())
                .getResultList();

        if (userCharacterList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userCharacterList.get(0));
        }
    }

    public List<Exp> findExpsByCharacterName(UserCharacter userCharacter) {
        String jpql = "select e from Exp e where e.characterId = :characterId order by date desc limit 7";

        List<Exp> expList = em.createQuery(jpql, Exp.class)
                .setParameter("characterId", userCharacter.getId())
                .getResultList();

        if (expList.isEmpty()) {
            return null;
        } else {
            return expList;
        }
    }

    public Optional<Exp> findExpByCharacterNameAtDate(UserCharacter userCharacter, LocalDate date) {
        String jpql = "select e from Exp e where e.characterId = :characterId and e.date = :date";

        List<Exp> expList = em.createQuery(jpql, Exp.class)
                .setParameter("characterId", userCharacter.getId())
                .setParameter("date", date)
                .getResultList();

        if (expList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(expList.get(0));
        }
    }

    public void updateExp(Exp exp) {
        Exp findExp = em.find(Exp.class, exp.getId());
        findExp.setCharacterExp(exp.getCharacterExp());
    }


}
