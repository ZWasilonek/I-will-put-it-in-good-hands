# create database charity_donation char set utf8mb4 collate utf8mb4_unicode_ci;
select * from categories;
select * from donations;

INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_ADMIN', 1);
INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_USER', 2);

INSERT INTO `user_authority` VALUES (1,1);

Insert into institutions VALUES (null, 'Pomoc dzieciom z ubogich rodzin.', 'Fundacja “Dbam o Zdrowie”'), (null, 'Pomoc wybudzaniu dzieci ze śpiączki.', 'Fundacja “A kogo”');
Insert into institutions VALUES (null, 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja “Dla dzieci”'), (null, 'Pomoc dla osób nie posiadających miejsca zamieszkania.', 'Fundacja “Bez domu”');

INSERT INTO donations VALUES (null, 'Warszawa', 'Brak komentarza', '2020-05-15', '15:00', '13','Kwiatowa', '04-888',2);
INSERT INTO user_donations VALUES (1,3);

INSERT INTO categories values (null, 'ubrania, które nadają się do ponownego użycia'), (null, 'ubrania, do wyrzucenia'), (null, 'zabawki'), (null, 'książki'), (null, 'inne');

# SELECT id, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id FROM donations d straight_join user_donations on d.id = user_donations.donation_id WHERE user_id=1;