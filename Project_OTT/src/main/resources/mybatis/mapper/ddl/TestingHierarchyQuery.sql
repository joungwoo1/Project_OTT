drop table T_H

--느려서 성능은 담보할 수 없음
create table T_H(
	id		INT primary key,
	hid		INT,
	name	varchar(255) not null
);


insert into t_h(id, hid, name)
values (0, null, '하나님');
insert into t_h(id, hid, name)
values (1, 0, '하나님 아들');
insert into t_h(id, hid, name)
values (2, 0, '하나님 둘째 아들');
insert into t_h(id, hid, name)
values (3, 2, '할아버지');
insert into t_h(id, hid, name)
values (4, 3, '아빠');
insert into t_h(id, hid, name)
values (5, 4, '나');
insert into t_h(id, hid, name)
values (6, 4, '동생');
insert into t_h(id, hid, name)
values (7, 5, '내 아들');
insert into t_h(id, hid, name)
values (8, 6, '동생 아들');


--'나' 찾기
select me.*
  from T_H me
 where me.name = '나';
 
--'나'의 부모 찾기
select me.*, p1.*
  from T_H me, T_H p1
 where me.name = '나'
   and me.hid = p1.id;

--'나'의 3대 조상 찾기
select me.*, p1.*, p2.*, p3.*
  from T_H me, T_H p1, T_H p2, T_H p3
 where me.name = '나'
   and me.hid = p1.id
   and p1.hid = p2.id
   and p2.hid = p3.id;


--시조부터 모든 후손
WITH RECURSIVE prevResult AS(
	SELECT id, name
	  FROM T_H
	 WHERE id = 0
	
	UNION ALL
	
	SELECT 
		child.id, child.name
		FROM T_H child
		INNER JOIN prevResult
		ON prevResult.id = child.hid
	
)
SELECT *
FROM prevResult;


--'나'의 모든 조상
WITH RECURSIVE prevResult AS(
	SELECT id, hid, name
	  FROM T_H
	 WHERE name = '나'
	
	UNION ALL
	
	SELECT 
		parent.id, parent.hid, parent.name
		FROM T_H parent
		INNER JOIN prevResult
		ON prevResult.hid = parent.id
	
)
SELECT *
FROM prevResult;