select user(), database ();

use grade;

show tables;

desc ban;
desc subject;
desc student;
desc score;
desc std_detail;

insert into ban values ('A01');
insert into ban values ('A02');

select * from ban;

insert into subject values (1, '국어');
insert into subject values (2, '영어');
insert into subject values (3, '수학');
insert into subject values (4, '사회');
insert into subject values (5, '과학');

select * from subject;

desc student;

insert into student values (20001, '박재선', 'A01');
insert into student values (20002, '한동성', 'A01');
insert into student values (20003, '정정일', 'A01');
insert into student values (20004, '정명훈', 'A01');
insert into student values (20005, '임정만', 'A01');
insert into student values (20006, '임성준', 'A01');
insert into student values (20007, '윤석수', 'A01');
insert into student values (20008, '이준민', 'A01');
insert into student values (20009, '이강길', 'A01');
insert into student values (20010, '박철호', 'A01');
insert into student values (20011, '여재일', 'A01');
insert into student values (20012, '박상엽', 'A01');
insert into student values (20013, '간효상', 'A01');
insert into student values (20014, '형동훈', 'A01');
insert into student values (20015, '진인우', 'A01');
insert into student values (20016, '박운승', 'A02');
insert into student values (20017, '김윤재', 'A02');
insert into student values (20018, '황보동명', 'A02');
insert into student values (20019, '사대호', 'A02');
insert into student values (20020, '박동수', 'A02');
insert into student values (20021, '안추환', 'A02');
insert into student values (20022, '정문식', 'A02');
insert into student values (20023, '윤대건', 'A02');
insert into student values (20024, '양우준', 'A02');
insert into student values (20025, '서동윤', 'A02');
insert into student values (20026, '송무길', 'A02');
insert into student values (20027, '박영우', 'A02');
insert into student values (20028, '신우석', 'A02');
insert into student values (20029, '이보민', 'A02');
insert into student values (20030, '조정우', 'A02');

select * from student;
                                    
desc score;

insert into score values (20001, 1, 72);
insert into score values (20001, 2, 73); 
insert into score values (20001, 3, 90); 
insert into score values (20001, 4, 72); 
insert into score values (20001, 5, 84); 

insert into score values (20002, 1, 83);
insert into score values (20002, 2, 72);
insert into score values (20002, 3, 83);
insert into score values (20002, 4, 90);
insert into score values (20002, 5, 91);

insert into score values (20003, 1, 88);
insert into score values (20003, 2, 72);
insert into score values (20003, 3, 92);
insert into score values (20003, 4, 88);
insert into score values (20003, 5, 99);

insert into score values (20004, 1, 90);
insert into score values (20004, 2, 70);
insert into score values (20004, 3, 82);
insert into score values (20004, 4, 93);
insert into score values (20004, 5, 98);

insert into score values (20005, 1, 78);
insert into score values (20005, 2, 95);
insert into score values (20005, 3, 79);
insert into score values (20005, 4, 79);
insert into score values (20005, 5, 97);

insert into score values (20006, 1, 77);
insert into score values (20006, 2, 95);
insert into score values (20006, 3, 87);
insert into score values (20006, 4, 81);
insert into score values (20006, 5, 85);

insert into score values (20007, 1, 71);
insert into score values (20007, 2, 72);
insert into score values (20007, 3, 92);
insert into score values (20007, 4, 91);
insert into score values (20007, 5, 96);

insert into score values (20008, 1, 95);
insert into score values (20008, 2, 94);
insert into score values (20008, 3, 93);
insert into score values (20008, 4, 88);
insert into score values (20008, 5, 100);

insert into score values (20009, 1, 97);
insert into score values (20009, 2, 78);
insert into score values (20009, 3, 78);
insert into score values (20009, 4, 90);
insert into score values (20009, 5, 73);

insert into score values (20010, 1, 95);
insert into score values (20010, 2, 78);
insert into score values (20010, 3, 80);
insert into score values (20010, 4, 92);
insert into score values (20010, 5, 72);  

insert into score values (20011, 1, 96);
insert into score values (20011, 2, 72);
insert into score values (20011, 3, 75);
insert into score values (20011, 4, 81);
insert into score values (20011, 5, 81);

insert into score values (20012, 1, 89);
insert into score values (20012, 2, 93);
insert into score values (20012, 3, 100);
insert into score values (20012, 4, 88);
insert into score values (20012, 5, 83);

insert into score values (20013, 1, 73);
insert into score values (20013, 2, 82);
insert into score values (20013, 3, 95);
insert into score values (20013, 4, 76);
insert into score values (20013, 5, 90);

