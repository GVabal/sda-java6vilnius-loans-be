CREATE TABLE loan
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    amount_to_repay DECIMAL(7,2) NOT NULL,
    amount_payed DECIMAL(7,2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    customer_id bigint NOT NULL,
    loan_application_id bigint NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (loan_application_id) REFERENCES loan_application (id)
);
