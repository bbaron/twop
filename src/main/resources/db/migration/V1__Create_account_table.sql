create sequence hibernate_sequence start with 1 increment by 1;
create table account (
    id bigint not null,
    created_date timestamp not null,
    email varchar(50) not null,
    favorite_show varchar(255),
    name varchar(255),
    primary key (id)
);
alter table account add constraint UK__account_email unique (email);
