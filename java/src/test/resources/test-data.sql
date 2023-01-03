BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO users (username,password_hash,role) VALUES ('renter1','renter1','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('renter2','renter2','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('renter3','renter3','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('landlord1','landlord1','ROLE_LANDLORD');
INSERT INTO users (username,password_hash,role) VALUES ('landlord2','landlord2','ROLE_LANDLORD');
INSERT INTO users (username,password_hash,role) VALUES ('landlord3','landlord3','ROLE_LANDLORD');
INSERT INTO users (username,password_hash,role) VALUES ('worker1','worker1','ROLE_WORKER');
INSERT INTO users (username,password_hash,role) VALUES ('worker2','worker2','ROLE_WORKER');
INSERT INTO users (username,password_hash,role) VALUES ('worker3','worker3','ROLE_WORKER');


insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (7, 4, '1', '1', 1, '1', '1', '1', '11111',
	   				 1,'1', 1, 1, 1, '1', true, true, true, '1');

insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 5, '2', '2', 2, '2', '2', '2', '2',
	   				 2, '2', 2, 2, 2, '2', false, false, false, '2');
insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, null, '2', '2', 2, '2', '2', '2', '2',
	   				 2, '2', 2, 2, 2, '2', false, false, false, '2');
insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, null, '2', '2', 2, '2', '2', '2', '2',
	   				 2, '2', 2, 2, 2, '2', false, false, false, '2');




INSERT INTO maintenance_request (property_id, title, description, category)
VALUES (1, 'title1', 'desc1', 'cat1');

INSERT INTO maintenance_request (property_id, title, description, category)
VALUES (2, 'title2 one', 'desc2 one', 'cat2 one');

INSERT INTO maintenance_request (property_id, title, description, category)
VALUES (2, 'title2 two', 'desc2 two', 'cat2 two');


INSERT INTO invoice (property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (1, 7, 4, 500, CURRENT_DATE);

INSERT INTO invoice (property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (2, 7, 5, 500, CURRENT_DATE);

INSERT INTO invoice (property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (3, 9, 5, 400, CURRENT_DATE);

COMMIT TRANSACTION;
