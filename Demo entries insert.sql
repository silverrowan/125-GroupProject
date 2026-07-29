INSERT INTO users (user_id, username, password, first_name, last_name, email, role, phone) 
	VALUES (1, "a", 123123123,"a","a","a@a","Admin","0"),
		(2, "t", 123123123,"a","a","t@a","Travel Agent","0"),
		(3, "c", 123123123,"a","a","c@a","Customer","0"),
		(4, "ad", 123123123,"a","a","ad@a","Admin","0"),
		(5, "tr", 123123123,"a","a","tr@a","Travel Agent","0"),
		(6, "cu", 123123123,"a","a","cu@a","Customer","0");
INSERT INTO customers (customer_id, user_id)
	VALUES (1, 3),
		(2, 6);
INSERT INTO employees (user_id)
	VALUES (1),
		(2),
        (4),
        (5);

INSERT INTO destinations ( destination_id, destination_name, country_region, duration_days, duration_nights, base_price, total_estimated_cost )
	VALUES ( 1, "dest01", "region01", 3, 4, 6000, 6000 ),
		( 2, "dest02", "region02", 2, 1, 2000, 2000 );
                
INSERT INTO trips ( trip_id, destination_id, trip_title, departure_date, return_date, max_travelers )
	VALUES ( 1, 1, "Trip01 - to dest01", '2026-01-01', '2026-01-05', 20 ),
		( 2, 1, "Trip02 - to dest01", '2026-02-01', '2026-02-05', 20 ),
		( 3, 2, "Trip01 - to dest02", '2026-01-01', '2026-01-05', 20 ),
		( 4, 2, "Trip02 - to dest02", '2026-02-01', '2026-02-05', 20 );

INSERT INTO bookings ( customer_id, trip_id, booking_date, number_of_travelers )
	VALUES ( 1, 4, '2025-12-12', 2 ),
		(1, 1, '2025-12-30', 1 ),
		(2, 1, '2025-12-15', 2 );