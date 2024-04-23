-- Drop existing tables in reverse order of dependencies
DROP TABLE IF EXISTS transaction_items;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS category;

-- Create the category table
CREATE TABLE category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    category_description VARCHAR(255)
);

-- Create the employee table
CREATE TABLE employee (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_first_name VARCHAR(255) NOT NULL,
    employee_last_name VARCHAR(255) NOT NULL,
    employee_phone VARCHAR(255) NOT NULL,
    employee_job_title VARCHAR(255) NOT NULL
);

-- Create the item table
CREATE TABLE item (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    item_quantity INT NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    item_is_fast_seller BOOLEAN NOT NULL,
    item_shelf_limit INT NOT NULL,
    item_is_discontinued BOOLEAN NOT NULL,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);

-- Create the transaction table
CREATE TABLE transaction (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATE NOT NULL,
    transaction_details VARCHAR(255) NOT NULL,
    transaction_item_count INT NOT NULL,
    transaction_total DECIMAL(10, 2) NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

-- Create the transaction_items table with ON DELETE SET NULL for item_id
CREATE TABLE transaction_items (
    transaction_id BIGINT NOT NULL,
    item_id BIGINT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transaction (transaction_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item (item_id) ON DELETE SET NULL,
    UNIQUE (transaction_id, item_id)
);

-- Insert data into category table
INSERT INTO category (category_name, category_description) VALUES
('Dairy', 'Refrigerated perishables'),
('Meat', 'Packaged'),
('Seafood', 'Packaged'),
('Grocery', 'General goods'),
('Produce', 'Fruits and Vegetables'),
('Merchandise', 'Non-food goods'),
('Frozen', 'Food items that need to be stored below freezing');

-- Insert data into employee table
INSERT INTO employee (employee_first_name, employee_last_name, employee_phone, employee_job_title) values
('Tom', 'Sawyer', '555-1000', 'Bagger'),
('Jane', 'Doe', '555-4324', 'Checker'),
('George', 'Miller', '555-5342', 'General Manager'),
('Taylor', 'Riles', '555-7939', 'Customer Service'),
('Ashely', 'Bailes', '555-4285', 'Grocery Manager'),
('Tyler', 'White', '555-1955', 'Assistant Grocery Manager'),
('Matt', 'Smith', '555-8385', 'Dairy Clerk'),
('Jim', 'Beam', '555-0103', 'Stock Clerk');

-- Insert data into item table
INSERT INTO item (item_name, item_quantity, item_price, item_shelf_limit, item_is_fast_seller, item_is_discontinued, category_id) VALUES
('Milk', 200, 4.99, 100, TRUE, FALSE, 1),
('Broccoli', 12, 2.99, 24, FALSE, FALSE, 5),
('Chicken', 60, 20.99, 30, TRUE, FALSE, 2),
('Eggs', 80, 5.99, 20, TRUE, FALSE, 1),
('Firewood', 60, 8.99, 80, FALSE, FALSE, 6),
('Bacon', 40, 7.99, 20, FALSE, FALSE, 2),
('Salmon', 20, 8.99, 10, FALSE, FALSE, 3),
('Fruitcake', 0, 3.99, 5, FALSE, TRUE, 4),
('Toy Guns', 5, 12.99, 3, FALSE, FALSE, 6),
('Gum', 30, 2.99, 10, FALSE, FALSE, 4),
('Sugar', 40, 3.99, 20, TRUE, FALSE, 4),
('Egg Nog', 0, 4.99, 20, TRUE, TRUE, 1),
('Apples', 80, 2.99, 20, TRUE, FALSE, 5),
('TV Dinner', 40, 3.99, 14, FALSE, FALSE, 7),
('Ice Cream', 80, 4.99, 60, TRUE, FALSE, 7);

-- Insert data into transaction table
INSERT INTO transaction (transaction_date, transaction_details, transaction_item_count, transaction_total, employee_id) VALUES
('2023-02-10', 'OK', 4, 40.96, 2),
('2023-02-11', 'Suspended', 3, 29.97, 4),
('2023-02-12', 'OK', 2, 11.98, 2),
('2023-02-13', 'Canceled', 3, 12.97, 3),
('2023-02-14', 'OK', 3, 8.97, 2);

-- Insert data into transaction_items table
INSERT INTO transaction_items (transaction_id, item_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 6),
(2, 7),
(2, 9),
(3, 10),
(3, 13),
(4, 11),
(4, 15),
(4, 4),
(5, 5),
(5, 8),
(5, 14);
