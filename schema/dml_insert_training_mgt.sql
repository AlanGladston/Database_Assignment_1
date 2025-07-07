INSERT INTO course_table (course_name) VALUES
('Mathematics'),
('Physics'),
('Chemistry'),
('Biology'),
('English');

INSERT INTO topic_table (topic_name, course_id) VALUES
('Algebra', 1),
('Trigonometry', 1),
('Mechanics', 2),
('Optics', 2),
('Organic Chemistry', 3),
('Human Anatomy', 4),
('Plant Physiology', 4),
('Grammar', 5),
('Essay Writing', 5);

INSERT INTO trainer_table (trainer_name) VALUES
('Gowsal Nazer'),
('Aadithya karma'),
('Athul KS'),
('Arun Martin'),
('Jerry Biku');

INSERT INTO batch_table (course_id, start_date, end_date) VALUES
(1, '2025-07-01', '2025-09-30'),
(2, '2025-07-01', '2025-09-30'),
(3, '2025-07-01', '2025-09-30'),
(4, '2025-07-01', '2025-09-30'),
(5, '2025-07-01', '2025-09-30');

INSERT INTO candidate_table (candidate_name, email, phone_number) VALUES
('Geethu Miss', 'geethu@yahoo.com', '1111111111'),
('Nisha Miss', 'nisha@amail.com', '2222222222'),
('Praveen Sir', 'praveen@bmail.com', '3333333333'),
('Prince Sir', 'prince@cmail.com', '4444444444'),
('Mufeed Sir', 'mufeed@dmail.com', '5555555555');

INSERT INTO candidate_batch (candidate_id, batch_code, current_status) VALUES
(1, 1, 'In Progress'),
(2, 2, 'Completed'),
(3, 1, 'Terminated'),
(4, 3, 'In Progress'),
(5, 5, 'Completed');

INSERT INTO assignment (batch_code, title, description, due_date) VALUES
(1, 'Algebra Worksheet', 'Solve problems from chapter 2', '2025-07-15'),
(2, 'Mechanics Lab', 'Complete the experiment on motion', '2025-07-20'),
(3, 'Organic Chemistry Quiz', 'Prepare quiz on hydrocarbons', '2025-07-25'),
(4, 'Biology Project', 'Prepare a model on human heart', '2025-08-01'),
(5, 'Essay Writing', 'Write an essay on environmental conservation', '2025-08-05');

INSERT INTO trainer_batch (trainer_id, batch_code) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

INSERT INTO submission (submission_id, assignment_id, candidate_id, submission_date, score) VALUES
(1, 1, 1, '2025-07-14', 85),
(2, 1, 3, '2025-07-15', 90),
(3, 2, 2, '2025-07-19', 88),
(4, 3, 4, '2025-07-24', 92),
(5, 5, 5, '2025-08-04', 95);