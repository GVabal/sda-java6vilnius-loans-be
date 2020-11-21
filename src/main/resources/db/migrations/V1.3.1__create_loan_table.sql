CREATE TABLE loan
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    amount_granted integer NOT NULL,
    amount_to_repay integer NOT NULL,
    amount_payed integer NOT NULL,
    customer_id bigint NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
