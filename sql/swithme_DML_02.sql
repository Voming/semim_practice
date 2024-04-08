--더미 회원 만들기 MEMBER 테이블
insert into member values('song','swith','swithall@gmail.com',default,'모두스윗해지세요',default);
insert into member values('oh','swith','ohall@gmail.com',default,'저는 달콤이혜효',default);
insert into member values('kim','swith','kimall@gmail.com',default,'집보내줘요',default);
insert into member values('hyuk','swith','hyukall@gmail.com',default,'모두스윗포테이토되세요',default);
insert into member values('seo','swith','seoall@gmail.com',default,'문라익춘식',default);
insert into member values('hyo','swith','hyoall@gmail.com',default,'이거메가박스아녜요',default);

select * from member;

--SUBJECT 더미
-- 핑크 1, 연두 2, 노랑 3, 파랑 4, 보라 5
insert into subject values(SEQ_SUBJECT_ID.CURRVAL  ,'song','JAVA',default,null,1);
insert into subject values(SEQ_SUBJECT_ID.nextval,'song','python',default,null,3);
insert into subject values(SEQ_SUBJECT_ID.nextval,'song','토익',default,null,4);
insert into subject values(SEQ_SUBJECT_ID.nextval,'song','spring',default,null,5);

insert into subject values(SEQ_SUBJECT_ID.nextval,'hyuk','C#',default,null,1);
insert into subject values(SEQ_SUBJECT_ID.nextval,'hyuk','HTML',default,null,3);
insert into subject values(SEQ_SUBJECT_ID.nextval,'oh','토익',default,null,4);
insert into subject values(SEQ_SUBJECT_ID.nextval,'seo','spring',default,null,5);

select * from subject;

--RECORD 더미
select * from record;
-- 타이머 시작
insert into record values(
    SEQ_RECORD_ID.nextval
    ,(select SUBJECT_ID from SUBJECT where mem_id='song' and subject_name='python') 
    ,'song',sysdate, null
    );
    
-- 타이머 종료
-- 타이머는 한번에 하나만 활성화 가능 -> 가장 나중에 시작한 타이머를 멈추는 원리
UPDATE RECORD SET RECORD_END = SYSDATE WHERE RECORD_START = (SELECT MAX(RECORD_START)FROM RECORD WHERE RECORD_MEM_ID = 'song');


insert into record values(
    SEQ_RECORD_ID.nextval
    ,(select SUBJECT_ID from SUBJECT where mem_id='hyuk' and subject_name='C#') 
    ,'hyuk',sysdate
    , null
    );
UPDATE RECORD SET RECORD_END = SYSDATE WHERE RECORD_START = (SELECT MAX(RECORD_START)FROM RECORD WHERE RECORD_MEM_ID = 'hyuk');

insert into record values(
    SEQ_RECORD_ID.nextval
    ,(select SUBJECT_ID from SUBJECT where mem_id='hyuk' and subject_name='HTML') 
    ,'hyuk',sysdate
    , null
    );
UPDATE RECORD SET RECORD_END = SYSDATE WHERE RECORD_START = (SELECT MAX(RECORD_START)FROM RECORD WHERE RECORD_MEM_ID = 'hyuk');

-- GRUOP 더미

