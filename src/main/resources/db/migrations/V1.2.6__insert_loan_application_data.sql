INSERT INTO loan_application(amount, term_months, interest_rate_per_year, loan_reason, status, employee_id, customer_id)
VALUES  (1000, 12, 15, 'Some reason', 'PENDING', null, 1),
        (2000, 24, 12.3, 'Some another reason', 'APPROVED', 3, 1),
        (3000, 36, 11.77, 'Such is the reason!', 'REJECTED', 3, 1),
        (4000, 48, 11, 'Yet another reason', 'PENDING', null, 4),
        (5000, 12, 20, 'Decent reason', 'TAKEN', 3, 1);
