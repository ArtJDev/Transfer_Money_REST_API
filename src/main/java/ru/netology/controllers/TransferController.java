package ru.netology.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dto.TransferRequest;

@RestController
public class TransferController {
    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        request.getCardFromNumber();
        request.getCardFromValidTill();
        request.getCardFromCVV();
        request.getCardToNumber();
        request.getAmount().getValue();
        request.getAmount().getCurrency();
        System.out.println(request);
    }

}
