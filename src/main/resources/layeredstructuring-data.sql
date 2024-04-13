drop table if exists users_roles;
drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists post cascade;
drop table if exists comment;


create table users (
	useraccount_id serial primary key,
	fullname varchar(255) not null,
	email varchar(255) not null unique,
	password varchar(255) not null
);
create table roles (
	role_id serial primary key,
	name varchar(50) not null
);
create table users_roles(
 fk_useraccount_id int,
 fk_role_id int,
foreign key (fk_useraccount_id) references users(useraccount_id),
foreign key (fk_role_id) references roles(role_id)
);
create table post (
	post_id serial primary key,
	posted_by int4,
	post_title varchar(255) not null,
	post_text text not null,
	post_summary varchar(255) not null,
	time_posted_epoch int8,
	time_updated_epoch int8
);
create table comment (
	comment_id serial primary key,
	guest_name varchar(255) not null,
	guest_email varchar(255) not null,
	comment_text text,
	time_posted_epoch bigint,
	time_updated_epoch bigint,
	post_id  int,
	foreign key (post_id) references  post(post_id)
);


insert into users (useraccount_id, fullname, email, password) values
(9999, 'admin', 'admin@admin.com', '$2a$10$k9644mshajjDvMhU8p76.u4sgOFuINZDkZ/csNgzFY99W1diZjBuC'),
(9998, 'blogger two', 'blogger@two.com', '$2a$10$8cXy77fgt6GMBtI/RciexeJR7rkWRX8Q0Bol2mqoVYXa2/zmoONNG'),
(9997, 'blogger three', 'blogger@three.com', '$2a$10$HLp3pNwYKgHwYfxpch3id.Tv2yDvUwDBXKGwh/e7xoKBdLPbJ8kHm'),
(9996, 'blogger four', 'blogger@four.com', '$2a$10$tVwU7jUwB36ug2goiHRVkeJxTy/CfLJ4NFttTf7z3o9JdiCbAoUdy'),
(9995, 'blogger five', 'blogger@five.com', '$2a$10$lYvneWkWNZnvaRWMtRZVkeSwTIWV5oUkNDDn9SaJtKr0ivDiURK8q');
insert into roles values (1, 'ADMIN'), (2, 'BLOGGER');
insert into users_roles(fk_useraccount_id, fk_role_id) values
(9999, 1), (9998, 2), (9997, 2), (9996, 2), (9995, 2);
insert into post(post_id,  posted_by, post_text, post_summary, post_title, time_posted_epoch, time_updated_epoch) values
(99990, 9999, 'post text 1', 'post summary 1', 'post title 1', 1669947792, 1669947792),
(99980, 9998, 'post text 2', 'post summary 2', 'post title 2', 1669947792, 1669947792),
(99970, 9997, 'post text 3', 'post summary 3', 'post title 3', 1669947792, 1669947792),
(99960, 9996, 'post text 4', 'post summary 4', 'post title 4', 1669947792, 1669947792),
(99950, 9995, 'post text 5', 'post summary 5', 'post title 5', 1669947792, 1669947792);
insert into comment values (55555, 'commenter1', 'commenter@one.com', 'comment text 1', 1669945592, 1669945592, 99990);
insert into comment values (55556, 'commenter2', 'commenter@two.com', 'comment text 2', 1669946692, 1669946692, 99980);
insert into comment values (55557, 'commenter3', 'commenter@three.com', 'comment text 3', 1669947792, 1669947792, 99970);
insert into comment values (55558, 'commenter2', 'commenter@two.com', 'comment text 4', 1669948892, 1669948892, 99960);
insert into comment values (55559, 'commenter1', 'commenter@one.com', 'comment text 5', 1669949992, 1669949992, 99950);


--select * from post;
--select * from comment;
--select * from users;
--select * from roles;
--select * from users_roles;