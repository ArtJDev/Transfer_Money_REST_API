create table card
(
    id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    number    VARCHAR(16) NOT NULL,
    validTill VARCHAR(4)  NOT NULL,
    cvv       VARCHAR(4)  NOT NULL,
    amount    INT         NOT NULL,
    currency  VARCHAR(10) NOT NULL
);