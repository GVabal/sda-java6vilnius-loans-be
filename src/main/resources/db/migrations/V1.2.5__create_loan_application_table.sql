CREATE TABLE loan_application
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    amount integer NOT NULL,
    status VARCHAR(255) NOT NULL,
    is_taken_by_customer BOOLEAN NOT NULL,
    employee_id bigint,
    customer_id bigint NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
