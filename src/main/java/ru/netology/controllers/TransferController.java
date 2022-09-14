package ru.netology.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dto.Amount;
import ru.netology.dto.TransferRequest;
import ru.netology.repositories.CardHolderRepository;
import ru.netology.services.TransferService;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService, CardHolderRepository cardHolderRepository) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
        request.getCardFromNumber(),
        request.getCardFromValidTill(),
        request.getCardFromCVV(),
        request.getCardToNumber(),
                new Amount(request.getAmount().getValue(), request.getAmount().getCurrency())
        );
        System.out.println(request);
        System.out.println(transferService.getCardHolderRepository(request.getCardFromNumber()));
        System.out.println(transferService.getCardHolderRepository(request.getCardToNumber()));
    }
}