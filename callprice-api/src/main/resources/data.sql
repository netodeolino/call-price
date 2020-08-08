INSERT INTO tax_rate
    (value)
VALUES
    (1.1);

INSERT INTO plan
    (duration, name, tax_rate_id)
VALUES
    (30, 'CallPrice 30', 1),
    (60, 'CallPrice 60', 1),
    (120, 'CallPrice 120', 1);

INSERT INTO price
    (ddd_origin, ddd_destiny, minute_price)
VALUES
    (11, 16, 1.90),
    (16, 11, 2.90),
    (11, 17, 1.70),
    (17, 11, 2.70),
    (11, 18, 0.90),
    (18, 11, 1.90);