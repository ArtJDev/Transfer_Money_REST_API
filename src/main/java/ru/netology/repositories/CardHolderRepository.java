package ru.netology.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.netology.model.Card;

import java.math.BigInteger;

public interface CardHolderRepository extends CrudRepository<Card, String> {
    @Modifying
    @Query("UPDATE card SET amount = :amount WHERE number = :number")
    void changeAmount(String number, int amount);

}