CREATE TABLE IF NOT EXISTS user_auth_authority (
    id varchar(36) NOT NULL,
    user_auth_id varchar(36) NOT NULL,
    authority_id varchar(36) NOT NULL,

    CONSTRAINT FKa3e8c97bb84f41c68e688582c245ff51 FOREIGN KEY (authority_id) REFERENCES authority(id),
    CONSTRAINT FK0db233977fef4c98bc0b79f6323ff6b1 FOREIGN KEY (user_auth_id) REFERENCES user_auth(id)
);
