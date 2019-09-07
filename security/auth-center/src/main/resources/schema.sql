drop table if exists umt_user_role_map;
drop table if exists umt_user_profile;
drop table if exists umt_user;
drop table if exists umt_role_permission_map;
drop table if exists umt_role;
drop table if exists umt_permission;


create table umt_permission (
   id bigint not null auto_increment,
	is_active bit not null,
	description varchar(255),
	permission_name varchar(255) not null,
	primary key (id)
) engine=InnoDB;


create table umt_role (
   id bigint not null auto_increment,
	is_active bit not null,
	description varchar(255),
	role_name varchar(255) not null,
	primary key (id)
) engine=InnoDB;


create table umt_role_permission_map (
   role_id bigint not null,
	permission_id bigint not null
) engine=InnoDB;


create table umt_user (
   id bigint not null auto_increment,
	is_active bit not null,
	display_name varchar(255),
	password varchar(255) not null,
	username varchar(255) not null,
	primary key (id)
) engine=InnoDB;


create table umt_user_profile (
   id bigint not null auto_increment,
	email varchar(255),
	gender varchar(255) not null,
	mobile varchar(10),
	user_id bigint,
	primary key (id)
) engine=InnoDB;


create table umt_user_role_map (
   user_id bigint not null,
	role_id bigint not null
) engine=InnoDB;


alter table umt_permission 
   drop index if exists UK_oknl5yugatncpl6xohsub5vwp;


alter table umt_permission 
   add constraint UK_oknl5yugatncpl6xohsub5vwp unique (permission_name);


alter table umt_role 
   drop index if exists UK_h2ab2bfqvopqqrqmd4bi2dc5q;


alter table umt_role 
   add constraint UK_h2ab2bfqvopqqrqmd4bi2dc5q unique (role_name);


alter table umt_user 
   drop index if exists UK_58as0kb1hqgtp4kvu7jt9qlp5;


alter table umt_user 
   add constraint UK_58as0kb1hqgtp4kvu7jt9qlp5 unique (username);


alter table umt_role_permission_map 
   add constraint FKhglsx4fq2w7exgjnaub376gs9 
   foreign key (permission_id) 
   references umt_permission (id);


alter table umt_role_permission_map 
   add constraint FK8qosrc00w4pro86wii0rywpgy 
   foreign key (role_id) 
   references umt_role (id);


alter table umt_user_profile 
   add constraint FKfnylo41phiikhhygrbspux5sf 
   foreign key (user_id) 
   references umt_user (id);


alter table umt_user_role_map 
   add constraint FK1e3dthe2rlmq5605734iexdmy 
   foreign key (role_id) 
   references umt_role (id);


alter table umt_user_role_map 
   add constraint FKl31bh9f8vpkp88bmjawp9mtim 
   foreign key (user_id) 
   references umt_user (id);
	   
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
