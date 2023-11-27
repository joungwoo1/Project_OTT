t_score 테이블은 std_id, class_id, score, grade

create table t_score(
	std_id char(5) ,
	class_id char(5),
	score int,
	grade char(1),
	primary key (std_id, class_id),
	);
	
insert into t_score(std_id, class_id,score)
values('00009', '99999', 55);

insert into t_score(std_id, class_id,score)
values('00009', '99998', 90);

insert into t_score(std_id, class_id,score, grade)
values('00009', '99997', 88 , 'A');

insert into t_score(std_id, class_id,score)
values('00009', '99995', 66);

DELIMITER $$
CREATE OR REPLACE PROCEDURE GRAIDING()
Begin
	update t_score
		set grade = case
		    when  score >= 90 then 'A'
			when  score >= 80 then 'B'
			when  score >= 70 then 'C'
			when  score >= 60 then 'D'
		    else 'F'
		end
	WHERE grade is null;
END;
$$
DELIMITER;

call GRAIDING;


select std_id, class_id,
	case
	    when  score >= 90 then 'A'
		when  score >= 80 then 'B'
		when  score >= 70 then 'C'
		when  score >= 60 then 'D'
	    else 'F'
	end
	from t_score;
	where grade is null;
End





