/* Electricity Billing System */

create database ebs;
use ebs;
create table login(meter_no varchar(10), username varchar(20), name varchar(50), password varchar(30), usertype varchar(20));
describe login;
select * from login;
create table customer(name varchar(50), meter_no varchar(20), address varchar(200), city varchar(30), state varchar(30), email varchar(60), phone varchar(20));
describe customer;
select * from customer;
create table meter_info(meter_no varchar(20), meter_location varchar(30), meter_type varchar(20), phase_code varchar(10), bill_type varchar(20), days varchar(10));
describe meter_info;
select * from meter_info;
create table tax(cost_per_unit varchar(20), meter_rent varchar(20), service_charge varchar(20), service_tax varchar(20), swacch_bharat_cess varchar(20), fixed_tax varchar(20));
insert into tax values("9", "47", "22", "57", "6", "18");
select * from tax;
create table bill(meter_no varchar(30), month varchar(20), units varchar(20), totalbill varchar(20), status varchar(20));
select * from bill;