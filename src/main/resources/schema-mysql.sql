-- drop table person;
CREATE TABLE if not exists person (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY (id)
);

select 1;