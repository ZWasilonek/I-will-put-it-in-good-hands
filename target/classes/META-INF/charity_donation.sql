-- # create database charity_donation char set utf8mb4 collate utf8mb4_unicode_ci;

INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_ADMIN', 1);
INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_USER', 2);

INSERT INTO users VALUES (1, 'admin@gmail.com', 'ZOFIA', 'WASILONEK', '$2a$10$1FchTNOoF6gS9v.s7GkNmujcqscQkMvSkWgi3..vEv9Z9l3X49BNq');
INSERT INTO users VALUES (null, 'jan@o2.pl', 'JAN', 'KOWALSKI', '$2a$10$wtD.roO8R7Cs.X/IRAdnmOfZ6epe1nUmzkHDxZurH4q4DFadAQSOy');

INSERT INTO `user_authority` VALUES (1,1);
INSERT INTO `user_authority` VALUES (1,2);
INSERT INTO `user_authority` VALUES (2,2);

Insert into institutions VALUES (null, 'Pomoc dzieciom z ubogich rodzin.', 'Fundacja “Dbam o Zdrowie”'), (null, 'Pomoc wybudzaniu dzieci ze śpiączki.', 'Fundacja “A kogo”');
Insert into institutions VALUES (null, 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja “Dla dzieci”'), (null, 'Pomoc dla osób nie posiadających miejsca zamieszkania.', 'Fundacja “Bez domu”');

INSERT INTO shipping_address VALUES (null, 'Warszawa', '666-354-754', 'Brak komentarza', '2020-05-15', '15:00','Kwiatowa', '04-888');
INSERT INTO shipping_address VALUES (null, 'Warszawa', '820-654-946', null, '2020-09-10', '09:00','Liliowa', '01-098');

INSERT INTO donations VALUES (null,2, 2, 1, 1);
INSERT INTO donations VALUES (null,3, 1, 2, 2);

INSERT INTO donations_categories VALUES (1, 2),(2,4),(2,1);

# INSERT INTO user_donations VALUES (1,1);
# INSERT INTO user_donations VALUES (2,2);

INSERT INTO categories values (null, 'ubrania, które nadają się do ponownego użycia'), (null, 'ubrania, do wyrzucenia'), (null, 'zabawki'), (null, 'książki'), (null, 'inne');

select d.id, d.bags_quantity, d.institution_id, d.shipping_address_id from donations as d straight_join users as u on d.user_id = u.id where user_id=2
