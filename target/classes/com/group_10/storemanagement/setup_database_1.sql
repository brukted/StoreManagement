CREATE DATABASE IF NOT EXISTS store_management;

USE store_management;

CREATE TABLE IF NOT EXISTS Customer(
    customer_id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE,
    phone_number VARCHAR(15) UNIQUE,
    age INT,
    sex ENUM("Male", "Female"),
    customer_city VARCHAR(30),
    customer_street VARCHAR(30),
    customer_house_number INT,
    CHECK(age >= 18),
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS Store(
    store_id INT AUTO_INCREMENT NOT NULL,
    store_name VARCHAR(30) UNIQUE NOT NULL,
    store_city VARCHAR(30) NOT NULL,
    store_street VARCHAR(30) NOT NULL,
    store_house_number VARCHAR(30) NOT NULL,
    PRIMARY KEY(store_id)
);

CREATE TABLE IF NOT EXISTS Employee(
    employee_id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    age INT NOT NULL,
    sex ENUM("Male", "Female") NOT NULL,
    city VARCHAR(30) NOT NULL,
    street VARCHAR(30) NOT NULL,
    house_number INT NOT NULL CHECK(age >= 18),
    branch_id INT NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES Store(store_id),
    PRIMARY KEY(employee_id)
);


CREATE TABLE IF NOT EXISTS Sale(
    sale_id INT AUTO_INCREMENT NOT NULL,
    date_of_sale DATETIME NOT NULL,
    employee_id INT NOT NULL,
    customer_id INT NOT NULL,
    store_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (store_id) REFERENCES Store(store_id),
    PRIMARY KEY (sale_id)
);

CREATE TABLE IF NOT EXISTS Payment(
    payment_id INT AUTO_INCREMENT NOT NULL,
    customer_id INT NOT NULL,
    sale_id INT NOT NULL,
    amount DECIMAL(7, 2) NOT NULL,
    CHECK(amount > 0),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (sale_id) REFERENCES Sale(sale_id),
    PRIMARY KEY (payment_id)
);

CREATE TABLE IF NOT EXISTS Product(
    product_id INT AUTO_INCREMENT NOT NULL,
    product_name VARCHAR(30) UNIQUE NOT NULL,
    unit_price DECIMAL(7, 2) NOT NULL,
    category VARCHAR(30) NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS Stock(
    stock_id INT AUTO_INCREMENT NOT NULL,
    stock_name VARCHAR(30),
    stock_location VARCHAR(30),
    stock_store_id INT NOT NULL,
    stock_product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (stock_store_id) REFERENCES Store(store_id),
    FOREIGN KEY (stock_product_id) REFERENCES Product(product_id),
    PRIMARY KEY (stock_id)
);

CREATE TABLE IF NOT EXISTS SaleItem(
    item_id INT AUTO_INCREMENT NOT NULL,
    type VARCHAR(30) NOT NULL,
    item_sale_id INT,
    item_product_id INT,
    quantity INT NOT NULL,
    CHECK(quantity > 0),
    FOREIGN KEY (item_sale_id) REFERENCES Sale(sale_id),
    FOREIGN KEY (item_product_id) REFERENCES Product(product_id),
    PRIMARY KEY (item_id)
);

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Brannon",
        "O'Reilly",
        "Giovanni.Abshire69@yahoo.com",
        "0923454634",
        36,
        "Male",
        "Haleytown",
        "Edwin Point",
        3466
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Bessie",
        "Bogan",
        "Laury_Zieme64@gmail.com",
        "0923456634",
        61,
        "Male",
        "East Darrel",
        "Roger Viaduct",
        6693
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Katlyn",
        "Torp",
        "Ima.Gorczany@hotmail.com",
        "0973454634",
        31,
        "Male",
        "Ezekieltown",
        "Courtney Trail",
        6940
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Frankie",
        "Heaney",
        "Magdalen_Jones@yahoo.com",
        "0923454639",
        31,
        "Male",
        "Lake Shaina",
        "Chadrick Mill",
        8055
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Lambert",
        "Kub",
        "Randal_Ryan43@yahoo.com",
        "09234543343",
        22,
        "Male",
        "Thomasville",
        "Dibbert Roads",
        9671
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Price",
        "Bergstrom",
        "Sofia_Mante89@gmail.com",
        "0963454634",
        18,
        "Male",
        "Lorinestad",
        "Drew Corner",
        6046
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Christelle",
        "Jacobi",
        "Marcelo_Schaefer@hotmail.com",
        "092345463476",
        73,
        "Male",
        "Hartmannmouth",
        "Yolanda Crescent",
        5954
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Ken",
        "Rau",
        "Lucio_Connelly@gmail.com",
        "092345463434",
        73,
        "Male",
        "Prohaskaport",
        "Mozell Orchard",
        1591
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Kavon",
        "Harvey",
        "Bobbie.Tromp@gmail.com",
        "09234546354",
        75,
        "Male",
        "Dietrichport",
        "Jerald Trafficway",
        5106
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Michale",
        "Weissnat",
        "Barney93@gmail.com",
        "09234546433",
        64,
        "Male",
        "South Earnestfurt",
        "Isadore Tunnel",
        5163
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Kian",
        "Waters",
        "Nannie.Larkin61@yahoo.com",
        "09234546455",
        31,
        "Male",
        "South Janae",
        "Cathrine Burg",
        4209
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Irving",
        "Quitzon",
        "Alf75@hotmail.com",
        "09234543454",
        76,
        "Male",
        "East Fernando",
        "Ryan Stream",
        9078
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Flo",
        "Stehr",
        "Desiree72@hotmail.com",
        "09234546546",
        19,
        "Male",
        "Karleymouth",
        "Irma Lodge",
        9326
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Cristopher",
        "Heathcote",
        "Felicia.Johnston@yahoo.com",
        "0923454656",
        80,
        "Male",
        "Pollichchester",
        "Juana Valley",
        6952
    );

INSERT INTO Customer(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        customer_city,
        customer_street,
        customer_house_number
    )
VALUES(
        "Freda",
        "Hauck",
        "Augustus.Runte@yahoo.com",
        "09234546765",
        44,
        "Male",
        "Port Micheal",
        "Dare Hill",
        1028
    );

INSERT INTO Product(product_name, unit_price, category)
VALUES("Fancy Villa", 112.00, "Home");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Mr. X's", 120.00, "Beauty");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Handy Pillar", 1.00, "Tools");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Football", 912.00, "Sports");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Think Like a freak", 362.00, "Books");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Call of Duty", 234.00, "Games");

INSERT INTO Product(product_name, unit_price, category)
VALUES("High capacity AC unit", 116.00, "Industrial");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Galaxy A20", 391.00, "Electronics");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Fresh Onion", 427.00, "Grocery");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Mofii Mouse", 241.00, "Electronics");

