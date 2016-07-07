-- Query 1:
--     Get daily sale

SELECT orderDate,
       sum(quantity*price) AS dailySale
FROM `Order`,
     ProductOrder
WHERE `Order`.idOrder = ProductOrder.idOrder
GROUP BY orderDate;

-- Query 2:
--     Get daily profit

SELECT orderDate,
       sum(ProductOrder.quantity*price) AS dailySale,
       sum(ProductOrder.quantity * buyPrice) AS dailyCostPrice,
       sum(ProductOrder.quantity * price) - sum(ProductOrder.quantity * buyPrice) AS dailyProfit
FROM `Order`,
     ProductOrder,
     Product
WHERE `Order`.idOrder = ProductOrder.idOrder
    AND ProductOrder.idProduct = Product.idProduct
GROUP BY orderDate;

-- Query 3:
--     Get average user order quantity

SELECT `User`.customerName,
       avg(ProductOrder.quantity) AS averageQuantity
FROM `User`,
     ProductOrder,
     `Order`
WHERE `User`.idUser = `Order`.idUser
    AND ProductOrder.idOrder = `Order`.idOrder
GROUP BY `User`.idUser
ORDER BY `User`.customerName ASC;

-- Query 4:
--     Drop a Product
--
--     Since deleting from Product table is not advisable due to it being
--     referenced by other tables, we add a `flag` column to the table

ALTER TABLE `Product` ADD COLUMN flag boolean DEFAULT TRUE;


UPDATE `Product`
SET flag = TRUE;

--
--     Then we simply mark the flag as FALSE to effectively delete it

UPDATE `Product`
SET flag = FALSE
WHERE idProduct = "S10_1678";

-- Query 5: Fulfill an order
--      Given the userId, productId, quantity, price (selling price)
--      first we fix the idOrder field to make it auto-increment
--      (tested properly on a copy of the table)
        --  CREATE TABLE BackupOrder LIKE Order;
        --  INSERT BackupOrder
        --  SELECT *
        --  FROM Order;
        --  ALTER TABLE `BackupOrder` MODIFY idOrder INT NOT NULL auto_increment;
SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE `Order` MODIFY idOrder INT NOT NULL auto_increment;
SET FOREIGN_KEY_CHECKS = 1;

-- Confirming whether the schema modification took effect or not
SHOW
CREATE TABLE `Order`;

-- Then we need to insert the order into the order table
START TRANSACTION;
INSERT INTO
`ORDER` (`idUser`,
       `status`,
       `orderDate`)
VALUES ("1",
        "Testing",
        now())-- Then we need to insert into the ProductOrder table

INSERT INTO ProductOrder (`idOrder`, `quantity`, `price`, `idProduct`)
VALUES(last_insert_id(),
       1,
       100,
       'S10_1678')
COMMIT;

-- Then we check if the insert took place or not
SELECT *
FROM
ORDER,
     ProductOrder
WHERE `Order`.idOrder = ProductOrder.idOrder
    AND `Order`.idOrder = last_insert_id();
