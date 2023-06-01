CREATE TRIGGER trg_product_id_update
AFTER UPDATE OF pd_id ON product FOR EACH ROW
BEGIN
    UPDATE order_list
    SET pd_id = :NEW.pd_id
    WHERE pd_id = :OLD.pd_id;
END;



