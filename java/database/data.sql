BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username,password_hash,role) VALUES ('bob','$2a$10$b6QBd2m5UjgCWH/GQPyrc.XvbWDUO7MS5TV4WaqwkRkf4Q9p5H4ci','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('sam','$2a$10$aBdfH0KsrDrm6eTb4qOa/Opz5qngxfz3.cxH7TXVHdFQsSv/sgE/m','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('lucy','$2a$10$nojkpo5tCpH51spRPn7wG.P5LXNk5TBzpYH5daCxrYzS6mNJeeDeq','ROLE_RENTER');
INSERT INTO users (username,password_hash,role) VALUES ('jill','$2a$10$rmZ7sT4rwoCtbZvDu6glbuiw/CqgG2ACi7taKn0rrWp.mYpwToEQa','ROLE_WORKER');
INSERT INTO users (username,password_hash,role) VALUES ('cat','$2a$10$lh5PY8f1hMCXlsG7OUNfUu8wKrbBfAB7I8aH4OVT223XMHf0J3hBK','ROLE_WORKER');

INSERT INTO users (username,password_hash,role) VALUES ('boss','$2a$10$G9nAMdn3HliMNr0vkx06VukN2G4l1hlNSumdGYHkb5eEnTJBu.Upe','ROLE_LANDLORD');
INSERT INTO users (username,password_hash,role) VALUES ('biggs','$2a$10$RtLWH4Ojer/om8AZKAtvruLH88nNaARgqYDFGMLObJWJ89YqmQqsq','ROLE_LANDLORD');


insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 3, 'fun place', 'Shadyside', 123, 'Pleasant St', 'Pittsburgh', 'PA', '15222',
	   				1200, 'castle', 4, 2, 2300, 'gas', true, false, true, '1 garage space');

insert into property (landlord_id, renter_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 4, 'slum central', 'Squirrel Hill South', 221, 'Rat St', 'Pittsburgh', 'PA', '15217',
	   				500, 'garage', 2, 1, 600, 'stove', false, true, true, 'none');

insert into property (landlord_id, description, neighborhood, house_number, street,
                      city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
                      area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'party central', 'Oakland', 121, 'Atwood St', 'Pittsburgh', 'PA', '15223',
                    1100, 'griddy basement', 3, 1, 800, 'gas', true, true, true, 'yeah right');

insert into property (landlord_id, description, neighborhood, house_number, street,
                      city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
                      area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'ok place, just ok', 'Regent Square', 321, 'All Right Ave', 'Pittsburgh', 'PA', '15333',
                    1500, 'apartment', 4, 2, 1800, 'electric', true, false, true, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'rustic gem', 'Squirrel Hill South', 7070, 'Dirt St', 'Pittsburgh', 'PA', '15217',
	   				600, 'tent', 1, 1, 500, 'campfire', false, true, true, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
 'Lawrenceville', 3567, 'Butler St', 'Pittsburgh', 'PA', '15123',
	   				2100, 'house', 4, 2, 1700, 'gas', true, false, false, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
            'Squirrel Hill North', 978, 'Main St', 'Pittsburgh', 'PA', '15217',
	   		1800, 'house', 4, 3, 1500, 'gas', true, false, true, '1 driveway space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Squirrel Hill South', 352, 'Beechwood Bvld', 'Pittsburgh', 'PA', '15217',
	   				2000, 'house', 5, 3, 2300, 'gas', false, false, true, '1 driveway space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'East Liberty', 532, 'Centre Ave', 'Pittsburgh', 'PA', '15217',
	   				1950, 'apartment', 4, 2, 1400, 'gas', false, false, true, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Highland Park', 153, 'Broad St', 'Pittsburgh', 'PA', '15217',
	   				1400, 'apartment', 2, 1, 1200, 'gas', true, false, false, '2 driveway spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Bloomfield', 634, 'Liberty Ave', 'Pittsburgh', 'PA', '15217',
	   				1700, 'apartment', 2, 1, 1800, 'gas', false, true, false, '2 driveway spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Point Breeze', 7456, 'Edgerton Ave', 'Pittsburgh', 'PA', '15217',
	   				2300, 'apartment', 1, 1, 1000, 'gas', true, false, true, '2 garage spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Squirrel Hill North', 643, 'Hobart St', 'Pittsburgh', 'PA', '15217',
	   				2600, 'apartment', 3, 1, 800, 'gas', true, false, false, '2 garage spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Squirrel Hill South', 463, 'Monitor St', 'Pittsburgh', 'PA', '15217',
	   				900, 'apartment', 1, 1, 800, 'electric', false, true, false, '1 garage space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Morningside', 7067, 'Morningside Ave', 'Pittsburgh', 'PA', '15217',
	   				1400, 'townhouse', 2, 1, 600, 'electric', true, false, true, 'none');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Morningside', 8754, 'Zoo St', 'Pittsburgh', 'PA', '15217',
	   				1200, 'townhouse', 3, 1, 1100, 'electric', false, false, true, '2 driveway spaces');



insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Point Breeze', 457, 'Homewood Ave', 'Pittsburgh', 'PA', '15235',
	   				1900, 'townhouse', 3, 1, 1100, 'electric', true, false, false, '1 garage space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Oakland', 765, 'Meyran Ave', 'Pittsburgh', 'PA', '15299',
	   				1600, 'townhouse', 2, 1, 900, 'electric', false, true, false, '1 garage space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Lawrenceville', 7654, '44th St', 'Pittsburgh', 'PA', '15277',
	   				1600, 'apartment', 4, 2, 700, 'gas', true, false, true, '2 garage spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Highland Park', 345, 'Highland Ave', 'Pittsburgh', 'PA', '15255',
	   				1300, 'apartment', 1, 1, 600, 'gas', false, true, false, '2 driveway spaces');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'East Liberty', 5433, 'Highland Ave', 'Pittsburgh', 'PA', '15217',
	   				1300, 'apartment', 2, 1, 700, 'gas', true, false, true, '1 driveway space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Bloomfield', 234, 'Millvale Ave', 'Pittsburgh', 'PA', '15222',
	   				1600, 'house', 3, 1, 600, 'gas', true, false, false, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Point Breeze', 7555, 'Willard St', 'Pittsburgh', 'PA', '15233',
	   				1800, 'house', 2, 1, 800, 'gas', true, false, true, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Shadyside', 7515, 'Shady Ave', 'Pittsburgh', 'PA', '15235',
	   				2500, 'house', 4, 2, 1400, 'gas', true, false, true, '1 driveway space');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (8, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Shadyside', 2455, 'Copland St', 'Pittsburgh', 'PA', '15256',
	   				2100, 'apartment', 2, 1, 950, 'electric', true, false, false, 'on-street');

insert into property (landlord_id, description, neighborhood, house_number, street,
					  city, state, zipcode, monthly_rent, type, bedrooms, bathrooms,
 					  area, heating_type, has_ac, is_smoking, are_pets_allowed, parking)
values (9, 'This is the hippest spot in town. adfgsdfg sdfgsdfg sdfgsfdg sdfgsdfg sdfgsdfg sdfgsfdg sdfg sdfgsdg gdhh ghdg srsregwrgs dghd gfhdgh.',
'Regent Square', 4555, 'Milton St', 'Pittsburgh', 'PA', '15234',
	   				1500, 'apartment', 3, 1, 750, 'gas', false, false, true, '1 driveway space');





INSERT INTO maintenance_request (property_id, title, description, category)
VALUES (1, 'broken sink', 'water is brown', 'plumbing');

INSERT INTO maintenance_request (property_id, worker_id, title, description, category)
VALUES (1, 7, 'rats', 'full of rats', 'pests');

INSERT INTO maintenance_request (property_id, title, description, category)
VALUES (2, 'ugly wallpaper', 'the wallpaper is hideous', 'other');

INSERT INTO maintenance_request (property_id, worker_id, title, description, category)
VALUES (3, 7, 'no toilet', 'there is no toilet', 'plumbing');

INSERT INTO maintenance_request (property_id, worker_id, title, description, category)
VALUES (3, 7, 'needs a cat', 'i would like there to be a resident cat', 'other');

INSERT INTO maintenance_request (property_id, worker_id, title, description, category)
VALUES (3, 6, 'literally everything', 'literally everything is broken', 'other');


INSERT INTO invoice(property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (1, 1, 1, 500, CURRENT_DATE);

INSERT INTO invoice(property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (1, 8, 3, 500, CURRENT_DATE);

INSERT INTO invoice(property_id, landlord_id, renter_id, amount_due, created_date)
VALUES (2, 9, 4, 400, CURRENT_DATE);

insert into photo (photo_path)
values ('castle.jpg');

insert into photo (photo_path)
values ('shack.jpg');

insert into photo (photo_path)
values ('tent.jpg');

insert into photo_property (photo_id, property_id)
values (3, 5);

insert into photo_property (photo_id, property_id)
values (1, 4);

insert into photo_property (photo_id, property_id)
values (2, 3);


COMMIT TRANSACTION;