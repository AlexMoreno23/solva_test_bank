create table transaction
(
    id                  int8        not null,
    account_from        varchar(10) not null,
    account_to          varchar(10) not null,
    currency_short_name varchar(5)  not null,
    datetime            timestamp   not null,
    sum                 numeric(19, 2),
    usd_limit_id        int8,
    primary key (id)
);
create table transaction_category
(
    transaction_id int8 not null,
    category       varchar(255)
);
create table usd_limit
(
    id                      int8 not null,
    currency_short_name     varchar(255),
    limit_exceeded          boolean,
    remaining_monthly_limit numeric(19, 2),
    sum_limit               numeric(19, 2),
    time_create_limit       timestamp,
    primary key (id)
);
create table usd_limit_category
(
    usd_limit_id int8 not null,
    category     varchar(255)
);
create table usd_rate
(
    id     int8           not null,
    rate   numeric(19, 2) not null,
    symbol varchar(255)   not null,
    time   varchar(255)   not null,
    primary key (id)
);
alter table if exists transaction add constraint FK2vfsahyx04b5milxofbk5vqb3
    foreign key (usd_limit_id) references usd_limit;

alter table if exists transaction_category add constraint FKo9vcqmrcla8kxtwjbh0ury6lq
    foreign key (transaction_id) references transaction;

alter table if exists usd_limit_category add constraint FKtnmr0bvau333wr5njnnlius3u
    foreign key (usd_limit_id) references usd_limit;