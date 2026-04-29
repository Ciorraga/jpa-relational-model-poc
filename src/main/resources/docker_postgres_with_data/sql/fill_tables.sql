-- Dataset sizing
set session my.number_of_sales = '2500';
set session my.number_of_users = '350';
set session my.number_of_products = '180';
set session my.number_of_stores = '80';
set session my.number_of_countries = '12';
set session my.number_of_cities = '36';
set session my.start_date = '2024-01-01 00:00:00';
set session my.end_date = '2025-12-31 23:59:59';

-- Load the pgcrypto extension to generate UUIDs
create extension if not exists pgcrypto;

-- Products
insert into product (product_id, name)
select id,
	case ((id - 1) % 12)
		when 0 then 'Wireless Headphones'
		when 1 then 'Mechanical Keyboard'
		when 2 then 'Gaming Mouse'
		when 3 then '4K Monitor'
		when 4 then 'USB-C Docking Station'
		when 5 then 'Portable SSD'
		when 6 then 'Smart Speaker'
		when 7 then 'Ergonomic Chair'
		when 8 then 'Laptop Stand'
		when 9 then 'Webcam Pro'
		when 10 then 'Noise Cancelling Earbuds'
		else 'Fitness Smartwatch'
	end || ' ' || lpad(id::text, 4, '0')
from generate_series(1, current_setting('my.number_of_products')::int) as id;

-- Countries
insert into country (country_id, country_name)
select id, country_name
from (
	values
		(1, 'Spain'),
		(2, 'Portugal'),
		(3, 'France'),
		(4, 'Italy'),
		(5, 'Germany'),
		(6, 'Netherlands'),
		(7, 'Belgium'),
		(8, 'United Kingdom'),
		(9, 'Ireland'),
		(10, 'Sweden'),
		(11, 'Denmark'),
		(12, 'Norway')
) as countries(id, country_name)
where id <= current_setting('my.number_of_countries')::int;

-- Cities
insert into city (city_id, city_name, country_id)
select id, city_name, country_id
from (
	values
		(1, 'Madrid', 1),
		(2, 'Barcelona', 1),
		(3, 'Valencia', 1),
		(4, 'Seville', 1),
		(5, 'Lisbon', 2),
		(6, 'Porto', 2),
		(7, 'Paris', 3),
		(8, 'Lyon', 3),
		(9, 'Marseille', 3),
		(10, 'Milan', 4),
		(11, 'Rome', 4),
		(12, 'Turin', 4),
		(13, 'Berlin', 5),
		(14, 'Munich', 5),
		(15, 'Hamburg', 5),
		(16, 'Amsterdam', 6),
		(17, 'Rotterdam', 6),
		(18, 'Utrecht', 6),
		(19, 'Brussels', 7),
		(20, 'Antwerp', 7),
		(21, 'Ghent', 7),
		(22, 'London', 8),
		(23, 'Manchester', 8),
		(24, 'Birmingham', 8),
		(25, 'Dublin', 9),
		(26, 'Cork', 9),
		(27, 'Galway', 9),
		(28, 'Stockholm', 10),
		(29, 'Gothenburg', 10),
		(30, 'Malmo', 10),
		(31, 'Copenhagen', 11),
		(32, 'Aarhus', 11),
		(33, 'Odense', 11),
		(34, 'Oslo', 12),
		(35, 'Bergen', 12),
		(36, 'Trondheim', 12)
) as cities(id, city_name, country_id)
where id <= current_setting('my.number_of_cities')::int;

-- Stores
insert into store (store_id, name, city_id)
select id,
	(case ((id - 1) % 8)
		when 0 then 'Downtown'
		when 1 then 'Central'
		when 2 then 'Riverside'
		when 3 then 'North Point'
		when 4 then 'South Gate'
		when 5 then 'Tech Plaza'
		when 6 then 'Market Square'
		else 'Harbor'
	end) || ' Store ' || lpad(id::text, 3, '0'),
	1 + ((id - 1) % current_setting('my.number_of_cities')::int)
from generate_series(1, current_setting('my.number_of_stores')::int) as id;

-- Users
insert into users (user_id, name)
select id,
	first_names[1 + floor(random() * array_length(first_names, 1))::int] || ' ' ||
	last_names[1 + floor(random() * array_length(last_names, 1))::int]
from generate_series(1, current_setting('my.number_of_users')::int) as id
cross join (
	select
		array[
			'Lucas', 'Emma', 'Noah', 'Olivia', 'Liam', 'Sofia', 'Mateo', 'Julia',
			'Hugo', 'Marta', 'Leo', 'Clara', 'Daniel', 'Nora', 'Pablo', 'Elena',
			'Adrian', 'Irene', 'Marco', 'Sara'
		] as first_names,
		array[
			'Garcia', 'Martinez', 'Lopez', 'Sanchez', 'Perez', 'Fernandez', 'Gomez',
			'Diaz', 'Romero', 'Navarro', 'Ruiz', 'Torres', 'Vega', 'Castro',
			'Molina', 'Ortega', 'Delgado', 'Santos', 'Iglesias', 'Cruz'
		] as last_names
) as name_parts;

-- Status names
insert into status_name (status_name_id, status_name)
values
	(1, 'CREATED'),
	(2, 'PAID'),
	(3, 'PACKED'),
	(4, 'SHIPPED'),
	(5, 'DELIVERED'),
	(6, 'CANCELLED');

-- Sales
insert into sale (sale_id, amount, date_sale, product_id, user_id, store_id)
select gen_random_uuid()::text,
	round((20 + random() * 2480)::numeric, 2),
	to_timestamp(start_date, 'YYYY-MM-DD HH24:MI:SS') +
		random() * (
			to_timestamp(end_date, 'YYYY-MM-DD HH24:MI:SS') -
			to_timestamp(start_date, 'YYYY-MM-DD HH24:MI:SS')
		),
	1 + floor(random() * current_setting('my.number_of_products')::int)::int,
	1 + floor(random() * current_setting('my.number_of_users')::int)::int,
	1 + floor(random() * current_setting('my.number_of_stores')::int)::int
from generate_series(1, current_setting('my.number_of_sales')::int) as id
cross join current_setting('my.start_date') as start_date
cross join current_setting('my.end_date') as end_date;

-- Order status history
with sale_status_plan as (
	select
		sale_id,
		date_sale,
		case
			when random() < 0.12 then 2 + floor(random() * 2)::int
			else 4 + floor(random() * 2)::int
		end as steps,
		random() < 0.12 as cancelled
	from sale
)
insert into order_status (order_status_id, update_at, sale_id, status_name_id)
select
	gen_random_uuid()::text,
	date_sale +
		(step_no - 1) * interval '10 hours' +
		random() * interval '2 hours',
	sale_id,
	case
		when step_no = 1 then 1
		when step_no = 2 then 2
		when step_no = 3 then 3
		when cancelled and step_no = steps then 6
		when step_no = 4 then 4
		else 5
	end
from sale_status_plan
cross join lateral generate_series(1, steps) as step_no;
