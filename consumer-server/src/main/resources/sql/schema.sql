create table if not exists company_data(
    company_data_id bigserial primary key,
    company_id bigint unique not null,
    description text not null check ( length(description) > 0 ),
    site text not null
);

create table if not exists contact_person(
    contact_person_id bigserial primary key,
    name text not null check ( length(name) > 0 ),
    email text not null check(length(email) > 0),
    surname text not null check ( length(surname) > 0 ),
    position text not null check ( length(position) > 0 ),
    company_data_id bigint unique references company_data(company_data_id) on update cascade on delete cascade
);


create table if not exists user_data(
    user_data_id bigserial primary key,
    user_id bigint unique not null,
    firstname text not null check ( length(firstname) > 0 ),
    surname text not null check ( length(surname) > 0 ),
    date_of_birth date not null,
    phone_number text not null check ( length(phone_number) = 12 or length(phone_number) = 11),
    email text not null,
    position text not null check ( length(position) > 0 ),
    salary bigint not null check ( salary >= 0 ),
    ready_to_move bool,
    ready_for_business_trips bool
);

create table if not exists vacancy(
    vacancy_id bigserial primary key,
    title text not null check(length(title) > 0),
    salary text,
    address text not null check(length(address) > 0),
    description text not null check(length(description) > 0),
    hash_tags text not null,
    currency text not null,
    company_id bigint not null,
    is_archived bool not null
);

create table if not exists vacancy_response(
   vacancy_id bigint references vacancy(vacancy_id) on update cascade on delete cascade,
   user_id bigint not null,
   status text not null check(length(status) > 0),
   comment text not null check(length(comment) > 0),
   date_last_status_update timestamp not null,
   primary key(vacancy_id, user_id)
);


create table if not exists payment_info(
    payment_info_id bigserial primary key,
    inn text not null check(length(inn) = 10),
    kpp text not null check(length(kpp) = 9),
    company_name text unique not null,
    company_account_number text not null check(length(company_account_number) = 20),
    bank_bik text not null check(length(bank_bik) = 9),
    bank_account_number text not null check(length(bank_account_number) = 20),
    bank_name text not null,
    company_id bigint unique not null
);


--- копируем поля для того, чтобы в случае обновления платежной информации не потерялись старые платежки
create table if not exists payment_order(
    payment_order_id bigserial primary key,
    payer_inn text not null check(length(payer_inn) = 10),
    payer_kpp text not null check(length(payer_kpp) = 9),
    payer_company_name text not null,
    payer_company_account_number text not null check(length(payer_company_account_number) = 20),
    payer_bank_bik text not null check(length(payer_bank_bik) = 9),
    payer_bank_account_number text not null check(length(payer_bank_account_number) = 20),
    payer_bank_name text not null,
    payer_company_id bigint not null,
    recipient_inn text not null check(length(recipient_inn) = 10),
    recipient_kpp text not null check(length(recipient_kpp) = 9),
    recipient_company_name text not null,
    recipient_company_account_number text not null check(length(recipient_company_account_number) = 20),
    recipient_bank_bik text not null check(length(recipient_bank_bik) = 9),
    recipient_bank_account_number text not null check(length(recipient_bank_account_number) = 20),
    recipient_bank_name text not null,
    creation_date date not null,
    purpose_of_payment text not null check(length(purpose_of_payment) > 0),
    sum bigint not null check(sum >= 0),
    is_completed bool not null
);


create table if not exists company_payment_info(
    company_payment_info_id bigserial primary key,
    inn text not null check(length(inn) = 10),
    kpp text not null check(length(kpp) = 9),
    company_name text unique not null,
    company_account_number text not null check(length(company_account_number) = 20),
    bank_bik text not null check(length(bank_bik) = 9),
    bank_account_number text not null check(length(bank_account_number) = 20),
    bank_name text not null check(length(bank_name) > 0),
    purpose_of_payment text not null check(length(purpose_of_payment) > 0),
    sum bigint not null check(sum > 0)
);