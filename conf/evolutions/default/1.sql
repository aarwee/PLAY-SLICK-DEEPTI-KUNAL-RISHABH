# --- !Ups
CREATE TABLE "language" ("name" VARCHAR(50) ,"fluency" VARCHAR(50) ,"user_id" int ,"id" Serial PRIMARY KEY );
INSERT into "language" values ('hindi','good',1,null);

# --- !Downs
Drop table "language";