INSERT INTO Product(product_name, unit_price, category)
VALUES("HP Envy x360", 251.00, "Computers");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Freaknomics", 335.00, "Books");

INSERT INTO Product(product_name, unit_price, category)
VALUES("4HP Electric Motor", 78.00, "Industrial");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Hand Torch", 643.00, "Tools");

INSERT INTO Product(product_name, unit_price, category)
VALUES("Start With WHY", 23.00, "Books");

INSERT INTO Store(
        store_name,
        store_city,
        store_street,
        store_house_number
    )
VALUES(
        "4 Kilo Branch",
        "Addis Ababa",
        "Main Road",
        4286
    );

INSERT INTO Store(
        store_name,
        store_city,
        store_street,
        store_house_number
    )
VALUES(
        "5 Kilo Branch",
        "Addis Ababa",
        "Main Road",
        4043
    );

INSERT INTO Store(
        store_name,
        store_city,
        store_street,
        store_house_number
    )
VALUES(
        "6 Kilo Branch",
        "Addis Ababa",
        "Main Road",
        5563
    );

INSERT INTO Store(
        store_name,
        store_city,
        store_street,
        store_house_number
    )
VALUES(
        "7 Kilo Branch",
        "Addis Ababa",
        "Main Road",
        5543
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Thora",
        "Hickle",
        "Sandra2@gmail.com",
        "0951962897",
        42,
        "Female",
        "Kendricktown",
        "Heidenreich Groves",
        2958,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Donavon",
        "Blick",
        "Pamela66@gmail.com",
        "0955558529",
        54,
        "Female",
        "New Sonia",
        "Ullrich Row",
        3451,3
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Nickolas",
        "Mann",
        "Effie73@gmail.com",
        "0955395697",
        44,
        "Female",
        "West Arden",
        "Lexi Crossing",
        2546,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Gianni",
        "Tremblay",
        "Tremayne.Torp@gmail.com",
        "0967321013",
        77,
        "Female",
        "New Garlandport",
        "Blaze Junction",
        1878,4
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Deshaun",
        "Reynolds",
        "Deion54@hotmail.com",
        "0961693898",
        46,
        "Female",
        "Homenickport",
        "Jordan Stravenue",
        1342,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Bret",
        "Klocko",
        "Alfonzo41@gmail.com",
        "0959469861",
        52,
        "Female",
        "Lake Kelleymouth",
        "Langworth Tunnel",
        7318,2
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Marilyne",
        "Kovacek",
        "Vernie.Ebert@gmail.com",
        "0962482096",
        42,
        "Female",
        "New Theo",
        "Reichert Union",
        4939,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Gabe",
        "Dibbert",
        "Guadalupe_Gutmann34@hotmail.com",
        "0971768623",
        82,
        "Female",
        "Dickensville",
        "Cristina Falls",
        2440,4
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Bell",
        "Cronin",
        "Bret.Upton@gmail.com",
        "0943329832",
        42,
        "Female",
        "Leathaview",
        "Jamil Prairie",
        8484,2
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Betsy",
        "Crist",
        "Mary94@hotmail.com",
        "0949172488",
        58,
        "Female",
        "Ryanport",
        "Kuvalis Trail",
        8841,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Juwan",
        "Bartoletti",
        "Ashtyn_Turner@hotmail.com",
        "0928159918",
        20,
        "Female",
        "Cormiershire",
        "Schmidt Garden",
        6068,3
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Pinkie",
        "Powlowski",
        "Aliyah17@gmail.com",
        "0988189393",
        60,
        "Female",
        "Mylenetown",
        "Cale Knoll",
        8672,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Lynn",
        "Thiel",
        "Jaclyn_Gutkowski@hotmail.com",
        "0987209077",
        62,
        "Female",
        "Lake Pansy",
        "Regan Forks",
        3631,1
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Alex",
        "Boehm",
        "Deon.Huels@yahoo.com",
        "0941304179",
        84,
        "Female",
        "Willchester",
        "Colten Trace",
        9337,3
    );

