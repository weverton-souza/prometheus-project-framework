CREATE TABLE IF NOT EXISTS lesson (
    id varchar(36) NOT NULL,
    main_description varchar(255) NULL,
    "quote" varchar(255) NULL,
    second_description varchar(255) NULL,
    title varchar(255) NOT NULL,

    CONSTRAINT PK520d710c96d843e18da552e9abd9f47e PRIMARY KEY (id)
);
