drop database if exists lackluster_video;
create database lackluster_video;

use lackluster_video;
drop table if exists order_line_items;
drop table if exists orders;
drop table if exists rentals;
drop table if exists customers;
drop table if exists employees;
create table employees (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
active_store_number VARCHAR(30) NOT NULL
);

create table customers (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
smart_id VARCHAR(30) NOT NULL UNIQUE,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL
);

create table rentals (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
rental_type VARCHAR(30) NOT NULL,
category VARCHAR(30) NOT NULL,
year VARCHAR(4) NOT NULL,
director VARCHAR(60),
num_available INT(9) UNSIGNED
);

create table orders (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
employee_id INT(9) UNSIGNED NOT NULL,
customer_id INT(9) UNSIGNED NOT NULL,
store_number VARCHAR(30) NOT NULL
);

create table order_line_items (
id INT(9) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
order_id INT(9) UNSIGNED NOT NULL,
rental_id INT(9) UNSIGNED NOT NULL
);

alter table orders add FOREIGN KEY (employee_id) REFERENCES employees(id);
alter table orders add FOREIGN KEY (customer_id) REFERENCES customers(id);
alter table order_line_items add FOREIGN KEY (order_id) REFERENCES orders(id);
alter table order_line_items add FOREIGN KEY (rental_id) REFERENCES rentals(id);

insert into employees (first_name, last_name, active_store_number) values ('Wynter', 'Miller', '39458');
insert into employees (first_name, last_name, active_store_number) values ('Max', 'Daniels', '39123');
insert into employees (first_name, last_name, active_store_number) values ('Bob', 'Deal', '39458');

insert into customers (first_name, last_name, smart_id) values ('Marlon', 'Hames', '239458010408');
insert into customers (first_name, last_name, smart_id) values ('Liz', 'Dibbert', '23945808497');
insert into customers (first_name, last_name, smart_id) values ('Ladonna', 'Lueilwitz', '23945804444');
insert into customers (first_name, last_name, smart_id) values ('Leon', 'Conroy', '23912310000');

insert into rentals(name, rental_type, category, year, director, num_available) values ('The Shawshank Redemption', 'dvd', 'drama', 1994, 'Frank Darabont', 15);
insert into rentals(name, rental_type, category, year, director, num_available) values ('Star Wars: A New Hope', 'dvd', 'adventure', 1976, 'George Lucas', 3);
insert into rentals(name, rental_type, category, year, director, num_available) values ('The Godfather', 'bluray', 'drama', 1972, 'Francis Ford Coppola', 6);
insert into rentals(name, rental_type, category, year, director, num_available) values ('The Lord of the Rings: The Return of the King', 'dvd', 'fantasy', 2003, 'Peter Jackson', 12);
