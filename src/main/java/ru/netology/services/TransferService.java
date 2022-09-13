package ru.netology.services;
package com.example.services;

import com.example.exceptions.AccountNotFoundException;
import com.example.model.Account;
import com.example.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.exceptions.CardNumberNotFoundException;
import ru.netology.model.Card;
import ru.netology.repositories.CardHolderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final CardHolderRepository cardHolderRepository;

    public TransferService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }


    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Card sender = cardHolderRepository.findById(idSender)
                .orElseThrow();

        Card receiver = cardHolderRepository.findById(idReceiver)
                .orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        cardHolderRepository.changeAmount(idSender, senderNewAmount);
        cardHolderRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
