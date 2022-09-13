package ru.netology.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.dto.Amount;
import ru.netology.exceptions.CardInvalidCvvException;
import ru.netology.exceptions.CardInvalidDateException;
import ru.netology.exceptions.CardNumberNotFoundException;
import ru.netology.exceptions.NotEnoughMoneyException;
import ru.netology.model.Card;
import ru.netology.repositories.CardHolderRepository;

@Service
public class TransferService {

    private final CardHolderRepository cardHolderRepository;

    public TransferService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }

    @Transactional
    public void transferMoney(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        Card sender = cardHolderRepository.findById(cardFromNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Номер карты отправителя не найден"));

        Card receiver = cardHolderRepository.findById(cardToNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Номер карты получателя не найден"));

        if (!cardFromValidTill.equals(sender.getValidTill())){
            throw new CardInvalidDateException();
        }
        if (!cardFromCVV.equals(sender.getCvv())) {
            throw new CardInvalidCvvException();
        }
        if (amount.getValue() > sender.getAmount()) {
            throw new NotEnoughMoneyException();
        }

        int senderNewAmount = sender.getAmount() - amount.getValue();
        int receiverNewAmount = receiver.getAmount() + amount.getValue();

        cardHolderRepository.changeAmount(cardFromNumber, senderNewAmount);
        cardHolderRepository.changeAmount(cardToNumber, receiverNewAmount);
    }
}