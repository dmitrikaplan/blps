create type role as enum(
    'ROLE_USER',
    'ROLE_COMPANY',
    'ROLE_ADMIN';
);

create type privilege as enum(
    'CREATE_VACANCY', 'UPDATE_VACANCY', 'DELETE_VACANCY',
     'CREATE_USER_DATA', 'UPDATE_USER_DATA', 'DELETE_USER_DATA',
        'CREATE_COMPANY_DATA', 'UPDATE_COMPANY_DATA', 'DELETE_COMPANY_DATA',
        'CREATE_RESPONSE', 'UPDATE_RESPONSE', 'DELETE_RESPONSE', 'VIEW_RESPONSE',
        'ADD_PAYMENT_DATA', 'UPDATE_PAYMENT_DATA',
        'UPDATE_PAYMENT_STATUS';

);

create table if not exists role_privilege(
    role_name text not null,
    privilege_name text not null
)

create table if not exists users(
    user_id bigserial primary key,
    username text not null unique check ( length(username)  >= 6 and length(username) <= 320),
    email text not null unique check ( length(email) > 0 ),
    password text not null check ( length(password) >= 8 and length(password) <= 1024),
    activated bool not null,
    activation_code text,
    role text not null
);

create table if not exists refresh_token(
    refresh_token_id bigserial primary key,
    token text not null check ( length(token) > 0 ),
    user_id bigint references users(user_id)
);