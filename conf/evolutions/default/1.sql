# --- !Ups


CREATE table "language" ("name" VARCHAR(50) ,"fluency" VARCHAR(50) ,"user_id" int ,"id"  Serial PRIMARY KEY);

INSERT into "language" values('hindi','good',1,1);

INSERT into "language" values('english','good',3,2);

CREATE table "programming"("name" VARCHAR(50) ,"fluency" VARCHAR(50) ,"user_id" int ,"id"  Serial PRIMARY KEY);
INSERT into "programming" values('groovy','good',1,1);
INSERT into "programming" values('scala','good',4,2);

CREATE TABLE "awards"("name" VARCHAR(50),"description" VARCHAR(50),"year" VARCHAR(10),"user_id" int ,"id"  Serial PRIMARY KEY );
INSERT into "awards" values('microsoft','good',1990,1,1);
INSERT into "awards" values('sun certificate','good',1992,3,2);

CREATE table "users"("name" VARCHAR(50),"email" VARCHAR(50),"password" VARCHAR(50) , "mobile" VARCHAR(50), "admin" BOOLEAN ,"id" Serial PRIMARY KEY);
INSERT into "users" values('rishabh','rishabh@gmail.com','1992','35555555',true,1);
INSERT into "users" values('deepti','deepti@hotmail.com','199222','3555555555',true,2);
CREATE table "assignment"("name" VARCHAR(50),"date" VARCHAR(50),"marks" int , "remarks" VARCHAR(50), "user_id" int ,"id"  Serial PRIMARY KEY );
INSERT into "assignment" values('c++','2015-08-08',22,'good',1,1);




# --- !Downs

DROP TABLE "language";
DROP TABLE "programming";
DROP TABLE "awards";
DROP TABLE "users";
DROP TABLE "assignment";