INSERT INTO Employee(
        first_name,
        last_name,
        email,
        phone_number,
        age,
        sex,
        city,
        street,
        house_number,branch_id
    )
VALUES(
        "Kenyon",
        "Turcotte",
        "Ethel_Koepp@hotmail.com",
        "0910855592",
        58,
        "Female",
        "Charleneland",
        "Kian Union",
        2516,2
    );

INSERT INTO Sale(date_of_sale, employee_id, customer_id, store_id)
VALUES("2020-3-15 07:06:21", 1, 14, 1);

INSERT INTO Sale(date_of_sale, employee_id, customer_id, store_id)
VALUES("2021-12-01 15:02:06", 7, 13, 1);

INSERT INTO Sale(date_of_sale, employee_id, customer_id, store_id)
VALUES("2021-6-5 02:33:46", 3, 1, 3);

INSERT INTO Sale(date_of_sale, employee_id, customer_id, store_id)
VALUES("2020-1-13 23:17:53", 8, 6, 1);

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(4, 2, 5,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(3, 1, 7,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(2, 10, 5,"type-2");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(1, 8, 9,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(1, 6, 10,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(4, 14, 8,"type-3");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(3, 12, 10,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(2, 7, 7,"type-1");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(1, 12, 9,"type-3");

INSERT INTO SaleItem(item_sale_id, item_product_id, quantity,type)
VALUES(1, 13, 8,"type-4");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 6, 10,"idk-0","5 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 13, 10,"idk-1","6 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 8, 10,"idk-2","6 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 12, 90,"idk-3","4 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(3, 10, 30,"idk-4","7 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(3, 7, 50,"idk-5","7 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(4, 1, 40,"idk-6","7 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(4, 12, 77,"idk-7","7 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 2, 45,"idk-8","7 Kilo Branch");

INSERT INTO Stock(stock_store_id, stock_product_id, quantity,stock_name,stock_location)
VALUES(1, 14, 33,"idk-9","6 Kilo Branch");

INSERT INTO Payment(customer_id, sale_id, amount)
VALUES(14, 1, 123.00);

INSERT INTO Payment(customer_id, sale_id, amount)
VALUES(13, 1, 455.90);

INSERT INTO Payment(customer_id, sale_id, amount)
VALUES(1, 2, 5665);

INSERT INTO Payment(customer_id, sale_id, amount)
VALUES(1, 3, 56467);

INSERT INTO Payment(customer_id, sale_id, amount)
VALUES(6, 4, 8897);