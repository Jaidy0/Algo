# -- 코드를 작성해주세요
SELECT t.ITEM_ID, i.ITEM_NAME, i.RARITY 
FROM ITEM_INFO i, ITEM_TREE t
WHERE i.ITEM_ID = t.ITEM_ID AND t.PARENT_ITEM_ID IN (
SELECT i.ITEM_ID 
-- 자식을 뽑아야 한다.
FROM ITEM_INFO i, ITEM_TREE t
WHERE i.ITEM_ID = t.ITEM_ID AND RARITY = 'RARE')
ORDER BY t.ITEM_ID DESC;

