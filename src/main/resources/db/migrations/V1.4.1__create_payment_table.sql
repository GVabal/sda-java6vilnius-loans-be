CREATE TABLE payment (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    payment_type VARCHAR(255) NOT NULL,
    amount float NOT NULL,
    customer_id bigint NOT NULL,
    loan_id bigint NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (loan_id) REFERENCES loan (id)
);
