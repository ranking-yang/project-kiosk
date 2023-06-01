--INSERT
--Theme 행 INSERT    
INSERT INTO theme VALUES (1, 'NEW');
INSERT INTO theme VALUES (2, '(ICE)커피');
INSERT INTO theme VALUES (3, '(HOT)커피');
INSERT INTO theme VALUES (4, '디카페인');
INSERT INTO theme VALUES (5, '(TEA)티');
INSERT INTO theme VALUES (6, '스무디, 프라페');
INSERT INTO theme VALUES (7, '에이드');
INSERT INTO theme VALUES (8, '기타음료');
INSERT INTO theme VALUES (9, '디저트');
INSERT INTO theme VALUES (10, 'MD상품');

--MANAGER

INSERT INTO manager VALUES (100001, 'Mr_Yang', '010-1111-1111', '점장', 00001, '2023/02/27');
INSERT INTO manager VALUES (100002, 'Mr_Lee', '010-2222-2222', '직원', 00001, '2023/02/27');
INSERT INTO manager VALUES (100003, 'Mr_Hong', '010-3333-3333', '직원', 00001, '2023/02/28');
INSERT INTO manager VALUES (100004, 'Mr_Jeong', '010-4444-4444', '직원', 00001, '2023/02/28');
INSERT INTO manager VALUES (100005, 'Mr_Bae', '010-5555-5555', '직원', 00001, '2023/03/01');

--BRANCH
INSERT INTO branch VALUES (00001, 'Ezen Coffee구리점', '구리시 인창동', '이젠학원'); 

--LOGIN
INSERT INTO login VALUES('admin', '1234');

commit;