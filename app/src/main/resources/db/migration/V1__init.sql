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


create table student(
    id serial PRIMARY KEY,
    first_name varchar,
    birth_date TIMESTAMPTZ,
    last_name varchar,
    matricule varchar,
    free_text varchar,
    mother_first_name varchar,
    mother_last_name varchar,
    father_first_name varchar,
    father_last_name varchar,
    contact_first_name varchar,
    contact_last_name varchar,
    primary_contact_phone varchar,
    secondary_contact_phone varchar,
    mail varchar not null,
    fullAddress varchar,
    registration_date TIMESTAMPTZ
);


create table record(
    id serial PRIMARY KEY,
    type varchar,
    price INT,
    comment varchar,
    eventStartDate TIMESTAMPTZ,
    eventEndDate TIMESTAMPTZ,
    invoice_id INT,
    student_id INT,
    registration_date TIMESTAMPTZ
);

create table invoice(
    id serial PRIMARY KEY,
    paid boolean,
    sendDate TIMESTAMPTZ,
    file bytea,
    registrationDate TIMESTAMPTZ
);

create table record_price_mapping(
    id serial PRIMARY KEY,
    type varchar,
    price INT
);

create table school(
    id serial PRIMARY KEY
);

create table actor(
    id serial PRIMARY KEY
);