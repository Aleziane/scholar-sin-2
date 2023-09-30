create table client(
    id serial PRIMARY KEY,
    servicer_id INT not null,
    number varchar not null,
    name varchar
);

create table rendez_vous(
    id serial PRIMARY KEY,
    status varchar,
    confirmation_status varchar,
    public_id varchar,
    client_id INT not null,
    servicer_id INT not null,
    due_date TIMESTAMPTZ,
    registration_date TIMESTAMPTZ,
    note varchar,
    prepayment INT
);

create table servicer(
    id serial PRIMARY KEY,
    full_name varchar,
    number varchar,
    mail varchar not null
);