CREATE TABLE course_table(
    course_id INT AUTO_INCREMENT,
    course_name VARCHAR(20),
    PRIMARY KEY(course_id)
);

CREATE TABLE topic_table(
    topic_id INT AUTO_INCREMENT,
    topic_name VARCHAR(20),
    course_id INT,
    PRIMARY KEY(topic_id),
    FOREIGN KEY(course_id) REFERENCES course_table(course_id)
);

CREATE TABLE trainer_table(
    trainer_id INT AUTO_INCREMENT,
    trainer_name VARCHAR(50),
    PRIMARY KEY(trainer_id)
);

CREATE TABLE batch_table(
    batch_code INT AUTO_INCREMENT,
    course_id int,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY(batch_code),
    foreign key(course_id) references course_table(course_id)
);

CREATE TABLE candidate_table(
    candidate_id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_name VARCHAR(20),
    email VARCHAR(50),
    phone_number VARCHAR(15)
);

CREATE TABLE candidate_batch(
    candidate_id INT,
    batch_code INT,
    current_status VARCHAR(20),
    PRIMARY KEY (candidate_id, batch_code),
    FOREIGN KEY (candidate_id) REFERENCES candidate_table(candidate_id),
    FOREIGN KEY (batch_code) REFERENCES batch_table(batch_code)
);

CREATE TABLE assignment(
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    batch_code INT,
    title VARCHAR(30),
    description TEXT,
    due_date DATE,
    FOREIGN KEY(batch_code) REFERENCES batch_table(batch_code)
);

CREATE TABLE trainer_batch(
    trainer_id INT,
    batch_code INT,
    PRIMARY KEY (trainer_id, batch_code),
    FOREIGN KEY (trainer_id) REFERENCES trainer_table(trainer_id),
    FOREIGN KEY (batch_code) REFERENCES batch_table(batch_code)
);


CREATE TABLE submission(
    submission_id INT PRIMARY KEY,
    assignment_id INT,
    candidate_id INT,
    submission_date DATE,
    score INT,
    FOREIGN KEY (assignment_id) REFERENCES assignment(assignment_id),
    FOREIGN KEY (candidate_id) REFERENCES candidate_table(candidate_id),
    UNIQUE (assignment_id, candidate_id) 
);


