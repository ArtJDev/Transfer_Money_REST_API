package ru.netology.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.Amount;
import ru.netology.dto.ConfirmResponse;
import ru.netology.dto.TransferRequest;
import ru.netology.dto.TransferResponse;
import ru.netology.services.TransferService;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    @ResponseBody
    public TransferResponse transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getCardFromNumber(),
                request.getCardFromValidTill(),
                request.getCardFromCVV(),
                request.getCardToNumber(),
                new Amount(request.getAmount().getValue(), request.getAmount().getCurrency())
        );
        TransferResponse confirmOperation = transferService.transferResponse();
        return confirmOperation;
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<ConfirmResponse> confirmOperation(@RequestBody ConfirmResponse confirmRequest) {
        confirmRequest.getOperationId();
        confirmRequest.getCode();
        return ResponseEntity.ok().body(confirmRequest);
    }
}