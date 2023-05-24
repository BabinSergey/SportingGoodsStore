-- USERS
create sequence user_seq start 1 increment 1;

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
    primary key (id)
);

-- BUCKET
create sequence bucket_seq start 1 increment 1;

create table buckets
(
    id bigint not null,
    user_id bigint,
    primary key (id)
);

-- BUCKET AND USER
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;

-- CATEGORY
create sequence category_seq start 1 increment 1;

create table categories
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);

-- PRODUCTS
create sequence product_seq start 1 increment 1;

create table products
(
    id bigint not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);

-- PRODUCT AND CATEGORY
create table products_categories
(
    product_id bigint not null,
    category_id bigint not null
);

alter table if exists products_categories
    add constraint products_categories_fk_category
        foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_products
        foreign key (product_id) references products;

-- BUCKET AND PRODUCT
create table bucket_products
(
    bucket_id bigint not null,
    product_id bigint not null
);

alter table if exists bucket_products
    add constraint bucket_products_fk_product
        foreign key (product_id) references products;

alter table if exists bucket_products
    add constraint bucket_products_fk_bucket
        foreign key (bucket_id) references buckets;

-- ORDER
create sequence order_seq start 1 increment 1;

create table orders
(
    id bigint not null,
    address varchar(255),
    created timestamp,
    updated timestamp,
    status varchar(255),
    sum numeric(38,2),
    user_id bigint,
    primary key (id)
);

alter table if exists orders
    add constraint orders_fk_user
        foreign key (user_id) references users;

-- ORDER_DETAILS
create sequence order_details_seq start 1 increment 1;

create table orders_details
(
    id bigint not null,
    order_id bigint not null,
    product_id bigint not null,
    amount numeric(38,2),
    price numeric(38,2),
    primary key (id)
);

alter table if exists orders_details
    add constraint orders_details_fk_order
        foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_product
        foreign key (product_id) references products;

-- BRAND
create sequence brand_seq start 1 increment 1;

create table brands
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);

-- PRODUCT AND BRAND
create table products_brand
(
    product_id bigint not null,
    brand_id bigint not null
);

alter table if exists products_brand
    add constraint products_brand_fk_brands
        foreign key (brand_id) references brands;

alter table if exists products_brand
    add constraint products_brand_fk_products
        foreign key (product_id) references products;

-- COLOR
create sequence color_seq start 1 increment 1;

create table colors
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);

-- SIZE
create sequence size_seq start 1 increment 1;

create table sizes
(
    id bigint not null,
    title varchar(255),
    primary key (id)
);

-- PRODUCT AND COLOR AND SIZE
create table products_color
(
    product_id bigint not null,
    sizes_id bigint not null,
    colors_id bigint not null
);

alter table if exists products_color
    add constraint products_color_fk_sizes
        foreign key (sizes_id) references sizes;

alter table if exists products_color
    add constraint products_color_fk_product
        foreign key (product_id) references products;

alter table if exists products_color
    add constraint product_color_fk_color
        foreign key (colors_id) references colors;