CREATE TABLE loan_application
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    amount integer NOT NULL,
    term_months integer NOT NULL,
    interest_rate_per_year float NOT NULL,
    loan_reason VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    employee_id bigint,
    customer_id bigint NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
