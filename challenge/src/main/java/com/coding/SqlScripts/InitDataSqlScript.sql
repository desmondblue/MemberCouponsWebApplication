
drop database if exists Member_Coupon_Database;
create database Member_Coupon_Database;

start transaction;

create schema if not exists member_coupons;
create  table if not exists member_coupons.member_details(
member_id serial primary key ,
member_name varchar,
member_address varchar
);
create table if not exists member_coupons.coupon_details(
coupon_id serial primary key,
coupon_name varchar,
coupon_description varchar,
valid_from date not null,
valid_until date not null,
latitude  float8,
longitude float8
);
create table if not exists member_coupons.member_coupon_mappings(
member_id integer,
coupon_id integer,
foreign key(member_id) references member_coupons.member_details(member_id),
foreign key(coupon_id) references member_coupons.coupon_details(coupon_id)
);
insert into member_coupons.member_details (member_name,member_address) values
('Jimmy Hoffa','Miami,Florida'),
('Frank Murphy','Berln,Germany'),
('Bill Buffalino','Sicily,Italy');
insert into member_coupons.coupon_details (coupon_name,coupon_description,latitude,longitude,valid_from,valid_until) values
('Flat 30','Flat 30% off for purchases more than EUR 2000','87.55','70.55','2022-02-21','2022-07-19'),
('BOGO','Buy One Get One with your purchase','90.55','-40.55','2022-01-01','2022-10-30'),
('Buy 3 Get One Free','Buy 3 products and get 1 more free of cost','110.55','72.89','2022-02-01','2022-06-25'),
('Buy 4 Get One Free','Buy 3 products and get 1 more free of cost','110.55','72.89','2022-02-11','2022-03-25');
insert into member_coupons.member_coupon_mappings values(1,2),(1,3),(2,2),(3,1),(3,3),(2,4),(3,4),(2,1);
create view member_coupon_map_view as select member.member_id,coupon.coupon_name,coupon.coupon_description,coupon.valid_from,coupon.valid_until,coupon.longitude,coupon.latitude from member_coupons.coupon_details coupon join member_coupons.member_coupon_mappings mem_cp_map on coupon.coupon_id = mem_cp_map.coupon_id join member_coupons.member_details member on mem_cp_map.member_id = member.member_id where CURRENT_DATE between coupon.valid_from and coupon.valid_until order by coupon.valid_until desc;
commit;