select user (), database ();

create database grade;
drop database grade;

use grade;

create table studentgrade (
	stdno	int 		not null primary key,
	stdname varchar(20) not null,
	ban 	varchar(10) not null,
	kor 	int 		not null,
	eng 	int 		not null,
	math 	int 		not null,
	soc 	int 		not null,
	sci 	int		 	not null
);

drop table studentgrade;

desc studentgrade; 

insert into studentgrade values 
	('20001', '박재선',  'A01',	72,	73,	90,	72,	84),
	('20002', '한동성',  'A01',	83,	72,	83,	90,	91),
	('20003', '정정일',  'A01',	88,	72,	92,	88,	99),
	('20004', '정명훈',  'A01',	90,	70,	82,	93,	98),
	('20005', '임정만',  'A01',	78,	95,	79,	79,	97),
	('20006', '임성준',  'A01',	77,	95, 87,	81,	85),
	('20007', '윤석수',  'A01',	71,	72,	92,	91,	96),
	('20008', '이준민',  'A01',	95,	94,	93,	88,	100),
	('20009', '이강길',  'A01',	97,	78,	78,	90,	73),
	('20010', '박철호',  'A01',	95,	78,	80,	92,	72),
	('20011', '여재일',  'A01',	96,	72,	75,	81,	81),
	('20012', '박상엽',  'A01',	89,	93,	100, 88, 83),
	('20013', '간효상',  'A01',	73,	82,	95,	76,	90),
	('20014', '형동훈',  'A01',	87,	90,	92,	96,	73),
	('20015', '진인우',  'A01',	94,	75,	76,	97,	75),
	('20016', '박운승',  'A02',	84,	97,	88,	87,	85),
	('20017', '김윤재',  'A02',	77,	83,	70,	98,	88),
	('20018', '황보동명', 'A02',	81,	90, 74,	73,	73),
	('20019', '사대호',  'A02',	71,	83,	79,	99,	83),
	('20020', '박동수',	'A02',	86,	98,	92,	81,	73),
	('20021', '안추환',	'A02',	98,	97,	93,	90,	71),
	('20022', '정문식',	'A02',	92,	80,	75,	81,	100),
	('20023', '윤대건',   'A02',	87,	77,	85,	78,	78),
	('20024', '양우준',	'A02',	95,	84,	73,	76,	84),
	('20025', '서동윤',	'A02',	95,	96,	98,	100, 77),
	('20026', '송무길',	'A02',	77,	80,	78,	99,	82),
	('20027', '박영우',	'A02',	84,	74,	95,	76,	96),
	('20028', '신우석',	'A02',	70,	78,	77,	97,	71),
	('20029', '이보민',	'A02',	100, 76, 96, 75, 87),
	('20030', '조정우',   'A02',	74,	78,	93,	83,	73);

select * from studentgrade; 

grant all
	on grade.*
	to 'user_grade'@'localhost' 
identified by 'rootroot';

drop user 'user_grade'@'localhost' ;

-- studentgrade
select stdno, stdname, ban, kor, eng, math, soc, sci from studentgrade;
select stdno, stdname, ban, kor, eng, math, soc, sci from studentgrade where stdno = 20012;

insert into studentgrade values(20031, '정재영', 'A03', 98, 78, 92, 82, 76);

delete from studentgrade where stdno = 20031;

update studentgrade 
set stdname = '김민석', ban = 'A02', kor = 78, eng = 67, math = 87, soc = 67, sci = 100
where stdno = 20031;

-- 분반별성적확인
select stdno, stdname, ban, kor, eng, math, soc, sci,
format ((avg(kor)+avg(eng)+avg(math)+avg(soc)+avg(sci))/5,1) 
from studentgrade
where ban = 'A01'
group by stdno;

select format (avg(kor),1), format (avg(eng),1),
format (avg(math),1), format (avg(soc),1), format (avg(sci),1)
from studentgrade
where ban = 'A01';

select stdno, stdname, ban, kor, eng, math, soc, sci,
format ((avg(kor)+avg(eng)+avg(math)+avg(soc)+avg(sci))/5,1) 
from studentgrade
where ban = 'A01'
group by stdno
-- order by kor desc;

-- 전체성적확인
select stdno, stdname, ban, kor, eng, math, soc, sci,
format ((avg(kor)+avg(eng)+avg(math)+avg(soc)+avg(sci))/5,1) 
from studentgrade
group by stdno;

select format (avg(kor),1), format (avg(eng),1),
format (avg(math),1), format (avg(soc),1), format (avg(sci),1)
from studentgrade;

