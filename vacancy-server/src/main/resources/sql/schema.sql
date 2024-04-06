create table if not exists company_data(
    company_data_id bigserial primary key,
    username text not null unique check (length(username) >= 6 and length(username) <= 320)
    company_name text not null check ( length(company_name) > 0 ),
    description text not null check ( length(description) > 0 ),
    site text not null
);

create table if not exists contact_person(
    contact_person_id bigserial primary key,
    name text not null check ( length(name) > 0 ),
    surname text not null check ( length(surname) > 0 ),
    position text not null check ( length(position) > 0 ),
    company_details_id bigint references company_details(company_details_id)
);


create table if not exists user_data(
    user_data_id bigserial primary key,
    username text not null unique check (length(username) >= 6 and length(username) <= 320)
    firstname text not null check ( length(firstname) > 0 ),
    surname text not null check ( length(surname) > 0 ),
    date_of_birth date not null,
    phone_number text not null check ( length(phone_number) = 12 ),
    email text not null,
    position text not null check ( length(position) > 0 ),
    salary bigint not null check ( salary >= 0 ),
    ready_to_move bool,
    ready_for_business_trip bool
);

create table if not exists vacancy(
    vacancy_id bigserial primary key,
    title text not null check(length(title) > 0),
    salary text,
    address text not null check(length(address) > 0),
    description text not null check(length(description) > 0),
    hash_tags text not null,
    currency text not null
);

create table if not exists vacancy_response(
   vacancy_id bigint references vacancy(vacancy_id),
   user_id bigint,
   primary key(vacancy_id, user_id)
);