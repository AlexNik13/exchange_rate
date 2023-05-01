create table roles
(
    name varchar(255) not null
        constraint roles_pkey
            primary key
);


create table users
(
    id         uuid         not null
        constraint users_pkey
            primary key,
    email      varchar(255) unique not null,
    password   varchar(255) not null,
    created_at timestamp    not null,
    modify_at  timestamp    not null
);


create table users_roles
(
    user_id uuid not null
        constraint users_roles_users_id
            references users,
    role_name varchar(255) not null
        constraint fk_roles_users_roles
            references roles,
    constraint users_roles_pkey
        primary key (user_id, role_name)
);


create table sessions
(
    id              bigserial    not null
        constraint sessions_pkey
            primary key,
    refresh_token   varchar(255) not null,
    user_agent_hash integer      not null,
    user_id         uuid
        constraint fk_users_sessions
            references users,
    created_at      timestamp(6) not null,
    expired_at      timestamp(6) not null
);


create table email_registrations
(
    id            bigserial
        constraint email_registrations_pkey
        primary key,
    email         varchar(255) not null,
    created_at    timestamp(6) not null,
    expiration_at timestamp(6) not null,
    token         uuid
);

