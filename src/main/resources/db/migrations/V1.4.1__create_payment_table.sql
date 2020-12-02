CREATE TABLE payment (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    payment_type VARCHAR(255) NOT NULL,
    amount DECIMAL(7,2) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    customer_id bigint NOT NULL,
    loan_id bigint NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (loan_id) REFERENCES loan (id)
);
