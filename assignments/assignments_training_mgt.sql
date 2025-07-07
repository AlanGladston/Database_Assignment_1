/*Get all batches a candidate is enrolled in, with their status.*/
SELECT candidate_batch.batch_code, batch_table.course_id, course_table.course_name, candidate_batch.current_status
FROM candidate_batch
JOIN batch_table ON candidate_batch.batch_code = batch_table.batch_code
JOIN course_table ON batch_table.course_id = course_table.course_id
WHERE candidate_batch.candidate_id = 1;

/* Get all trainers assigned to a batch. */
SELECT trainer_table.trainer_name, 
       batch_table.batch_code, 
       course_table.course_name, 
       batch_table.start_date, 
       batch_table.end_date
FROM trainer_table
JOIN trainer_batch ON trainer_table.trainer_id = trainer_batch.trainer_id
JOIN batch_table ON trainer_batch.batch_code = batch_table.batch_code
JOIN course_table ON batch_table.course_id = course_table.course_id
WHERE batch_table.batch_code = 1;

/*Get all topics under a course.*/
SELECT course_table.course_name, 
       topic_table.topic_name
FROM course_table
JOIN topic_table ON course_table.course_id = topic_table.course_id
WHERE course_table.course_id = 1;

/*List assignment scores for a candidate in a batch.*/
SELECT candidate_table.candidate_name, 
       batch_table.batch_code, 
       course_table.course_name, 
       assignment.title, 
       assignment.description, 
       assignment.due_date, 
       submission.submission_date, 
       submission.score
FROM candidate_table
JOIN candidate_batch ON candidate_table.candidate_id = candidate_batch.candidate_id
JOIN batch_table ON candidate_batch.batch_code = batch_table.batch_code
JOIN course_table ON batch_table.course_id = course_table.course_id
JOIN assignment ON batch_table.batch_code = assignment.batch_code
JOIN submission ON assignment.assignment_id = submission.assignment_id 
AND candidate_table.candidate_id = submission.candidate_id
WHERE candidate_table.candidate_id = 1
AND batch_table.batch_code = 1;

/*List candidates with status "Completed" in a given batch.*/
SELECT candidate_table.candidate_name, 
       candidate_table.email, 
       candidate_table.phone_number, 
       batch_table.batch_code, 
       course_table.course_name, 
       candidate_batch.current_status
FROM candidate_table
JOIN candidate_batch ON candidate_table.candidate_id = candidate_batch.candidate_id
JOIN batch_table ON candidate_batch.batch_code = batch_table.batch_code
JOIN course_table ON batch_table.course_id = course_table.course_id
WHERE candidate_batch.current_status = 'Completed' AND batch_table.batch_code = 2;
