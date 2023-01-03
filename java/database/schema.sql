BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, photo, photo_property, maintenance_request, property, invoice;

CREATE TABLE users (
	user_id serial NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	first_name varchar(50),
	last_name varchar(50),
	
	CONSTRAINT pk_user_id PRIMARY KEY (user_id),
	CONSTRAINT uq_username UNIQUE (username)
);

CREATE TABLE property (
	
	property_id serial,
	landlord_id int NOT NULL,
	renter_id int,
	description varchar(999),
	neighborhood varchar(255) NOT NULL,
	house_number int NOT NULL,
	street varchar(255) NOT NULL,
	city varchar(50) NOT NULL,
	state varchar(2) NOT NULL,
	zipcode varchar(5) NOT NULL,
	monthly_rent int NOT NULL,
	type varchar(255) NOT NULL,
	bedrooms int NOT NULL,
	bathrooms numeric(2,1) NOT NULL,
	area int NOT NULL,
	heating_type varchar(255) NOT NULL,
	has_ac boolean NOT NULL,
	is_smoking boolean NOT NULL,
	are_pets_allowed boolean NOT NULL,
	parking varchar(255) NOT NULL,
	
	CONSTRAINT pk_property_id PRIMARY KEY (property_id),
	CONSTRAINT fk_landlord_id FOREIGN KEY (landlord_id) REFERENCES users (user_id),
	CONSTRAINT fk_renter_id FOREIGN KEY (renter_id) REFERENCES users (user_id),
--	CONSTRAINT chk_only_digits_in_zip CHECK (zipcode ~ '^\d*$'),
	CONSTRAINT chk_rent_positive_rent_amount CHECK (monthly_rent > 0),
	CONSTRAINT chk_rent_positive_bedroom_amount CHECK (bedrooms > 0),
	CONSTRAINT chk_rent_positive_bathroom_amount CHECK (bathrooms > 0),
	CONSTRAINT chk_rent_positive_area_amount CHECK (area > 0),
	CONSTRAINT chk_positive_house_num CHECK (house_number > 0)
);

CREATE TABLE photo (
	photo_id serial NOT NULL,
	photo_path varchar(255) NOT NULL,
	
	CONSTRAINT pk_photo_id PRIMARY KEY (photo_id)
);

CREATE TABLE photo_property (
	photo_id int NOT NULL,
	property_id int NOT NULL,
	
	CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property (property_id),
	CONSTRAINT fk_photo_id FOREIGN KEY (photo_id) REFERENCES photo (photo_id),
	CONSTRAINT pk_comp_photo_property PRIMARY KEY(photo_id, property_id)
);



CREATE TABLE maintenance_request (
	request_id serial,
	worker_id int,
	property_id int NOT NULL,
	title varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	category varchar(255) NOT NULL,
	request_date date NOT NULL DEFAULT CURRENT_DATE ,
	complete_date date,
	
	CONSTRAINT pk_request PRIMARY KEY (request_id),
	CONSTRAINT fk_worker_id FOREIGN KEY (worker_id) REFERENCES users (user_id),
	CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property (property_id)
);



CREATE TABLE invoice (

	invoice_id serial,
	landlord_id int NOT NULL,
	renter_id int NOT NULL,
	property_id int NOT NULL,
	amount_due int NOT NULL,
	amount_paid int DEFAULT 0,
	status int GENERATED ALWAYS AS (amount_due - amount_paid) STORED,
	created_date date NOT NULL DEFAULT CURRENT_DATE,
	due_date date NOT NULL DEFAULT CURRENT_DATE + 30,
	paid_date date,

	CONSTRAINT pk_invoice_id PRIMARY KEY (invoice_id),
	CONSTRAINT fk_landlord_id FOREIGN KEY (landlord_id) REFERENCES users (user_id),
	CONSTRAINT fk_renter_id FOREIGN KEY (renter_id) REFERENCES users (user_id),
	CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property (property_id)
);

COMMIT;