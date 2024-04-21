-- Create tables with appropriate foreign keys

DROP TABLE IF EXISTS transaction_items;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS employee;

CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) UNIQUE NOT NULL,
    category_description TEXT
);

CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_first_name VARCHAR(255) NOT NULL,
    employee_last_name VARCHAR(255) NOT NULL,
    employee_phone VARCHAR(255) NOT NULL,
    employee_job_title VARCHAR(255) NOT NULL
);

CREATE TABLE item (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    item_quantity INT NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    item_is_fast_seller BOOLEAN NOT NULL,
    item_shelf_limit INT NOT NULL,
    item_is_discontinued BOOLEAN NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATE NOT NULL,
    transaction_details VARCHAR(255) NOT NULL,
    transaction_item_count INT NOT NULL,
    transaction_total DECIMAL(10, 2) NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

CREATE TABLE transaction_items (
    transaction_id INT,
    item_id INT,
    PRIMARY KEY (transaction_id, item_id),
    FOREIGN KEY (transaction_id) REFERENCES transaction (transaction_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id)
);

-- Insert data ensuring foreign key constraints are respected

INSERT INTO category (category_name, category_description) VALUES
('Dairy', 'milk, cheese, and dairy products'),
('Beverages', 'Soft drinks, juices'),
('Bakery', 'Bread, pastries, and other baked goods'),
('Produce', 'Fruits and vegetables'),
('Meat', 'Fresh and processed meats'),
('Unspecified', 'Default category for uncategorized items');

INSERT INTO employee (employee_first_name, employee_last_name, employee_phone, employee_job_title) VALUES
('John', 'Doe', '555-0101', 'Cashier'),
('Jane', 'Smith', '555-0102', 'Manager'),
('Jim', 'Beam', '555-0103', 'Stock Clerk'),
('Emily', 'Roe', '555-0104', 'Assistant Manager'),
('Sam', 'Tailor', '555-0105', 'Sales Associate');

INSERT INTO item (item_name, item_quantity, item_price, item_is_fast_seller, item_shelf_limit, item_is_discontinued, category_id) VALUES
('Milk', 100, 2.99, TRUE, 50, FALSE, 1),
('Orange Juice', 80, 3.49, TRUE, 50, FALSE, 2),
('Whole Wheat Bread', 50, 1.99, TRUE, 30, FALSE, 3),
('Apples', 100, 0.99, TRUE, 50, FALSE, 4),
('Chicken', 60, 5.49, FALSE, 40, FALSE, 5),
('Generic Soda', 150, 1.50, TRUE, 75, FALSE, 2),
('Chocolate Chip Cookies', 45, 2.99, TRUE, 25, FALSE, 3),
('Romaine Lettuce', 85, 1.89, TRUE, 45, FALSE, 4),
('T-Bone Steak', 40, 12.99, FALSE, 20, FALSE, 5),
('Expired Milk', 0, 0.99, FALSE, 50, TRUE, 1); -- discontinued item

INSERT INTO transaction (transaction_date, transaction_details, transaction_item_count, transaction_total, employee_id) VALUES
('2024-04-20', 'Success', 5, 45.20, 2),
('2024-04-21', 'Success', 3, 20.45, 1),
('2024-04-22', 'Failed', 0, 0.00, 3),  -- failed transaction
('2024-04-23', 'Success', 2, 25.48, 4);

INSERT INTO transaction_items (transaction_id, item_id) values
-- should of used item names here
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 1),
(2, 3),
(4, 6),
(4, 7);