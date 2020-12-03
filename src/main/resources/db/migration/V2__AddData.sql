insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('2002', 'user1@mail.ru', 1, 'user1', '111', 'dgd', '2019-12-01 20:00');

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('asd', 'user2@mail.ru', 0, 'user2', '111', 'dgd', '2020-01-03 15:00');



insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'NEW', 1, 'New post #1', '2020-12-01 19:19:00', 'Title of post #1', 125, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'NEW', 1, 'New post #2', '2020-12-02 23:19:00', 'Title of post #2', 125, 2);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'NEW', 1, 'New post #3', '2020-12-03 20:19:00', 'Title of post #3', 125, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'NEW', 1, 'New post #4', '2020-12-03 21:19:00', 'Title of post #4', 125, 2);


insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:21', 1, 1, 1);

insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:25', -1, 1, 2);





