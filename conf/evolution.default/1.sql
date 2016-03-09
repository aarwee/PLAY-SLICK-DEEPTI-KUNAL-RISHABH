
# --- !Ups
--create table "users" ("name" VARCHAR(20) NOT NULL,"email" VARCHAR(30) NOT NULL,"password" VARCHAR(20) NOT NULL,"mobile" VARCHAR(10) NOT NULL,"admin" BOOLEAN NOT NULL,"id" SERIAL PRIMARY KEY);
--INSERT INTO "users" values ('kunal','k@k.com','kunal','9999',true,1);
--
--create table "awards" ("cer_id" SERIAL NOT NULL PRIMARY KEY,"user_id" INTEGER NOT NULL,"cer_email" VARCHAR(50) NOT NULL,"cer_desc" VARCHAR(200) NOT NULL,"cer_year" INTEGER NOT NULL);
--INSERT INTO "awards" values(1,1,'Test Certificate','Test Desc',2016);

create table "language" ("name" VARCHAR(50) ,"fluency" VARCHAR(50) ,"user_id" int ,"id" int PRIMARY KEY auto_increment);

INSERT INTO "language" values('hindi','good',1,null);