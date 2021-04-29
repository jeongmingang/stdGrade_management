select user(), database ();

--
select ban from ban;
select subjNo, subjName from subject;
select stdNo, stdName, ban from student;
select stdNo, subjNo, stdScore from score;
select stdNo, subjNo, stdScore from score where stdNo = 20001 and subjNo = 1;
select stdNo, pic, gender, birthday from std_detail;

-- 분반 테이블 --------------------------------------------------------
select ban from ban;

-- 분반 추가
insert into ban values ('A03');

-- 분반 삭제
delete from ban where ban = 'A03';

-- 모든 과목 검색 ------------------------------------------------------
select subjNo, subjName from subject;

-- 과목 번호로 과목 검색
select subjNo, subjName from subject where subjNo = 1;

-- 과목 추가
insert into subject values (6,'한국사');

-- 과목 삭제
delete from subject where subjNo = 6;

-- 학생 테이블 --------------------------------------------------------
select stdNo, stdName, ban from student;

-- 학생 전체 정보 검색 --
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score;

-- 학번으로 학생정보 검색
select stdNo, stdName, ban from student where stdNo = 20002;

-- 성명으로 학생정보 검색
select stdNo, stdName, ban from student where stdName = '황보동명';

-- 학생 추가
insert into student values (20001, '임성준', 'A02');

-- 학생 수정
update student 
	set stdName = '신재석' , ban = 'A01' 
 where stdNo = 20031;

-- 학생 삭제
delete from student where stdNo = 20033;

-- 점수 테이블 ---------------------------------------------------------
select stdNo, subjNo, stdScore from score;

-- 학번으로 점수 검색
select stdNo, subjNo, stdScore from score where stdNo = "30001";

-- 점수 추가
insert into score values (30001, 2, 73); 

-- 점수 수정
update score 
	set stdScore = 100 
 where stdNo = 20032 and subjNo = 2;

-- 점수 삭제
delete from score where stdNo = 10022; 

-- 학생 상세 정보 테이블 -------------------------------------------------------
select stdno, pic, gender, birthday from std_detail;

-- 학번으로 학생 상세정보 검색
select stdno, pic, gender, birthday from std_detail where stdNo = "20001"; 

-- 학생 상세 정보 추가
insert into std_detail values (20001, null, true, 19940817);

-- 학생 상세 정보 수정
update std_detail 
	set pic = null, gender = false, birthday = 19940819 
 where stdNo = 20001;

-- 학생 상세 정보 삭제
delete from std_detail where stdno = 20031;

-- 전체 학생 정보 ----------------------------------------
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
	
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score;

-- 학번으로 전체 학생 정보 검색
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
 where stdNo = 20020;

-- 전체성적 평균 내림차순 정렬
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	order by avgScore desc;

-- 전체성적 상위평균 인원수만큼만 가져와 내림차순 정렬
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	order by avgScore desc limit 5;

-- 전체성적 과목 점수 내림차순 정렬
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	order by 국어 desc, avgScore desc;

-- 전체성적 과목 점수 상위평균 인원수만큼만 가져와 내림차순 정렬
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	order by 국어 desc limit 5;

-- 이름으로 전체 학생 정보 검색
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
where stdName = '정명훈';

-- 전체성적 분반 별 조회 (평균 내림차순)
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	where ban = 'A01'
	order by avgScore desc; 
	
-- 전체성적 분반 별 조회(과목점수 내림차순)
select stdNo, stdName, ban, 국어, 영어, 수학, 사회, 과학, sumScore, avgScore
	from vw_student_score
	where ban = 'A01'
	order by 국어 desc, avgScore desc; 

-- test---------------------------------------------------- 
select stdNo, subjNo, stdScore from score;

select stdNo, subjNo, stdScore from score where stdNo = "30001";

select stdNo, subjNo, stdScore from score where subjNo = 1;

select stdScore from score where subjNo = 1;

-- 과목별 평균점수 구하기 ----------------------------------------------------------
select round(sum(국어)/count(stdNo),1) 국어평균점수 from vw_student_score;
select round(sum(영어)/count(stdNo),1) 영어평균점수 from vw_student_score;
select round(sum(수학)/count(stdNo),1) 수학평균점수 from vw_student_score;
select round(sum(사회)/count(stdNo),1) 사회평균점수 from vw_student_score;
select round(sum(과학)/count(stdNo),1) 과학평균점수 from vw_student_score;
select round(sum(국어+영어+수학+사회+과학)/5/count(stdNo),2) 전체평균점수 from vw_student_score;

select round(sum(stdScore)/count(stdNo),1) 국어평균점수 from score where subjNo = 1;
select round(sum(stdScore)/count(stdNo),1) 영어평균점수 from score where subjNo = 2;
select round(sum(stdScore)/count(stdNo),1) 수학평균점수 from score where subjNo = 3;
select round(sum(stdScore)/count(stdNo),1) 사회평균점수 from score where subjNo = 4;
select round(sum(stdScore)/count(stdNo),1) 과학평균점수 from score where subjNo = 5;

select round(sum(stdScore)/count(stdNo),2) 전체평균점수 from score;



select round(sum(국어)/count(stdNo),1) 국어평균점수,
	   round(sum(영어)/count(stdNo),1) 영어평균점수,
	   round(sum(수학)/count(stdNo),1) 수학평균점수,
	   round(sum(사회)/count(stdNo),1) 사회평균점수,
	   round(sum(과학)/count(stdNo),1) 과학평균점수,
	   round(sum(국어+영어+수학+사회+과학)/5/count(stdNo),2) 전체평균점수
	from vw_student_score;




