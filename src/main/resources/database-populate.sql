insert into user (username, fullname, password) values ('admin', 'Administrator', '$2a$10$FPXLVweqprpxU9yGoX2RfuF/xtUVamB4XQByESlaqygK8d4cORgtq');
insert into user (username, fullname, password) values ('user01', 'User 01', '$2a$10$DWBqLX6nv6h2UTzg1FcX4uC21PN.MdMKWegsj/tLJvR/iDjzK/jpS');
insert into user (username, fullname, password) values ('user02', 'User 02', '$2a$10$xI8tXn1xzqe.YyeysYL5kuD3WEZLCom7vZ5TJOEQggoFBG.Z4jXhu');
insert into user (username, fullname, password) values ('user03', 'User 03', '$2a$10$CINTGP9y3EH.DdsgHb4BsuiX/cekGwK0A6If5xcZgtamzGt3ZQ06W');
insert into user (username, fullname, password) values ('user04', 'User 04', '$2a$10$bfUBwRcPUJJaibXJUekOWubfdUzAQyv91m/JcJ9ftu2Ryp/7kTVtW');
insert into user (username, fullname, password) values ('user05', 'User 05', '$2a$10$tc2P1gncfAJsKcA.hMRszuN3N1vljXaHaV4nhuHGekcnagEcLxDfu');
insert into user (username, fullname, password) values ('user06', 'User 06', '$2a$10$EQPjEsFVBAIPQCemXtWSwe2OVQt28mUp1jQahuQHep2CUOej0VxjC');
insert into user (username, fullname, password) values ('user07', 'User 07', '$2a$10$88Nu.zsouhH9YZG5kX7td.bm3j6fySQUSeJ7pAo5.ZifT88kTzUvO');
insert into user (username, fullname, password) values ('user08', 'User 08', '$2a$10$pIcoFjNK7iAgpogzOMQteO1wwTTYaXu5iajgFIS7c2JkaxJ4ugp0a');
insert into user (username, fullname, password) values ('user09', 'User 09', '$2a$10$vO4e/TobzfF0tabk2dZ.TewfShZHgIIAhekiHdbLmbnQ5g4MVTFiO');
insert into user (username, fullname, password) values ('user10', 'User 10', '$2a$10$kDuuZOFYrKNpKOcu/MOT8eOGecp..dlRo8m.LrJF4hga.6KKp1ula');

insert into role (rolename) values ('ROLE_ADMIN');
insert into role (rolename) values ('ROLE_USER');

insert into user_role (username, rolename) values ('admin', 'ROLE_ADMIN');
insert into user_role (username, rolename) values ('user01', 'ROLE_USER');
insert into user_role (username, rolename) values ('user02', 'ROLE_USER');
insert into user_role (username, rolename) values ('user03', 'ROLE_USER');
insert into user_role (username, rolename) values ('user04', 'ROLE_USER');
insert into user_role (username, rolename) values ('user05', 'ROLE_USER');
insert into user_role (username, rolename) values ('user06', 'ROLE_USER');
insert into user_role (username, rolename) values ('user07', 'ROLE_USER');
insert into user_role (username, rolename) values ('user08', 'ROLE_USER');
insert into user_role (username, rolename) values ('user09', 'ROLE_USER');
insert into user_role (username, rolename) values ('user10', 'ROLE_USER');
