CREATE TABLE IF NOT EXISTS users
(
  id                      INT                                          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userType                ENUM ('USER', 'ADMINISTRATOR', 'SUPERVISOR') NOT NULL,
  first_name              VARCHAR(50)                                  NOT NULL,
  surname                 VARCHAR(50)                                  NOT NULL,
  email                   VARCHAR(50)                                  NOT NULL UNIQUE,
  username                VARCHAR(50)                                  NOT NULL UNIQUE,
  password                VARCHAR(500)                                 NOT NULL,
  date_of_birth           DATE,
  weight                  DOUBLE,
  height                  DOUBLE,
  rating                  DOUBLE,
  supervisor_id           INT,
  requested_supervisor_id INT
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS products
(
  id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(100) NOT NULL,
  image_url     VARCHAR(300),
  description   VARCHAR(400),
  calories      INT          NOT NULL,
  proteins      INT          NOT NULL,
  lipids        INT          NOT NULL,
  carbohydrates INT          NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS meals
(
  id           INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id      INT  NOT NULL,
  product_id   INT  NOT NULL,
  date         DATE NOT NULL,
  meal_time_id INT  NOT NULL,
  quantity     INT  NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (meal_time_id) REFERENCES meal_time (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS meal_time
(
  id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  meal_time VARCHAR(30) NOT NULL UNIQUE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS ratings
(
  id            INT    NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id       INT    NOT NULL,
  supervisor_id INT    NOT NULL,
  rating        DOUBLE NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS comments_for_users
(
  id              INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_of_comment TIMESTAMP     NOT NULL,
  mealDate        DATE          NOT NULL,
  user_id         INT           NOT NULL,
  commentator_id  INT           NOT NULL,
  comment         VARCHAR(2000) NOT NULL
) ENGINE = InnoDB;



