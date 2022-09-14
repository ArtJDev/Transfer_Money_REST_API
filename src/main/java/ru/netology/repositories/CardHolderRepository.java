package ru.netology.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.netology.model.Card;

import java.util.List;

public interface CardHolderRepository extends CrudRepository<Card, Long> {
    @Query("SELECT * FROM card WHERE number = :number")
    Card findCardByNumber(long number);

    @Modifying
    @Query("UPDATE card SET amount = :amount WHERE number = :number")
    void changeAmount(long number, int amount);

}