select * from users;

select id from users;

select count(distinct id) from users;

select count(id) from users;

select count(id) from users;

select count(* ) from book_borrow
where is_returned = 0;

select book_id, name from book_borrow bb inner join books b on bb.book_id = b.id
where book_id=847;

select * from book_categories;

select bc.name, count(*) from book_borrow bb
                                 inner join books b
                                            on bb.book_id = b.id
                                 inner join book_categories bc
                                            on b.book_category_id = bc.id
group by bc.name
order by count(*) desc;


select name, author, isbn from books
where name='Head First Java 01';