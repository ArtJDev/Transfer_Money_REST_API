create table card
(
    number    VARCHAR(16) NOT NULL PRIMARY KEY,
    validtill VARCHAR(4)  NOT NULL,
    cvv       VARCHAR(4)  NOT NULL,
    amount    INT         NOT NULL,
    currency  VARCHAR(10) NOT NULL
);