insert into score values (20014, 1, 87);
insert into score values (20014, 2, 90);
insert into score values (20014, 3, 92);
insert into score values (20014, 4, 96);
insert into score values (20014, 5, 73);

insert into score values (20015, 1, 94);
insert into score values (20015, 2, 75);
insert into score values (20015, 3, 76);
insert into score values (20015, 4, 97);
insert into score values (20015, 5, 75);

insert into score values (20016, 1, 84);
insert into score values (20016, 2, 97);
insert into score values (20016, 3, 88);
insert into score values (20016, 4, 87);
insert into score values (20016, 5, 85);

insert into score values (20017, 1, 77);
insert into score values (20017, 2, 83);
insert into score values (20017, 3, 70);
insert into score values (20017, 4, 98);
insert into score values (20017, 5, 88);

insert into score values (20018, 1, 81);
insert into score values (20018, 2, 90);
insert into score values (20018, 3, 74);
insert into score values (20018, 4, 73);
insert into score values (20018, 5, 73);

insert into score values (20019, 1, 71);
insert into score values (20019, 2, 83);
insert into score values (20019, 3, 79);
insert into score values (20019, 4, 99);
insert into score values (20019, 5, 83);

insert into score values (20020, 1, 86);
insert into score values (20020, 2, 98);
insert into score values (20020, 3, 92);
insert into score values (20020, 4, 81);
insert into score values (20020, 5, 73);

insert into score values (20021, 1, 98);
insert into score values (20021, 2, 97);
insert into score values (20021, 3, 93);
insert into score values (20021, 4, 90);
insert into score values (20021, 5, 71);

insert into score values (20022, 1, 92);
insert into score values (20022, 2, 80);
insert into score values (20022, 3, 75);
insert into score values (20022, 4, 81);
insert into score values (20022, 5, 100);

insert into score values (20023, 1, 87);
insert into score values (20023, 2, 77);
insert into score values (20023, 3, 85);
insert into score values (20023, 4, 78);
insert into score values (20023, 5, 78);

insert into score values (20024, 1, 95);
insert into score values (20024, 2, 84);
insert into score values (20024, 3, 73);
insert into score values (20024, 4, 76);
insert into score values (20024, 5, 84);

insert into score values (20025, 1, 95);
insert into score values (20025, 2, 96);
insert into score values (20025, 3, 98);
insert into score values (20025, 4, 100);
insert into score values (20025, 5, 77);

insert into score values (20026, 1, 77);
insert into score values (20026, 2, 80);
insert into score values (20026, 3, 78);
insert into score values (20026, 4, 99);
insert into score values (20026, 5, 82);

insert into score values (20027, 1, 84);
insert into score values (20027, 2, 74);
insert into score values (20027, 3, 95);
insert into score values (20027, 4, 76);
insert into score values (20027, 5, 96);

insert into score values (20028, 1, 70);
insert into score values (20028, 2, 78);
insert into score values (20028, 3, 77);
insert into score values (20028, 4, 97);
insert into score values (20028, 5, 71);

insert into score values (20029, 1, 100);
insert into score values (20029, 2, 76);
insert into score values (20029, 3, 96);
insert into score values (20029, 4, 75);
insert into score values (20029, 5, 87);

insert into score values (20030, 1, 74);
insert into score values (20030, 2, 78);
insert into score values (20030, 3, 93);
insert into score values (20030, 4, 83);
insert into score values (20030, 5, 73);

select * from score;

desc std_detail;

insert into std_detail
values
(20001, null, 1, 19940817),
(20002, null, 1, 19940425),
(20003, null, 1, 19940525),
(20004, null, 1, 19940421),
(20005, null, 1, 19940422),
(20006, null, 1, 19940325),
(20007, null, 1, 19940125),
(20008, null, 1, 19940225),
(20009, null, 1, 19940325),
(20010, null, 1, 19940525),
(20011, null, 1, 19940625),
(20012, null, 1, 19940725),
(20013, null, 1, 19940825),
(20014, null, 1, 19940925),
(20015, null, 1, 19941225),
(20016, null, 1, 19941025),
(20017, null, 1, 19941125),
(20018, null, 1, 19940221),
(20019, null, 1, 19940422),
(20020, null, 2, 19940423),
(20021, null, 2, 19940405),
(20022, null, 2, 19940401),
(20023, null, 2, 19940408),
(20024, null, 2, 19940406),
(20025, null, 2, 19940408),
(20026, null, 2, 19940409),
(20027, null, 2, 19940422),
(20028, null, 2, 19940411),
(20029, null, 2, 19940424),
(20030, null, 2, 19940603);

select * from std_detail;

delete from std_detail;




