-- GUARDIAN ----
INSERT INTO public.guardian
(guardian_name, relationship, cell_phone, email, home_phone, address, city, province, postal_code)
  VALUES
  ('Chucky Cheese', 'father', '604-555-1000', 'chucky.cheese@email.com', '604-333-1000', '777 Lucky st.', 'Vancouver', 'BC', 'V3E 3A3'),
  ('Iron Man', 'father', '604-555-1001', 'iron.man@email.com', '604-333-1001', '1000 Lindle way', 'Coquitlam', 'BC', 'V3K 3M3');


-- STUDENT --
INSERT INTO public.student
(guardian_id, preferred_name, legal_name, date_of_birth, gender, membership_type, grade, date_of_registration, school)
  VALUES
  ( 1000, 'Slime Cheese', 'Slimy', '2003-10-01', 'male', 'intern', 10, '2018-10-01', 'Vancouver Eagle Secondary'),
  ( 1001, 'Bronze Man', 'Bronzy', '2002-01-30', 'female', 'student', 11, '2019-08-01', 'Coquitlam Secondary');

-- STUDENT CONTACT --
INSERT INTO public.student_contact
(membership_id, cell_phone, email, home_phone, address, city, province, postal_code)
VALUES
(1000, '778-555-1000', 'slimy.cheese@email.com', '604-333-1000', '777 Lucky st.', 'Vancouver', 'BC', 'V3E 3A3'),
(1001, '778-555-1001', 'bronze.man@email.com', '604-333-1001', '1000 Lindle way', 'Coquitlam', 'BC', 'V3K 3M3');

-- PROGAM --
INSERT INTO public.program(subject, cost)
VALUES
('debate', 500),
('coding', 500),
('mechatronics', 600),
('math', 400),
('english', 400);

-- TEACHER (level = intern (1), trainer (2), coach (3)) --
INSERT INTO public.teacher(
    teacher_name, cell_phone, email, home_phone, address, city, province, postal_code, subject, status, level, start_date)
VALUES
('John Smith','604-555-0000','john.smith@gmail.com',null,'1055 west Hasting st.','Vancouver','BC','V5K 0A1','Debate', 'active', 1,'2018-12-31'),
('Michael Jo','604-555-1111','michael.jo@gmail.com','604-939-8893','739 Linton st.','Coquitlam','BC','V5J 6K4','Coding', 'active', 5,'2017-12-31');


-- User
INSERT INTO public.user(username, name, password, enabled)
  VALUES ('michael.jo@gmail.com', 'Michael Jo','$2a$12$R5x9qtnCadcEi/QcmbCpI.71j6UPAt3mGNnyYukkPd1gYxPUPdHru', '1');

-- Role
INSERT INTO public.role( authority)
VALUES
('ADMIN'),
('STAFF'),
('USER'),
('GUEST');

-- UserRole
INSERT INTO public.userrole(
  user_id, role_id)
  VALUES (100, 10);