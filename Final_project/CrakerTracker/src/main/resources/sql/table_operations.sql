INSERT into users (userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating)
VALUES ('ADMINISTRATOR', 'Anna', 'Talstaya', 'an.t@yandex.ru', 'anna.t',
        '$2a$12$/R.FtVP69Vz7YFhnVi.ZhuKRe86940jgf1a0.VoZ6T964DfDpJeoG', '1998-11-30', 45, 160, null);

SELECT requested_supervisor_id FROM users WHERE id=1;