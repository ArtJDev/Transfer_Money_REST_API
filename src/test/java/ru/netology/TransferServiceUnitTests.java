package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.dto.Amount;
import ru.netology.exceptions.CardInvalidCvvException;
import ru.netology.exceptions.CardInvalidDateException;
import ru.netology.exceptions.CardNumberNotFoundException;
import ru.netology.exceptions.NotEnoughMoneyException;
import ru.netology.model.Card;
import ru.netology.repositories.CardHolderRepository;
import ru.netology.services.TransferService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {
    @Mock
    private CardHolderRepository cardHolderRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    public void transferServiceTransferMoneyTest() {
        Card sender = new Card();
        sender.setNumber(4000);
        sender.setValidtill("1111");
        sender.setCvv("333");
        sender.setAmount(1000);
        sender.setCurrency("RUB");

        Card receiver = new Card();
        receiver.setNumber(5000);
        receiver.setAmount(1000);

        Amount amount = new Amount(100, "RUB");

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

        given(cardHolderRepository.findCardByNumber(receiver.getNumber()))
                .willReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getNumber(), "1111", "333", receiver.getNumber(), amount);

        verify(cardHolderRepository).changeAmount(sender.getNumber(), 900);
        verify(cardHolderRepository).changeAmount(receiver.getNumber(), 1100);
    }

    @Test
    public void senderCardNumberNotFoundExceptionTest() {
        Amount amount = new Amount(100, "RUB");
        Exception exception = assertThrowsExactly(CardNumberNotFoundException.class, () ->
                transferService.transferMoney(1000, "1111", "333", 2000, amount)
        );
        String expectedMessage = "1000";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void receiverCardNumberNotFoundExceptionTest() {
        Card sender = new Card();
        sender.setNumber(1000);

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

        Amount amount = new Amount(100, "RUB");

        Exception exception = assertThrowsExactly(CardNumberNotFoundException.class, () ->
                transferService.transferMoney(1000, "1111", "333", 2000, amount)
        );
        String expectedMessage = "2000";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cardInvalidDateExceptionTest() {
        Card sender = new Card();
        sender.setNumber(1000);

        Card receiver = new Card();
        receiver.setNumber(2000);

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

        given(cardHolderRepository.findCardByNumber(receiver.getNumber()))
                .willReturn(Optional.of(receiver));

        Amount amount = new Amount(100, "RUB");

        Exception exception = assertThrowsExactly(CardInvalidDateException.class, () ->
                transferService.transferMoney(1000, "1111", "333", 2000, amount)
        );
        String expectedMessage = "дата";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void cardInvalidCvvExceptionTest() {
        Card sender = new Card();
        sender.setNumber(1000);
        sender.setValidtill("1111");
        sender.setCvv("222");

        Card receiver = new Card();
        receiver.setNumber(2000);

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

        given(cardHolderRepository.findCardByNumber(receiver.getNumber()))
                .willReturn(Optional.of(receiver));

        Amount amount = new Amount(100, "RUB");

        Exception exception = assertThrowsExactly(CardInvalidCvvException.class, () ->
                transferService.transferMoney(1000, "1111", "333", 2000, amount)
        );
        String expectedMessage = "cvv";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void notEnoughMoneyExceptionTest() {
        Card sender = new Card();
        sender.setNumber(1000);
        sender.setValidtill("1111");
        sender.setCvv("333");
        sender.setAmount(10);

        Card receiver = new Card();
        receiver.setNumber(2000);

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

        given(cardHolderRepository.findCardByNumber(receiver.getNumber()))
                .willReturn(Optional.of(receiver));

        Amount amount = new Amount(100, "RUB");

        Exception exception = assertThrowsExactly(NotEnoughMoneyException.class, () ->
                transferService.transferMoney(1000, "1111", "333", 2000, amount)
        );
        String expectedMessage = "Недостаточно средств";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}


