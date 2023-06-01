-- 프로젝트 테이블

-- 테마 theme  약축 - tm
CREATE TABLE Theme (
    tm_id NUMBER(5) 
        CONSTRAINT tm_id_pk PRIMARY KEY,
    tm_name VARCHAR2(50)    
        CONSTRAINT tm_name_nn NOT NULL
);

-- 제품  약축 -pd
CREATE TABLE product(
        pd_id NUMBER(4)
            CONSTRAINT pd_id_pk PRIMARY KEY,
        pd_name VARCHAR2(60) 
            CONSTRAINT pd_name_nn NOT NULL
            CONSTRAINT pd_name_uk UNIQUE,
        pd_price NUMBER(6)
            CONSTRAINT pd_price_nn NOT NULL
            CONSTRAINT pd_price_chk CHECK(pd_price >= 0),
        pd_thumbnail VARCHAR2(500) 
            CONSTRAINT pd_thumnail_nn NOT NULL
	CONSTRAINT pd_thumnail_chk CHECK (LOWER(pd_thumbnail) LIKE '%.jpg' OR LOWER(pd_thumbnail) LIKE '%.png'),
        tm_id NUMBER(5) 
            CONSTRAINT tm_id_fk REFERENCES Theme(tm_id)
               ON DELETE CASCADE,
        pd_shot CHAR(1)
            CONSTRAINT pd_shot_chk CHECK(pd_shot IN ('Y', 'N')),
        pd_milk  CHAR(1) 
            CONSTRAINT pd_milk_chk CHECK(pd_milk IN ('Y', 'N')),
        pd_ice  CHAR(1)
            CONSTRAINT pd_ice_chk CHECK(pd_ice IN ('Y', 'N')),
        pd_stevia  CHAR(1)
            CONSTRAINT pd_stevia_chk CHECK(pd_stevia IN ('Y', 'N')),
        pd_cream CHAR(1)
            CONSTRAINT pd_cream_chk CHECK(pd_cream IN ('Y', 'N'))
);

-- 옵션  약축 - op
CREATE TABLE personal_option(
    op_id CHAR(5) 
        CONSTRAINT op_id_pk PRIMARY KEY,  
    op_shot NUMBER(1)
        CONSTRAINT op_shot_chk CHECK(op_shot BETWEEN 0 AND 2),
    op_milk NUMBER(1) 
        CONSTRAINT op_milk_chk CHECK(op_milk BETWEEN 0 AND 2),
    op_ice NUMBER(1) 
        CONSTRAINT op_ice_chk CHECK(op_ice BETWEEN 0 AND 2),
    op_stevia NUMBER(1)
        CONSTRAINT op_stevia_chk CHECK(op_stevia BETWEEN 0 AND 1),
    op_cream NUMBER(1)
        CONSTRAINT op_cream_chk CHECK(op_cream BETWEEN 0 AND 2),
    add_price NUMBER(5) DEFAULT 0
        CONSTRAINT add_price_chk CHECK(add_price >= 0)
);

--결제  - 약축 -pay
CREATE TABLE payment(
    pay_id NUMBER(5)
        CONSTRAINT pay_id_pk PRIMARY KEY,
    pay_way VARCHAR2(30)
        CONSTRAINT pay_way_nn NOT NULL
        CONSTRAINT pay_way_uk UNIQUE
);
    
-- 판매데이터  약축 - sd
CREATE TABLE sellingdata (
    sd_id NUMBER(8) 
        CONSTRAINT sd_id_pk PRIMARY KEY,
    sd_time DATE DEFAULT sysdate
        CONSTRAINT sd_time_nn NOT NULL,
    pay_id NUMBER(5)
        CONSTRAINT pay_id_fk REFERENCES payment(pay_id),
    bc_id NUMBER(5) 
        CONSTRAINT bc_id_fk REFERENCES branch(bc_id),
    pick_up NUMBER(1)
        CONSTRAINT pick_up_chk CHECK (pick_up BETWEEN 0 AND 1)
        CONSTRAINT pick_up_nn NOT NULL
);

CREATE SEQUENCE sd_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 99999999
    CYCLE
    NOCACHE;

-- 선택목록  약축- alist_ 이름변경
CREATE TABLE order_list (
    alist_id NUMBER(10)
        CONSTRAINT alist_id_pk PRIMARY KEY,
    pd_id NUMBER(5)
        CONSTRAINT pd_id_fk REFERENCES product(pd_id)
        ON DELETE SET NULL,
    alist_count NUMBER(3)
        CONSTRAINT alist_count_chk CHECK (alist_count >= 0),
    al_price NUMBER(7)
        CONSTRAINT al_price_nn NOT NULL
        CONSTRAINT al_price_chk CHECK (al_price >= 0),
    sd_id NUMBER(8)
        CONSTRAINT sd_id_nn NOT NULL,
        CONSTRAINT sd_id_fk REFERENCES sellingdata(sd_id), 
    op_id CHAR(5) 
        CONSTRAINT op_id_fk REFERENCES personal_option(op_id),
    pd_name VARCHAR2(60)
        CONSTRAINT pd_name_nn NOT NULL    	
);

CREATE SEQUENCE alist_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999999999
    CYCLE
    NOCACHE;

-- 지점  약축 - bc
CREATE TABLE branch(
    bc_id NUMBER(5)
        CONSTRAINT bc_id_pk PRIMARY KEY,
    bc_name VARCHAR2(50)
        CONSTRAINT bc_name_nn NOT NULL,
    bc_location VARCHAR2(500) 
        CONSTRAINT bc_location_nn NOT NULL,
    bc_chief VARCHAR2(20) 
        CONSTRAINT bc_chief_nn NOT NULL
);

-- 매니저 약축 - mg
CREATE TABLE manager(
    mg_id NUMBER(6) 
        CONSTRAINT mg_id_pk PRIMARY KEY,
    mg_name VARCHAR2(30)
        CONSTRAINT mg_name_nn NOT NULL,
    mg_phone CHAR(13) 
        CONSTRAINT mg_phone CHECK(REGEXP_LIKE(mg_phone, '(0[0-9]{2})-([0-9]{3,4})-([0-9]{4})')),
    mg_position VARCHAR2(30)
        CONSTRAINT mg_position_nn NOT NULL,
    bc_id NUMBER(5)
        CONSTRAINT bc_id REFERENCES branch(bc_id),
    mg_hire_date DATE 
        CONSTRAINT mg_hire_date_nn NOT NULL
);

-- 쿠폰
CREATE TABLE coupon (
    phone_number VARCHAR2(11) 
        CONSTRAINT phone_number_pk PRIMARY KEY
    coupon_count NUMBER(2) DEFAULT 0
        CONSTRAINT coupon_count_chk CHECK(coupon_count >= 0),
    stamp_count NUMBER(3) DEFAULT 0
        CONSTRAINT stamp_count_chk CHECK(stamp_count >= 0)
);

--로그인
CREATE TABLE login(
     login_id VARCHAR2(30)
        CONSTRAINT login_id_pk PRIMARY KEY,
     login_pw VARCHAR2(30) 
        CONSTRAINT login_pw_nn NOT NULL
);




