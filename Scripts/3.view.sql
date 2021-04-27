select user(), database ();

desc ban;
desc subject;
desc student;
desc score;
desc std_detail;

--
create or replace view vw_student_score
as
select s.stdNo, stdName, ban,  
		sum(if(subjNo = 1, stdScore, 0)) '국어',
		sum(if(subjNo = 2, stdScore, 0)) '영어',
		sum(if(subjNo = 3, stdScore, 0)) '수학',
		sum(if(subjNo = 4, stdScore, 0)) '사회',
		sum(if(subjNo = 5, stdScore, 0)) '과학',
		sum(stdScore) 'sumScore',
		round(avg(stdScore),1) 'avgScore'
		from student s join score c on s.stdNo = c.stdNo
		group by stdNo;
	
select * from vw_student_score;

select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, SumScore, avgScore from vw_student_score;

select s.stdNo, stdName, ban,  
		sum(if(subjNo = 1, stdScore, 0)) '국어',
		sum(if(subjNo = 2, stdScore, 0)) '영어',
		sum(if(subjNo = 3, stdScore, 0)) '수학',
		sum(if(subjNo = 4, stdScore, 0)) '사회',
		sum(if(subjNo = 5, stdScore, 0)) '과학',
		sum(stdScore) 'sumScore',
		round(avg(stdScore),1) 'avgScore'
		from student s join score c on s.stdNo = c.stdNo
		group by stdNo;





