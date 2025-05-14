
select * from class_details_test cdt ;
select * from section_details_test sdt ;
select * from student_details_test sdt ;
select *  from exam_details_test edt ;
select *  from class_details_test cdt ;
update exam_details_test set finaly_calculated=false ;
delete from section_details_test;
select *  from exam_details_test edt, student_details_test , class_details_test  ;
--Query to get students by standard and section
select * from student_details_test sdt where sdt.class_stud_fk = (select cdt.class_id  from class_details_test cdt  where cdt.standard = '3' 
and cdt.class_sect_fk=(select sdt2.sect_id from section_details_test sdt2 where sdt2.section_name='HH'));

--query to get standard wise sections
select cdt.section_name , cdt.standard  from class_details_test cdt group by cdt.standard,cdt.section_name ;

--Query to get marks of each student
select
	edt.*,
	sdt."name"
from
	exam_details_test edt
inner join student_details_test sdt on
	sdt.stud_id = edt.exam_stud_fk ;

--Query to get student by section and standard
select sdt.stud_id as studId,sdt."name" as studentName,sdt.assigned_status as assignedStatus,sdt.std as standard,cdt.standard,cdt.section_name as sectionName from class_details_test cdt , student_details_test sdt where 
cdt.class_id =sdt.class_stud_fk and sdt.assigned_status='Allotted'
and cdt.section_name = :section and (cdt.standard=:std);

--query to get studdent of each standard and section
select sdt.std,sdt."name"  from student_details_test sdt group by sdt.std,sdt."name" ;
--query to get all student along with standard and section
select sdt."name" ,sdt.std, cdt.section_name, cdt.class_id ,sdt.stud_id   from student_details_test sdt ,class_details_test cdt where sdt.class_stud_fk =cdt.class_id;

select edt.*, sdt."name" from exam_details_test edt , student_details_test sdt , class_details_test cdt  where sdt.stud_id = edt.exam_stud_fk  and
sdt.class_stud_fk = (select cdt.class_id  from class_details_test cdt  where cdt.standard = '3' and cdt.class_sect_fk=(select sdt2.sect_id from section_details_test sdt2 where sdt2.section_name='HH'));


--query to get exam details of student bu section and standard
select sdt.exam_allottment_status as examStatus,sdt.stud_id as studId,sdt.name as studentName,sdt.assigned_status as assignedStatus,sdt.std as standard,cdt.standard,cdt.section_name as sectionName from class_details_test cdt , student_details_test sdt,exam_details_test edt   where cdt.class_id =sdt.class_stud_fk and sdt.assigned_status='Allotted' and edt.exam_stud_fk = sdt.stud_id and cdt.section_name = 'HH' and (cdt.standard='3');

select * from student_details_test sdt ;
--query to get exams of all student
select
edt.year,    edt.is_marks_assigned ,    edt.finaly_calculated ,    edt.result_status ,edt.calc_status ,    edt.subject1Name,    edt.subject2Name,    edt.subject3Name,    edt.subject4Name,    edt.subject5Name,edt.subject1total_marks ,    edt.subject2total_marks ,    edt.subject3total_marks ,    edt.subject4total_marks ,    edt.subject5total_marks ,edt.subject1obtained_marks ,    edt.subject2obtained_marks ,    edt.subject3obtained_marks ,    edt.subject4obtained_marks ,    edt.subject5obtained_marks ,edt.total_marks ,    edt.total_obtained_marks ,edt.percentage,sdt."name"  from exam_details_test edt,student_details_test sdt where exam_stud_fk = sdt.stud_id;

--query to get exams of particular student
select edt.year , edt.is_marks_assigned , edt.finaly_calculated , edt.result_status ,edt.calc_status ,    edt.subject1Name,    edt.subject2Name,    edt.subject3Name,    edt.subject4Name,    edt.subject5Name,edt.subject1total_marks ,    edt.subject2total_marks ,    edt.subject3total_marks ,    edt.subject4total_marks ,    edt.subject5total_marks ,edt.subject1obtained_marks ,    edt.subject2obtained_marks ,    edt.subject3obtained_marks ,    edt.subject4obtained_marks ,    edt.subject5obtained_marks ,edt.total_marks ,    edt.total_obtained_marks ,edt.percentage,sdt."name"  from exam_details_test edt,student_details_test sdt where exam_stud_fk = sdt.stud_id and  sdt.stud_id= '452';