--select 'drop table if exists "' || tablename || '" cascade;' 
--  from pg_tables
-- where schemaname = 'public'; -- or any other schema


create table if not exists customers(
  id serial PRIMARY KEY,
  username varchar(30),
  password varchar(30)
);

create table if not exists employees(
  id serial PRIMARY KEY,
  username varchar(30),
  password varchar(30)
);
-- https://www.postgresql.org/docs/13/datatype-numeric.html
create table if not exists accounts(
  id serial PRIMARY KEY,
  status character(1),
  balance NUMERIC(10, 2)
);

insert into
  accounts(balance, status)
values
  (999999, 'o');

create table if not exists applications(
    id serial PRIMARY KEY,
    intial_amount NUMERIC(10, 2),
    status character(1)
  );

create table if not exists customeraccounts(
    customer_id integer REFERENCES customers (id),
    account_id integer REFERENCES accounts (id),
    PRIMARY KEY (customer_id, account_id)
  );

create table if not exists transactions(
    id serial PRIMARY KEY,
    source_account_id integer references accounts (id),
    destination_account_id integer references accounts (id),
    amount NUMERIC(10, 2)
  );

create table if not exists logs(
    id serial PRIMARY KEY,
    entry_date timestamp,
    level varchar(100),
    logger varchar(100),
    message text
  );

create table if not exists customerapplications(
    customer_id integer REFERENCES customers (id),
    application_id integer REFERENCES applications (id),
    PRIMARY KEY (customer_id, application_id)
  );


create or replace function add(init NUMERIC(10, 2), amount NUMERIC(10, 2))
returns NUMERIC(10, 2)
language plpgsql
as
$$
declare
total NUMERIC(10, 2);
begin
	select ( $1 + $2) into total;
	return total;
end
$$
;
--
create or replace function new_withdrawl(i integer, amount_arg NUMERIC(10, 2)) returns boolean
language plpgsql
as
$$
declare
base NUMERIC(10, 2);
new_amount NUMERIC(10, 2);

begin
	insert into transactions (source_account_id,destination_account_id,amount) values ($1,0,$2);
	update accounts ac set balance = (select add(balance,amount_arg )) where i = ac.id;
return true;
end
$$
;
--
create or replace function new_deposit(i integer, amount_arg NUMERIC(10, 2)) returns boolean
language plpgsql
as
$$
declare
base NUMERIC(10, 2);
new_amount NUMERIC(10, 2);

begin
	insert into transactions (source_account_id,destination_account_id,amount) values (0,$1,$2);
	update accounts ac set balance = (select add(balance,$2 )) where $1 = ac.id;
return true;
end
$$
;
--
create or replace function subtract(init NUMERIC(10, 2), amount NUMERIC(10, 2))
returns NUMERIC(10, 2)
language plpgsql
as
$$
declare
total NUMERIC(10, 2);
begin
	select ( $1 - $2) into total;
	return total;
end
$$
;
--
create or replace function transfer(source_id integer,destination_id integer,amount NUMERIC(10, 2)) returns boolean
language plpgsql
as
$$
begin
	update accounts ac set balance = (select subtract(balance,$3 )) where $1 = ac.id;
	update accounts ac set balance = (select add(balance,$3 )) where $2 = ac.id;
	return true;
end
$$
;
select * from accounts ac inner join customeraccounts ca on ac.id = ca.account_id inner join customers cu on cu.id = ca.customer_id where cu.id = 2