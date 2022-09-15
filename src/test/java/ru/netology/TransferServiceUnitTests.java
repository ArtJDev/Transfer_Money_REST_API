package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.netology.dto.Amount;
import ru.netology.exceptions.CardNumberNotFoundException;
import ru.netology.model.Card;
import ru.netology.repositories.CardHolderRepository;
import ru.netology.services.TransferService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    public void cardNumberNotFoundExceptionTest() {
        Card sender = new Card();
        sender.setNumber(1000);
        sender.setValidtill("0123");
        sender.setCvv("111");
        sender.setAmount(1000);
        sender.setCurrency("RUB");

        Amount amount = new Amount(100, "RUB");

        given(cardHolderRepository.findCardByNumber(sender.getNumber()))
                .willReturn(Optional.of(sender));

//        given(cardHolderRepository.findCardByNumber(2000L))
//                .willReturn(Optional.empty());

        transferService.transferMoney(1000, "1111", "333", 2000, amount);


//        verify(cardHolderRepository, never()).changeAmount(anyLong(), any());
    }
}


