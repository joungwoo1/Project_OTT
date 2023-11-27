drop table T_ID_SEED;
create table T_ID_SEED(id varchar(3));

SELECT *
FROM T_ID_SEED;

DELETE
FROM T_ID_SEED
where length(id) = 1;

INSERT INTO T_ID_SEED(id)
SELECT CONCAT(a.id, b.id)
FROM T_ID_SEED a, T_ID_SEED b;
where length(a.id) = 2;
  and length(b.id) = 1;

--앞에 primary key로 번호 추가
alter table T_ID_SEED add column (num integer);
alter table T_ID_SEED drop column num;

SELECT tid.*, @ROWNUM:=@ROWNUM+1 AS rowNum
FROM T_ID_SEED tid, (SELECT @ROWNUM:=0) AS R

UPDATE T_ID_SEED t1, (
	SELECT tid.id iid, @ROWNUM:=@ROWNUM+1 AS rowNum
		FROM T_ID_SEED tid, (SELECT @ROWNUM:=0) AS R
		ORDER BY iid ASC
	)t2 SET t1.num = t2.rowNum
WHERE t1.id = t2.iid;

select num, count(*) dup_Cnt
from T_ID_SEED
group by num
having dup_Cnt > 1;

alter table T_ID_SEED add primary key (num);

SELECT *
FROM T_ID_SEED
WHERE num > 1600000;

INSERT INTO T_ID_SEED(id) VALUES ('a');
INSERT INTO T_ID_SEED(id) VALUES ('b');
INSERT INTO T_ID_SEED(id) VALUES ('c');
INSERT INTO T_ID_SEED(id) VALUES ('d');
INSERT INTO T_ID_SEED(id) VALUES ('e');
INSERT INTO T_ID_SEED(id) VALUES ('f');
INSERT INTO T_ID_SEED(id) VALUES ('g');
INSERT INTO T_ID_SEED(id) VALUES ('h');
INSERT INTO T_ID_SEED(id) VALUES ('i');
INSERT INTO T_ID_SEED(id) VALUES ('j');
INSERT INTO T_ID_SEED(id) VALUES ('k');
INSERT INTO T_ID_SEED(id) VALUES ('l');
INSERT INTO T_ID_SEED(id) VALUES ('m');
INSERT INTO T_ID_SEED(id) VALUES ('n');
INSERT INTO T_ID_SEED(id) VALUES ('o');
INSERT INTO T_ID_SEED(id) VALUES ('p');
INSERT INTO T_ID_SEED(id) VALUES ('q');
INSERT INTO T_ID_SEED(id) VALUES ('r');
INSERT INTO T_ID_SEED(id) VALUES ('s');
INSERT INTO T_ID_SEED(id) VALUES ('t');
INSERT INTO T_ID_SEED(id) VALUES ('u');
INSERT INTO T_ID_SEED(id) VALUES ('v');
INSERT INTO T_ID_SEED(id) VALUES ('w');
INSERT INTO T_ID_SEED(id) VALUES ('x');
INSERT INTO T_ID_SEED(id) VALUES ('y');
INSERT INTO T_ID_SEED(id) VALUES ('z');
INSERT INTO T_ID_SEED(id) VALUES ('0');
INSERT INTO T_ID_SEED(id) VALUES ('1');
INSERT INTO T_ID_SEED(id) VALUES ('2');
INSERT INTO T_ID_SEED(id) VALUES ('3');
INSERT INTO T_ID_SEED(id) VALUES ('4');
INSERT INTO T_ID_SEED(id) VALUES ('5');
INSERT INTO T_ID_SEED(id) VALUES ('6');
INSERT INTO T_ID_SEED(id) VALUES ('7');
INSERT INTO T_ID_SEED(id) VALUES ('8');
INSERT INTO T_ID_SEED(id) VALUES ('9');

create table T_NUM(
   target_name varchar(255) primary key
   num integer
);