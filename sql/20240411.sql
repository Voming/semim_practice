select * from board;
commit;
exec pro_board_insert('kh1','제목',50);
EXEC PRO_BOARD_INSERT('kh2', '제목----', 50);

-- 페이지당 글 수 3씩
-- 페이지 5개씩
-- 전체 페이지 수 
select count(*)/5 from board;
-- 총 글 개수 113
-- 전체 페이지 수 = 총 글 개수/ 한페이지당 수 
select ceil(count(*)/5) from board;

select t2.* from 
    (select t1.*, rownum rn from
        (select * from board order by board_id desc) t1) t2 
where t2.rn between 1 and 3;
-- (한 페이지당 글 수 * 현재 페이지(-1)) +1 == 시작숫자 and 한 페이지당 글 수 * 현재 페이지 == 끝숫자

-- (현제 페이지 수 %  페이지 수 ==0 )?
-- 시작 페이지 startPageNum =(현제 페이지 수 %  페이지 수 ==0 ) ? (한 페이지당 글 수 * 현재 페이지(-1)) +1  : (한 페이지당 글 수 * 현재 페이지) +1 
-- 끝 페이지 endpageNum = start + 페이지 수 -1;
-- 또는 endpageNum = (endPageNum > 전체 페이지수) ? 전체 페이지 수 : start+페이지수-1;