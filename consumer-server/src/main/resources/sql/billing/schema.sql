
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