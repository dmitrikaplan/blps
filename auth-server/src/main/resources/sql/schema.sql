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