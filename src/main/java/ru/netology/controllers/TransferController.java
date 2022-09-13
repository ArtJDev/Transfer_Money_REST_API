package ru.netology.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dto.Amount;
import ru.netology.dto.TransferRequest;
import ru.netology.services.TransferService;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
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
    }
}