insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('2002', 'user1@mail.ru', 1, 'user1', '111', 'dgd', '2019-12-01 20:00');

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('asd', 'user2@mail.ru', 0, 'user2', '111', 'dgd', '2020-01-03 15:00');

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('asd', 'user3@mail.ru', 0, 'user3', '111', 'dgd', '2020-01-04 15:00');

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values ('asd', 'user4@mail.ru', 0, 'user4', '111', 'dgd', '2020-01-05 15:00');


insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #1', '2020-12-01 19:19:00', 'Title of post #1', 135, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #2', '2020-12-02 23:19:00', 'Title of post #2', 125, 2);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #3', '2020-12-03 20:19:00', 'Title of post #3', 122, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (0, 'NEW', 1, 'New post #4', '2020-12-03 21:19:00', 'Title of post #4', 111, 2);


insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:21', 1, 3, 1);

insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:25', -1, 3, 2);

insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:21', 1, 3, 3);

insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:25', 1, 3, 4);


insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:21', 1, 2, 1);

insert into post_votes (time, value, post_id, user_id)
values ('2020-12-01 19:20:21', 1, 2, 3);


insert into post_comments (text, time, post_id, user_id)
values ('bla-bla-bla', '2020-12-01 19:19:10', 2, 1);

insert into post_comments (text, time, post_id, user_id)
values ('bla-bla-bla', '2020-12-01 19:20:00', 1, 1);

insert into post_comments (parent_id, text, time, post_id, user_id)
values (2, 'сам ты блабла', '2020-12-01 19:20:01', 1, 2);


#####

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #5', '2019-12-01 19:20:00', 'Title of post #5', 1585, 3);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #6', '2020-12-02 23:20:00', 'Title of post #6', 4015, 4);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #7', '2020-12-03 20:20:00', 'Title of post #7', 301, 3);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (0, 'NEW', 1, 'New post #8', '2020-12-03 21:20:00', 'Title of post #8', 198, 2);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #9', '2020-12-02 19:19:00', 'Title of post #9', 5201, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #10', '2020-12-03 23:19:00', 'Title of post #10', 3986, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #11', '2020-12-04 20:19:00', 'Title of post #11', 4576, 4);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (0, 'NEW', 1, 'New post #12', '2020-12-05 21:19:00', 'Title of post #12', 4578, 3);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #13', '2020-12-06 19:19:00', 'Title of post #13', 3654, 2);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #14', '2020-12-07 23:19:00', 'How good to code (title of post #14)', 145, 1);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #15', '2020-12-07 20:19:00', 'Title of post #15', 1650, 2);

insert into posts (is_active, moderation_status, moderator_id, text, time, title, view_count, user_id)
values (1, 'ACCEPTED', 1, 'New post #16', '2020-12-09 10:30:00', 'Title of post #16', 1985, 3);


insert into tags (name)
values ('sport');
insert into tags (name)
values ('politics');
insert into tags (name)
values ('coding');
insert into tags (name)
values ('finance');

insert into tag2post (post_id, tag_id)
values (16, 3);

insert into tag2post (post_id, tag_id)
values (1, 1);

insert into tag2post (post_id, tag_id)
values (1, 2);

insert into tag2post (post_id, tag_id)
values (2, 2);





