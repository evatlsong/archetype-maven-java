
    alter table BID 
        drop 
        foreign key FK_BIDDER_ID;

    alter table BID 
        drop 
        foreign key FK_ITEM_ID;

    alter table BILLING_ADDRESS 
        drop 
        foreign key FK_6xg9muax45hd8a2nlgbd3vb6r;

    alter table ITEM 
        drop 
        foreign key FK_APPROVED_BY_USER_ID;

    alter table ITEM 
        drop 
        foreign key FK_SELLER_ID;

    alter table ITEM 
        drop 
        foreign key FK_SUCCESSFUL_BID_ID;

    alter table ITEM_IMAGES 
        drop 
        foreign key FK_4ttmkmk94v5lp732t9ak4et8i;

    alter table category 
        drop 
        foreign key FK_CATEGORY_PARENT_ID;

    drop table if exists BID;

    drop table if exists BILLING_ADDRESS;

    drop table if exists ITEM;

    drop table if exists ITEM_IMAGES;

    drop table if exists Person;

    drop table if exists USERS;

    drop table if exists category;

    create table BID (
        BID_ID bigint not null auto_increment,
        CREATED datetime not null,
        BIDDER_ID bigint not null,
        ITEM_ID bigint not null,
        BID_POSITION integer,
        primary key (BID_ID)
    );

    create table BILLING_ADDRESS (
        CITY varchar(255),
        STREET varchar(255),
        ZIPCODE varchar(16),
        USER_ID bigint not null,
        primary key (USER_ID)
    );

    create table ITEM (
        ITEM_ID bigint not null auto_increment,
        APPROVAL_DATETIME datetime,
        CREATED datetime not null,
        DESCRIPTION varchar(4000) not null,
        END_DATE datetime not null,
        ITEM_NAME varchar(255) not null,
        START_DATE datetime not null,
        ITEM_STATE varchar(5) not null,
        OBJ_VERSION integer,
        APPROVED_BY_USER_ID bigint,
        SELLER_ID bigint not null,
        SUCCESSFUL_BID_ID bigint,
        primary key (ITEM_ID)
    );

    create table ITEM_IMAGES (
        ITEM_ID bigint not null,
        FILENAME varchar(255),
        ITEM_IMAGE_ID bigint not null auto_increment,
        primary key (ITEM_IMAGE_ID)
    );

    create table Person (
        id bigint not null auto_increment,
        firstname varchar(255),
        lastname varchar(255),
        primary key (id)
    );

    create table USERS (
        USER_ID bigint not null auto_increment,
        IS_ADMIN bit not null,
        EMAIL varchar(255) not null,
        FIRSTNAME varchar(255) not null,
        HOME_CITY varchar(255),
        HOME_STREET varchar(255),
        HOME_ZIPCODE varchar(16),
        LASTNAME varchar(255) not null,
        `PASSWORD` varchar(12) not null,
        RANK integer not null,
        USERNAME varchar(16) not null,
        OBJ_VERSION integer,
        primary key (USER_ID)
    );

    create table category (
        id bigint not null auto_increment,
        name varchar(255),
        parent_category_id bigint,
        primary key (id)
    );

    create index IDX_AAAAA on ITEM (DESCRIPTION, SELLER_ID);

    create index IDX_END_DATE on ITEM (END_DATE);

    alter table USERS 
        add constraint UK_h6k33r31i2nvrri9lok4r163j  unique (USERNAME);

    alter table BID 
        add constraint FK_BIDDER_ID 
        foreign key (BIDDER_ID) 
        references USERS (USER_ID);

    alter table BID 
        add constraint FK_ITEM_ID 
        foreign key (ITEM_ID) 
        references ITEM (ITEM_ID);

    alter table BILLING_ADDRESS 
        add constraint FK_6xg9muax45hd8a2nlgbd3vb6r 
        foreign key (USER_ID) 
        references USERS (USER_ID);

    alter table ITEM 
        add constraint FK_APPROVED_BY_USER_ID 
        foreign key (APPROVED_BY_USER_ID) 
        references USERS (USER_ID);

    alter table ITEM 
        add constraint FK_SELLER_ID 
        foreign key (SELLER_ID) 
        references USERS (USER_ID);

    alter table ITEM 
        add constraint FK_SUCCESSFUL_BID_ID 
        foreign key (SUCCESSFUL_BID_ID) 
        references BID (BID_ID);

    alter table ITEM_IMAGES 
        add constraint FK_4ttmkmk94v5lp732t9ak4et8i 
        foreign key (ITEM_ID) 
        references ITEM (ITEM_ID);

    alter table category 
        add constraint FK_CATEGORY_PARENT_ID 
        foreign key (parent_category_id) 
        references category (id);
