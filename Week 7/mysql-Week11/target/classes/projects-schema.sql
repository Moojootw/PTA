DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS project;


CREATE TABLE project (
  project_id INT AUTO_INCREMENT PRIMARY KEY,
  project_name VARCHAR(128) NOT NULL,
  estimated_hours DECIMAL(7,2),
  actual_hours DECIMAL(7,2),
  difficulty INT,
  notes TEXT
 );

CREATE TABLE category (
	category_id INT AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(128) NOT NULL
);

CREATE TABLE step (
	step_id INT AUTO_INCREMENT PRIMARY KEY,
	project_id INT,
	step_text text NOT NULL,
	step_order INT NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE material (
	material_id INT AUTO_INCREMENT PRIMARY KEY,
	project_id INT,
	material_name VARCHAR(128) NOT NULL,
	num_required INT,
	cost DECIMAL(7,2),
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_category (
	project_id INT,
	category_id INT,
	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
);
