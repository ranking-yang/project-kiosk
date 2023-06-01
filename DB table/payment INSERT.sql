--payment
INSERT INTO payment VALUES(37730,'카카오페이');
INSERT INTO payment VALUES(13561,'네이버페이');
INSERT INTO payment VALUES(12481,'삼성페이');
INSERT INTO payment VALUES(12345,'카드결제');
INSERT INTO payment VALUES(11111, '쿠폰결제'); 
COMMIT;

--Theme 행 INSERT    
INSERT INTO theme VALUES (1, '(ICE)커피');
INSERT INTO theme VALUES (2, '(HOT)커피');
INSERT INTO theme VALUES (3, '(ICE)디카페인');
INSERT INTO theme VALUES (4, '(HOT)디카페인');
INSERT INTO theme VALUES (5, '스무디');
INSERT INTO theme VALUES (6, '에이드');
INSERT INTO theme VALUES (7, '(ICE)티');
INSERT INTO theme VALUES (8, '(HOT)티');
INSERT INTO theme VALUES (9, '디저트');

--ICE 커피
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)아메리카노', 1500, 'images/products/ice_Americano.jpg', 1,'Y', 'N', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)라떼', 1500, 'images/products/ice_Caffe Latte.jpg', 1,'Y', 'Y', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)카페모카', 2000, 'images/products/ice_Caffe Mocha.jpg', 1,'Y', 'Y', 'Y', 'N','Y'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)카푸치노', 2000, 'images/products/ice_Cappuccino.jpg', 1,'Y', 'Y', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)헤이즐넛 라떼', 2000, 'images/products/ice_Hazelnut Latte.jpg', 1,'Y', 'Y', 'Y', 'Y','N'
    );

-- HOT 커피    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)아메리카노', 1500, 'images/products/hot_Americano.jpg', 2, 'Y', 'N', 'N', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)라떼', 1500, 'images/products/hot_Caffe Latte.jpg', 2, 'Y', 'Y', 'N', 'Y','N'
    );    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)카페모카', 2000, 'images/products/hot_Caffe Mocha.jpg', 2, 'Y', 'Y', 'N', 'N','Y'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)카푸치노', 2000, 'images/products/hot_Cappuccino.jpg', 2, 'Y', 'Y', 'N', 'Y','N'
    );    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)헤이즐넛 라떼', 2000, 'images/products/hot_Hazelnut Latte.jpg', 2, 'Y', 'Y', 'N', 'Y','N'
    );

-- 디카페인커피    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)디카페인 아메리카노', 2000, 'images/products/ice_Decaf Americano.jpg', 3,'Y', 'N', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)디카페인 라떼', 2000, 'images/products/ice_Decaf Cafe Latte.jpg', 3,'Y', 'Y', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)디카페인 카페모카', 2500, 'images/products/ice_Decaf Cafe Mocha.jpg', 3,'Y', 'Y', 'Y', 'N','Y'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)디카페인 카푸치노', 2500, 'images/products/ice_Decaf Cappuccino.jpg', 3,'Y', 'Y', 'Y', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)디카페인 헤이즐넛 라떼', 2500, 'images/products/ice_Decaf Hazelnut Latte.jpg', 3,'Y', 'Y', 'Y', 'Y','N'
    );    
        
-- hot 디카페인
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)디카페인 아메리카노', 2000, 'images/products/hot_Decaf Americano.jpg', 4, 'Y', 'N', 'N', 'Y','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)디카페인 라떼', 2000, 'images/products/hot_Decaf Cafe Latte.jpg', 4, 'Y', 'Y', 'N', 'Y','N'
    );    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)디카페인 카페모카', 2500, 'images/products/hot_Decaf Cafe Mocha.jpg', 4, 'Y', 'Y', 'N', 'N','Y'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)디카페인 카푸치노', 2500, 'images/products/hot_Decaf Cappuccino.jpg', 4, 'Y', 'Y', 'N', 'Y','N'
    );    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)디카페인 헤이즐넛 라떼', 2500, 'images/products/hot_Decaf Hazelnut Americano.jpg', 4, 'Y', 'Y', 'N', 'Y','N'
    );

--스무디    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '딸기요거트스무디', 3500, 'images/products/_Strawberry Yogurt Smoothie.jpg', 5, 'N', 'N', 'N', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '망고요거트스무디', 3500, 'images/products/_Mango Yogurt Smoothie.jpg', 5, 'N', 'N', 'N', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '플레인요거트스무디', 3500, 'images/products/_Plain Yogurt Smoothie.jpg', 5, 'N', 'N', 'N', 'N','N'
    );  
  
--에이드 
INSERT INTO product VALUES (
    pd_id_seq.nextval, '레몬에이드', 2500, 'images/products/_Lemon Ade.jpg', 6, 'N', 'N', 'Y', 'N','N'
    );    
INSERT INTO product VALUES (
    pd_id_seq.nextval, '블루레몬에이드', 2500, 'images/products/_Blue Lemon Ade.jpg', 6, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '자몽에이드', 3000, 'images/products/_Grapefruit Ade.jpg', 6, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '청포도에이드', 3000, 'images/products/_Green Grape Ade.jpg', 6, 'N', 'N', 'Y', 'N','N'
    ); 

--ICE 티
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)녹차', 2000, 'images/products/ice_Green Tea.jpg', 7, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)얼그레이', 2000, 'images/products/ice_Earl Grey.jpg', 7, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)캐모마일', 2000, 'images/products/ice_Chamomile.jpg', 7, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)페퍼민트', 2000, 'images/products/ice_Peppermint Tea.jpg', 7, 'N', 'N', 'Y', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(ICE)유자차', 2000, 'images/products/ice_Citron Tea.jpg', 7, 'N', 'N', 'Y', 'N','N'
    );

--HOT 티   
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)녹차', 2000, 'images/products/hot_Green Tea.jpg', 8, 'N', 'N', 'N', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)얼그레이', 2000, 'images/products/hot_Earl Grey.jpg', 8, 'N', 'N', 'N', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)캐모마일', 2000, 'images/products/hot_Chamomile.jpg', 8, 'N', 'N', 'N', 'N','N'
    ); 
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)페퍼민트', 2000, 'images/products/hot_Peppermint Tea.jpg', 8, 'N', 'N', 'N', 'N','N'
    ); 
INSERT INTO product VALUES (
    pd_id_seq.nextval, '(HOT)유자차', 2000, 'images/products/hot_Citron Tea.jpg', 8, 'N', 'N', 'N', 'N','N'
    ); 

-- 디저트
INSERT INTO product VALUES (
    pd_id_seq.nextval, '치즈케이크', 5000, 'images/products/_Cheese Cake.jpg', 9, 'N', 'N', 'N', 'N','N'
    ); 
INSERT INTO product VALUES (
    pd_id_seq.nextval, '초코케이크', 5000, 'images/products/_Chcholate Mousse Cake.jpg', 9, 'N', 'N', 'N', 'N','N'
    );
INSERT INTO product VALUES (
    pd_id_seq.nextval, '허니브레드', 5500, 'images/products/_Honey Bread.jpg', 9, 'N', 'N', 'N', 'N','N'
    );

    
commit;