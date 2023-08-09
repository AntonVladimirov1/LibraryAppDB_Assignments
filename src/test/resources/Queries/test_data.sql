-- US01-1
select count(id) from users; -- actual

select count(distinct id) from users; -- expected
-- US01-2
select * from users;

-- US02
select count(*)from book_borrow where is_returned=0;