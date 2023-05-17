-- USERS
DROP SEQUENCE IF EXISTS user_seq;
create sequence user_seq start with 1 increment by 1;

DROP TABLE IF EXISTS users CASCADE;
create table users
    (
        id bigint not null,
        city varchar(255),
        country varchar(255),
        email varchar(255),
        locked boolean not null,
        name varchar(255),
        password varchar(255),
        phone varchar(255),
        role varchar(255),
        surname varchar(255),
--         bucket_id bigint,
        primary key (id)
    );

-- BRAND
DROP SEQUENCE IF EXISTS brand_seq;
create sequence brand_seq start with 1 increment by 1;

DROP TABLE IF EXISTS brands CASCADE;
create table brands
    (
        id bigint not null,
        title varchar(255),
        primary key (id)
    );

-- BUCKET
DROP SEQUENCE IF EXISTS bucket_seq;
create sequence bucket_seq start with 1 increment by 1;

DROP TABLE IF EXISTS buckets CASCADE;
create table buckets
    (
        id bigint not null,
        user_id bigint,
        primary key (id)
    );
alter table if exists buckets
    add constraint buckets_fk_users
        foreign key (user_id) references users;

-- alter table if exists users
--     add constraint users_fk_buckets
--         foreign key (bucket_id) references buckets;

-- ORDER_DETAILS
DROP SEQUENCE IF EXISTS order_details_seq;
create sequence order_details_seq start with 1 increment by 1;

DROP TABLE IF EXISTS orders_details CASCADE;
create table orders_details
    (
        id bigint not null,
        amount numeric(38,2),
        price numeric(38,2),
        orders_id bigint,
        product_id bigint,
        order_id bigint not null,
        details_id bigint not null,
        primary key (id)
    );
alter table if exists orders_details
    add constraint orders_details_fk
        unique (details_id);

alter table if exists orders_details
    add constraint orders_details_fk_orders_details
        foreign key (details_id) references orders_details;

-- CATEGORY
DROP SEQUENCE IF EXISTS category_seq;
create sequence category_seq start with 1 increment by 1;

DROP TABLE IF EXISTS categories CASCADE;
create table categories
    (
        id bigint not null,
        title varchar(255),
        primary key (id)
    );

-- COLOR
DROP SEQUENCE IF EXISTS color_seq;
create sequence color_seq start with 1 increment by 1;

DROP TABLE IF EXISTS colors CASCADE;
create table colors
    (
        id bigint not null,
        title varchar(255),
        primary key (id)
    );

-- SIZE
DROP SEQUENCE IF EXISTS size_seq;
create sequence size_seq start with 1 increment by 1;

DROP TABLE IF EXISTS sizes CASCADE;
create table sizes
    (
        id bigint not null,
        title varchar(255),
        primary key (id)
    );

-- ORDER
DROP SEQUENCE IF EXISTS order_seq;
create sequence order_seq start with 1 increment by 1;

DROP TABLE IF EXISTS orders CASCADE;
create table orders
    (
        id bigint not null,
        address varchar(255),
        created timestamp(6),
        status varchar(255),
        sum numeric(38,2),
        updated timestamp(6),
        user_id bigint,
        primary key (id)
    );

alter table if exists orders
    add constraint orders_fk_users
        foreign key (user_id) references users;

alter table if exists orders_details
    add constraint orders_details_fk_orders
        foreign key (orders_id) references orders;

-- alter table if exists orders_details
--     add constraint orders_details_fk_orders
--         foreign key (orders_id) references orders;

-- PRODUCT
DROP SEQUENCE IF EXISTS product_seq;
create sequence product_seq start with 1 increment by 1;

DROP TABLE IF EXISTS products CASCADE;
create table products
    (
        id bigint not null,
        price numeric(38,2),
        title varchar(255),
        primary key (id)
    );

-- BUCKET AND PRODUCT
DROP TABLE IF EXISTS buckets_products CASCADE;
create table buckets_products
    (
        bucket_id bigint not null,
        product_id bigint not null
    );

alter table if exists buckets_products
    add constraint buckets_products_fk_products
        foreign key (product_id) references products;

alter table if exists buckets_products
    add constraint buckets_products_fk_buckets
        foreign key (bucket_id) references buckets;

alter table if exists orders_details
    add constraint orders_details_fk_products
        foreign key (product_id) references products;

-- PRODUCT AND BRAND
DROP TABLE IF EXISTS products_brands CASCADE;
create table products_brands
    (
        product_id bigint not null,
        brands_id bigint not null
    );

alter table if exists products_brands
    add constraint products_brands_fk_brands
        foreign key (brands_id) references brands;

alter table if exists products_brands
    add constraint products_brands_fk_products
        foreign key (product_id) references products;

-- PRODUCT AND CATEGORY
DROP TABLE IF EXISTS products_categories CASCADE;
create table products_categories
    (
        product_id bigint not null,
        categories_id bigint not null
    );

alter table if exists products_categories
    add constraint products_categories_fk_categories
        foreign key (categories_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_products
        foreign key (product_id) references products;

-- PRODUCT AND COLOR
DROP TABLE IF EXISTS products_colors CASCADE;
create table products_colors
    (
        product_id bigint not null,
        sizes_id bigint not null,
        colors_id bigint not null
    );

alter table if exists products_colors
    add constraint products_colors_fk_sizes
        foreign key (sizes_id) references sizes;

alter table if exists products_colors
    add constraint products_colors_fk_products
        foreign key (product_id) references products;

alter table if exists products_colors
    add constraint products_colors_fk_colors
        foreign key (colors_id) references colors;