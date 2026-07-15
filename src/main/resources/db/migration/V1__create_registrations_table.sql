CREATE TABLE registrations (

                               id BIGINT NOT NULL AUTO_INCREMENT,

                               name VARCHAR(100) NOT NULL,

                               email VARCHAR(150) NOT NULL,

                               phone_number VARCHAR(15) NOT NULL,

                               age INT NOT NULL,

                               address TEXT NOT NULL,

                               occupation VARCHAR(100) NOT NULL,

                               registration_group ENUM('EC', 'DT', 'LT', 'SLT') NOT NULL,

                               created_at DATETIME(6) NOT NULL,

                               updated_at DATETIME(6) NOT NULL,

                               PRIMARY KEY (id),

                               CONSTRAINT uk_registrations_email
                                   UNIQUE (email)

) ENGINE = InnoDB;