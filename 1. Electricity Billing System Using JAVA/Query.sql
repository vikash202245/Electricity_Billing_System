create database ebs1;

use ebs1;

create table login(meter_no varchar(20), userName varchar(30), name varchar(30), password varchar(30), user varchar(30));
describe login;
select * from login;
SET SQL_SAFE_UPDATES = 0;
select * from login where  meter_no='957261';
delete from login where  meter_no='898024';

create table customer(name varchar(30), meter_no varchar(20), address varchar(40), city varchar(30), state varchar(30),email varchar(40), phone varchar(10));
describe customer;
select * from customer;
select *from customer where meter_no='957261';

create table meter_info(meter_no varchar(20), meter_location varchar(20), meter_type varchar(20), phase_code varchar(20), bill_type varchar(20),days varchar(20));
describe meter_info;
select * from meter_info;

create table tax(cost_per_unit varchar(20),meter_rent varchar(20),service_charge varchar(20),service_tax varchar(20),swachchh_bharat_cess varchar(20),fixed_tax varchar(20));
insert into tax values('9','47','22','57','6','18');
select * from tax;

create table bill(meter_no varchar(20),month varchar(20),units varchar(30),totalamount varchar(30),status varchar(30));
select* from bill;
drop table bill;
