CREATE TABLE group_gen(
                          ID bigserial PRIMARY KEY NOT NULL,
                          name varchar,
                          parent_id bigint REFERENCES "group_gen"(ID)

);

CREATE TABLE item(
                     ID bigserial PRIMARY KEY NOT NULL,
                     basePrice int,
                     name varchar,
                     imageUrl varchar,
                     parentId bigint REFERENCES group_gen(ID)
);