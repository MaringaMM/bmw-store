CREATE TABLE customer (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          first_name TEXT NOT NULL,
                          last_name TEXT NOT NULL,
                          email TEXT UNIQUE NOT NULL
);

CREATE TABLE product (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         name TEXT NOT NULL,
                         price REAL NOT NULL
);

CREATE TABLE `order` (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         customer_id INTEGER NOT NULL,
                         total_price REAL NOT NULL,
                         FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- composite primary key using both the order_id and product_id columns
CREATE TABLE order_product (
                               order_id INTEGER NOT NULL,
                               product_id INTEGER NOT NULL,
                               PRIMARY KEY (order_id, product_id),
                               FOREIGN KEY (order_id) REFERENCES `order`(id),
                               FOREIGN KEY (product_id) REFERENCES product(id)
);
