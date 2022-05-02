-- Fill table departments
insert into departments (id, title)
values (1, 'Java'),
       (2, 'Java2'),
       (3, 'Java3');

-- Fill table projects
insert into projects (id, title, start_date, end_date)
values (1, 'IBX Project', '2021-09-10', '2022-03-25'),
       (2, 'IB Project', '2022-03-10', '2022-03-28'),
       (3, 'ProjectName', '2022-03-20', '2022-03-28'),
       (4, 'ProjectName2', '2022-03-20', '2022-04-28'),
       (5, 'ProjectName3', '2022-03-20', '2022-03-22'),
       (6, 'ProjectName4', '2022-03-29', '2022-04-22'),
       (7, 'ProjectName5', '2022-03-29', '2022-04-22');

-- Fill table users
insert into users (id, first_name, last_name, email, password, job_title, role, status, department)
values (1, 'Kateryna', 'Danko', 'admin@mail.com',
        '$2a$12$uXEVVqGpKGzQgMAo2B2okudDGlIflzebtvAP4dfbjNNtWg83TAxpS', 'lava-dev', 'ROLE_ADMIN',
        'ACTIVE', 1),
       (2, 'Kira', 'Black', 'kira', '$2a$10$6pVAWQ5GfdK9qVgy0ueHGeVtX1zfXNYOFmvn91SMwLuiDFkyapVtC',
        'junior', 'ROLE_USER', 'ACTIVE', 1),
       (3, 'Kira', 'Black', 'kira2', '$2a$10$lS2clWVAGSrreWid6DD0AeTnJf72.QKoJGEHXk5wllf45HhtPmtGG',
        'junior', 'ROLE_USER', 'ACTIVE', 1),
       (4, 'Tom', 'Doc', 'tom01', '$2a$10$LKw5ALsOkoycR3IgF3IjPOW3YPDjBkK4tyBcXhB73V/8IEUjYM9yW',
        'junior', 'ROLE_USER', 'ACTIVE', 1);

-- Fill table project_positions
insert into project_positions (id, position_title, position_start_date, position_end_date,
                               occupation, user_id, project_id)
values (1, 'Senior Java-dev', '2022-01-15', '2022-05-23', '100%', 1, 1),
       (2, 'Middle Java-dev', '2022-03-15', '2022-03-23', '50%', 2, 2),
       (4, 'Junior', '2022-02-17', '2022-02-25', '100%', 2, 1),
       (5, 'Junior', '2022-02-17', '2022-02-25', '100%', 2, 1),
       (6, 'Junior', '2022-04-17', '2022-05-20', '100%', 2, 1),
       (7, 'Junior', '2022-02-17', '2022-03-20', '100%', 2, 1),
       (8, 'Junior', '2022-04-17', '2022-05-20', '50%', 3, 4),
       (9, 'Middle', '2022-01-17', '2022-03-25', '75%', 4, 5),
       (11, 'Middle', '2022-01-17', '2022-04-05', '75%', 3, 1),
       (12, 'Middle', '2022-01-17', '2022-03-24', '75%', 1, 1),
       (13, 'Middle', '2022-03-26', '2022-05-10', '50%', 1, 2);



