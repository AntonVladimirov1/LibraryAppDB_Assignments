-- US01-1
select count(id) from users; -- actual

select count(distinct id) from users; -- expected
-- US01-2
select * from users;

-- US02
select count(*)from book_borrow where is_returned=0;

-- US03
select name from book_categories;

-- US04
select * from books where name='Antonio Banderas' ;
-- book category
select bc.name from books b join book_categories bc on b.book_category_id=bc.id where b.name='Antonio Banderas';