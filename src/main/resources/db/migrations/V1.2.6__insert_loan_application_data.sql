INSERT INTO loan_application(amount, term_months, interest_rate_per_year, monthly_income, monthly_liabilities, loan_reason, status, datetime_applied, employee_id, customer_id)
VALUES  (1000, 12, 15, 800, 65, 'Some reason', 'PENDING', '2020-11-15 19:21:45', null, 1),
        (2000, 24, 12.3, 1500, 0, 'Some another reason', 'APPROVED', '2020-11-18 04:33:45', 3, 1),
        (3000, 36, 11.77, 650, 115, 'Such is the reason!', 'REJECTED', '2020-11-28 17:06:11', 3, 1),
        (5000, 12, 20, 980, 30, 'Decent reason', 'TAKEN', '2020-11-30 19:50:59', 3, 1);
