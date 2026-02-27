WITH inserted_school AS (
    INSERT INTO schools (name, description)
    VALUES ('School of Information Technology', 'Focuses on computing, software development, and digital technologies')
    RETURNING school_id
)
INSERT INTO programs (school_id, name, description)
SELECT school_id, 'Computer Science', 'Bachelor program covering software development, algorithms, and computing theory'
FROM inserted_school;

BEGIN;

WITH new_user AS (
    INSERT INTO users (
        email,
        firstname,
        lastname,
        middlename,
        password,
        role
    )
    VALUES (
        'gabriel.loslos@email.com',
        'gabriel',
        'loslos',
        'm',
        '$2a$12$5gFv6eMws7g0oY.75.0E/.7boADkPAaAdbeDCf1w7NDBIcwhR8WNa',
        'ROLE_STUDENT'
    )
    RETURNING user_id
)
INSERT INTO students (
    user_id,
    student_number,
    school_id,
    program_id
)
SELECT
    user_id,
    '2023123456',
    1,
    1
FROM new_user;

COMMIT;