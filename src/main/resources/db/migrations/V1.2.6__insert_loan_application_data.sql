INSERT INTO loan_application(amount, term_months, interest_rate_per_year, loan_reason, status, is_taken_by_customer, employee_id, customer_id)
VALUES  (1000, 12, 15, 'Some reason Some reason Some reason Some reason', 'PENDING', 0, null, 1),
        (2000, 24, 12.3, 'Some another reason reason reason', 'APPROVED', 0, 3, 1),
        (3000, 36, 11.77, 'Such is the reason!', 'REJECTED', 0, 3, 1),
        (4000, 48, 11, 'Yet another reason', 'PENDING', 0, null, 4);
