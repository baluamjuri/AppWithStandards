/*password is 'john'*/
insert into umt_user(username, password,is_active) values('john', '$2a$10$J.89bFmBY402UOb.HSuD4eITPi2vv7fUoSXuyE.lVeN2VHMp0io9e', true);

insert into umt_role(role_name, is_active) values('ADMIN',true);
insert into umt_role(role_name, is_active) values('MANAGER',true);
insert into umt_role(role_name, is_active) values('TEAM_LEAD',true);
insert into umt_role(role_name, is_active) values('TEAM_MEMBER',true);

insert into umt_permission(permission_name, is_active) values('USER_CREATE',true);
insert into umt_permission(permission_name, is_active) values('USER_READ',true);
insert into umt_permission(permission_name, is_active) values('USER_UPDATE',true);
insert into umt_permission(permission_name, is_active) values('USER_DELETE',true);
insert into umt_permission(permission_name, is_active) values('PASSWORD_UPDATE',true);
insert into umt_permission(permission_name, is_active) values('PERMISSION_CREATE',true);
insert into umt_permission(permission_name, is_active) values('PERMISSION_READ',true);
insert into umt_permission(permission_name, is_active) values('PERMISSION_UPDATE',true);
insert into umt_permission(permission_name, is_active) values('PERMISSION_DELETE',true);
insert into umt_permission(permission_name, is_active) values('ROLES_CREATE',true);
insert into umt_permission(permission_name, is_active) values('ROLES_READ',true);
insert into umt_permission(permission_name, is_active) values('ROLES_UPDATE',true);
insert into umt_permission(permission_name, is_active) values('ROLES_DELETE',true);
insert into umt_permission(permission_name, is_active) values('USER_ROLES_MAPPING',true);
insert into umt_permission(permission_name, is_active) values('ROLES_PERMISSION_MAPPING',true);

insert into umt_user_role_map(user_id, role_id) values(1,1);

insert into umt_role_permission_map(role_id, permission_id) values(1,1);
insert into umt_role_permission_map(role_id, permission_id) values(1,2);
insert into umt_role_permission_map(role_id, permission_id) values(1,3);
insert into umt_role_permission_map(role_id, permission_id) values(1,4);
insert into umt_role_permission_map(role_id, permission_id) values(1,5);
insert into umt_role_permission_map(role_id, permission_id) values(1,6);
insert into umt_role_permission_map(role_id, permission_id) values(1,7);
insert into umt_role_permission_map(role_id, permission_id) values(1,8);
insert into umt_role_permission_map(role_id, permission_id) values(1,9);
insert into umt_role_permission_map(role_id, permission_id) values(1,10);
insert into umt_role_permission_map(role_id, permission_id) values(1,11);
insert into umt_role_permission_map(role_id, permission_id) values(1,12);
insert into umt_role_permission_map(role_id, permission_id) values(1,13);
insert into umt_role_permission_map(role_id, permission_id) values(1,14);
insert into umt_role_permission_map(role_id, permission_id) values(1,15);

/*client_secret is 'test'*/
insert into oauth_client_details(client_id, client_secret, scope, authorized_grant_types, access_token_validity, refresh_token_validity, autoapprove)
values('springdeveloper123', '$2a$10$1XsBFUnfHjAMVY2A0itBKewiV6H3q381qmERomwd0srJeu787py7m','read,write','refresh_token,password,authorization_code', 600, 3600, 'read');