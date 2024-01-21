drop database if exists NaptuneBay;

Create database NaptuneBay;

Use NaptuneBay;

create table user(
	username varchar(15) primary key,
	password int not null
);


create table employee(
	empId varchar (10) primary key,
	name varchar (10) not null,
	address varchar (10) not null,
	tel varchar(20) not null,
	username varchar (15) ,
	foreign key (username) references user (username) on delete cascade on update cascade
);


create table salary(
	salaryId varchar (10) primary key,
    amount decimal not null,
    month varchar (10) not null,
    empId varchar (10) ,
	foreign key (empId) references employee (empId) on delete cascade on update cascade
);


Create table attendance(
               attendanceId varchar (10) primary key,
               date date not null,
               count int not null,
               empId varchar (10),
	foreign key (empId) references employee (empId) on delete cascade on update cascade
);


create table customer(
	custId varchar(5) primary key,
	name varchar(25) not null,
	address varchar(50) not null,
	tel int not null,
	username varchar(15) ,
	foreign key(username) references user(username) on delete cascade on update cascade
);


create table orders(
	orderId varchar(5) primary key,
	name varchar (10) not null,
	date date not null,
    details varchar (10) not null,
	custId varchar(5) ,
	foreign key(custId) references customer(custId) on delete cascade on update cascade
);


create table menu_item(
               itemCode varchar (5) primary key,
               name varchar (10) not null,
               price varchar(10) not null,
               orderId varchar(5) ,
               foreign key(orderId) references orders(orderId) on delete cascade on update cascade
);


create table order_detail(
	orderId varchar(5) not null,
    itemCode varchar (5) not null,
	date date not null,
	foreign key(orderId) references orders(orderId) on delete cascade on update cascade,
               foreign key(itemCode) references menu_item(itemCode) on delete cascade on update cascade
);


create table payment(
               payId varchar (10) primary key,
               date date not null,
               amount varchar (10)  not null,
               orderId varchar(5),
               foreign key(orderId) references orders(orderId) on delete cascade on update cascade
);


create table suppliers(
               supId varchar (10) primary key,
               supName varchar (10) not null,
               address varchar (10) not null,
               tel varchar(20) not null,
               username varchar (15) ,
	foreign key(username) references user(username) on delete cascade on update cascade
);


create table suppliers_order(
               supItemId varchar (10) primary key,
               name varchar (10) not null,
               date date not null,
               time time not null,
               supId varchar (10) ,
               foreign key(supId) references suppliers(supId) on delete cascade on update cascade
);


create table stocks(
               code varchar (10) primary key,
               description varchar (10) not null,
               unitPrice varchar (10) not null,
               qtyOnHand varchar (10) not null,
               supItemId varchar (10),
               foreign key(supItemId) references suppliers_order(supItemId) on delete cascade on update cascade
);


create table suppliers_order_detail(
               supItemId varchar (10) ,
               code varchar (10) ,
               qty int not null,
               foreign key(supItemId) references suppliers_order(supItemId) on delete cascade on update cascade,
               foreign key(code) references stocks(code) on delete cascade on update cascade
);


