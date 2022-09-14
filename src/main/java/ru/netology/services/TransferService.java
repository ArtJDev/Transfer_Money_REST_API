package ru.netology.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(TransferService.class);

    private final CardHolderRepository cardHolderRepository;

    public TransferService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }

    @Transactional
    public void transferMoney(long cardFromNumber, String cardFromValidTill, String cardFromCVV, long cardToNumber, Amount amount) {
        Card sender = cardHolderRepository.findById(cardFromNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Номер карты отправителя не найден"));

        Card receiver = cardHolderRepository.findById(cardToNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Номер карты получателя не найден"));

        if (!cardFromValidTill.equals(sender.getValidtill())){
            throw new CardInvalidDateException("Неверная дата действия карты");
        }
        if (!cardFromCVV.equals(sender.getCvv())) {
            throw new CardInvalidCvvException("Неверный код cvv карты");
        }
        if (amount.getValue() > sender.getAmount()) {
            throw new NotEnoughMoneyException("Недостаточно средств для перевода на счете карты");
        }

        int senderNewAmount = sender.getAmount() - amount.getValue();
        int receiverNewAmount = receiver.getAmount() + amount.getValue();

        cardHolderRepository.changeAmount(cardFromNumber, senderNewAmount);
        cardHolderRepository.changeAmount(cardToNumber, receiverNewAmount);
        logger.info("Транзакция завершена");
    }

    public Card getCardHolderRepository(long number) {
        return cardHolderRepository.findCardByNumber(number);
    }
}