-- School of Information Technology
WITH inserted_school AS (
    INSERT INTO schools (name)
    VALUES ('School of Information Technology')
    RETURNING school_id
)
INSERT INTO programs (school_id, name)
SELECT school_id, program_name
FROM inserted_school,
LATERAL (VALUES
    ('Computer Science'),
    ('Entertainment and Multimedia Computing'),
    ('Information Systems'),
    ('Information Technology'),
    ('Data Science')
) AS p(program_name);

-- School of Multimedia and Digital Arts
WITH inserted_school AS (
    INSERT INTO schools (name)
    VALUES ('School of Multimedia and Digital Arts')
    RETURNING school_id
)
INSERT INTO programs (school_id, name)
SELECT school_id, program_name
FROM inserted_school,
LATERAL (VALUES
    ('Broadcast Media'),
    ('Digital Film'),
    ('Multimedia Arts')
) AS p(program_name);

-- E.T. Yuchengco School of Business
WITH inserted_school AS (
    INSERT INTO schools (name)
    VALUES ('E.T. Yuchengco School of Business')
    RETURNING school_id
)
INSERT INTO programs (school_id, name)
SELECT school_id, program_name
FROM inserted_school,
LATERAL (VALUES
    ('Bachelor of Science in Accountancy'),
    ('Bachelor of Science in Business Administration'),
    ('Bachelor of Science in Business Analytics with AI'),
    ('Bachelor of Science in Entrepreneurship'),
    ('Bachelor of Science in Financial Management and Technology'),
    ('Bachelor of Science in International Business'),
    ('Bachelor of Science in Marketing'),
    ('Bachelor of Science in Real Estate Management'),
    ('Bachelor of Science in Accountancy with Masters in Business Analytics (ACMAN)'),
    ('Bachelor of Science In Business Administration with Masters in Business Analytics (BAMAN)'),
    ('Master of Business Administration')
) AS p(program_name);

-- School of Health Sciences
WITH inserted_school AS (
    INSERT INTO schools (name)
    VALUES ('School of Health Sciences')
    RETURNING school_id
)
INSERT INTO programs (school_id, name)
SELECT school_id, program_name
FROM inserted_school,
LATERAL (VALUES
    ('Bachelor of Science in Biology'),
    ('Bachelor of Science in Medical Technology'),
    ('Bachelor of Science in Pharmacy'),
    ('Bachelor of Science in Physical Therapy'),
    ('Bachelor of Science in Psychology'),
    ('Bachelor of Arts in Psychology'),
    ('Bachelor of Science in Radiologic Technology'),
    ('Bachelor of Science in Psychology - Master of Arts in Psychology (BMPSY)'),
    ('Bachelor of Arts in Psychology - Master of Arts in Psychology (AMPSY)'),
    ('Master of Arts in Psychology (MPSY)')
) AS p(program_name);

-- School of Nursing
WITH inserted_school AS (
    INSERT INTO schools (name)
    VALUES ('School of Nursing')
    RETURNING school_id
)
INSERT INTO programs (school_id, name)
SELECT school_id, program_name
FROM inserted_school,
LATERAL (VALUES
    ('Bachelor of Science in Nursing')
) AS p(program_name);




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