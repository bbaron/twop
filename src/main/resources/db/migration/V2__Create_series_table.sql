create table series (
    id bigint not null,
    name varchar(255) not null,
    primary key (id)
);
alter table series add constraint UK__series_name unique (name);
