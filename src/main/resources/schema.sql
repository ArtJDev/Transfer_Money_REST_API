create table cardholder
(
    number    VARCHAR(16) NOT NULL PRIMARY KEY,
    validTill VARCHAR(4)  NOT NULL,
    cvv       VARCHAR(4)  NOT NULL,
    amount    INT         NOT NULL,
    currency  VARCHAR(10) NOT NULL
);