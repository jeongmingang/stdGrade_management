-- 성적관리
DROP SCHEMA IF EXISTS grade;

-- 성적관리
CREATE SCHEMA grade;

-- 학생
CREATE TABLE grade.student (
	stdNo   INT         NOT NULL COMMENT '학번', -- 학번
	stdName VARCHAR(20) NULL     COMMENT '학생명', -- 학생명
	ban     VARCHAR(10) NULL     COMMENT '분반' -- 분반
)
COMMENT '학생';

-- 학생
ALTER TABLE grade.student
	ADD CONSTRAINT PK_student -- 학생 기본키
		PRIMARY KEY (
			stdNo -- 학번
		);

-- 분반
CREATE TABLE grade.ban (
	ban VARCHAR(10) NOT NULL COMMENT '분반' -- 분반
)
COMMENT '분반';

-- 분반
ALTER TABLE grade.ban
	ADD CONSTRAINT PK_ban -- 분반 기본키
		PRIMARY KEY (
			ban -- 분반
		);

-- 과목
CREATE TABLE grade.subject (
	subjNo   INT         NOT NULL COMMENT '과목번호', -- 과목번호
	subjName VARCHAR(20) NULL     COMMENT '과목이름' -- 과목이름
)
COMMENT '과목';

-- 과목
ALTER TABLE grade.subject
	ADD CONSTRAINT PK_subject -- 과목 기본키
		PRIMARY KEY (
			subjNo -- 과목번호
		);

-- 성적
CREATE TABLE grade.score (
	stdNo    INT NOT NULL COMMENT '학번', -- 학번
	subjNo   INT NOT NULL COMMENT '과목번호', -- 과목번호
	stdScore INT NULL     COMMENT '점수' -- 점수
)
COMMENT '성적';

-- 성적
ALTER TABLE grade.score
	ADD CONSTRAINT PK_score -- 성적 기본키
		PRIMARY KEY (
			stdNo,  -- 학번
			subjNo  -- 과목번호
		);

-- 학생 세부정보
CREATE TABLE grade.std_detail (
	stdNo    INT      NOT NULL COMMENT '학번', -- 학번
	pic      LONGBLOB NULL     COMMENT '증명사진', -- 증명사진
	gender   TINYINT  NULL     COMMENT '성별', -- 성별
	birthday DATE     NULL     COMMENT '생년월일' -- 생년월일
)
COMMENT '학생 세부정보';

-- 학생 세부정보
ALTER TABLE grade.std_detail
	ADD CONSTRAINT PK_std_detail -- 학생 세부정보 기본키
		PRIMARY KEY (
			stdNo -- 학번
		);

-- 학생
ALTER TABLE grade.student
	ADD CONSTRAINT FK_ban_TO_student -- 분반 -> 학생
		FOREIGN KEY (
			ban -- 분반
		)
		REFERENCES grade.ban ( -- 분반
			ban -- 분반
		);

-- 성적
ALTER TABLE grade.score
	ADD CONSTRAINT FK_student_TO_score -- 학생 -> 성적
		FOREIGN KEY (
			stdNo -- 학번
		)
		REFERENCES grade.student ( -- 학생
			stdNo -- 학번
		)
		ON DELETE CASCADE;

-- 성적
ALTER TABLE grade.score
	ADD CONSTRAINT FK_subject_TO_score -- 과목 -> 성적
		FOREIGN KEY (
			subjNo -- 과목번호
		)
		REFERENCES grade.subject ( -- 과목
			subjNo -- 과목번호
		)
		ON DELETE CASCADE;

-- 학생 세부정보
ALTER TABLE grade.std_detail
	ADD CONSTRAINT FK_student_TO_std_detail -- 학생 -> 학생 세부정보
		FOREIGN KEY (
			stdNo -- 학번
		)
		REFERENCES grade.student ( -- 학생
			stdNo -- 학번
		)
		ON DELETE CASCADE;
	
-- 권한 부여
grant all 
	on grade.*
	to 'user_grade'@'localhost' identified by 'rootroot';


	
	
