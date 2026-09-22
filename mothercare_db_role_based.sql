CREATE DATABASE IF NOT EXISTS mothercare_db
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mothercare_db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS reminder;
DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS diagnosis;
DROP TABLE IF EXISTS medical_report;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS home_visit;
DROP TABLE IF EXISTS mother_assignment;
DROP TABLE IF EXISTS health_record;
DROP TABLE IF EXISTS baby_growth;
DROP TABLE IF EXISTS vaccination;
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS pregnancy;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS exercise_plan;
DROP TABLE IF EXISTS nutrition_guide;
DROP TABLE IF EXISTS mother_profile;
DROP TABLE IF EXISTS midwife_profile;
DROP TABLE IF EXISTS doctor_profile;
DROP TABLE IF EXISTS admin_profile;
DROP TABLE IF EXISTS `user`;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================================================
-- 1. COMMON USER TABLE
-- =========================================================
CREATE TABLE `user` (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    date_of_birth DATE,
    role ENUM('MOTHER','MIDWIFE','DOCTOR','ADMIN') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================================
-- 2. ROLE-SPECIFIC PROFILE TABLES
-- =========================================================
CREATE TABLE mother_profile (
    user_id INT PRIMARY KEY,
    mother_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE midwife_profile (
    user_id INT PRIMARY KEY,
    midwife_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    area VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctor_profile (
    user_id INT PRIMARY KEY,
    doctor_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    specialization VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE admin_profile (
    user_id INT PRIMARY KEY,
    admin_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 3. PREGNANCY
-- =========================================================
CREATE TABLE pregnancy (
    pregnancy_id INT AUTO_INCREMENT PRIMARY KEY,
    mother_id INT NOT NULL,
    start_date DATE,
    due_date DATE,
    current_week INT,
    status VARCHAR(50) DEFAULT 'Active',
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 4. APPOINTMENT
-- =========================================================
CREATE TABLE appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    mother_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    reason TEXT,
    status VARCHAR(30) DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor_profile(doctor_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- =========================================================
-- 5. MOTHER ASSIGNMENT
-- =========================================================
CREATE TABLE mother_assignment (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    midwife_id INT NOT NULL,
    mother_id INT NOT NULL,
    assigned_date DATE NOT NULL,
    status VARCHAR(30) DEFAULT 'Active',
    FOREIGN KEY (midwife_id) REFERENCES midwife_profile(midwife_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 6. HOME VISIT
-- =========================================================
CREATE TABLE home_visit (
    visit_id INT AUTO_INCREMENT PRIMARY KEY,
    assignment_id INT NOT NULL,
    visit_date DATE NOT NULL,
    observations TEXT,
    advice TEXT,
    pregnancy_progress TEXT,
    FOREIGN KEY (assignment_id) REFERENCES mother_assignment(assignment_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 7. HEALTH RECORD
-- =========================================================
CREATE TABLE health_record (
    health_id INT AUTO_INCREMENT PRIMARY KEY,
    pregnancy_id INT NOT NULL,
    record_date DATE NOT NULL,
    weight DECIMAL(5,2),
    blood_pressure VARCHAR(20),
    blood_sugar DECIMAL(6,2),
    water_intake DECIMAL(5,2),
    notes TEXT,
    FOREIGN KEY (pregnancy_id) REFERENCES pregnancy(pregnancy_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 8. BABY GROWTH
-- =========================================================
CREATE TABLE baby_growth (
    growth_id INT AUTO_INCREMENT PRIMARY KEY,
    pregnancy_id INT NOT NULL,
    week INT,
    weight DECIMAL(6,2),
    length DECIMAL(6,2),
    notes TEXT,
    FOREIGN KEY (pregnancy_id) REFERENCES pregnancy(pregnancy_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 9. VACCINATION
-- =========================================================
CREATE TABLE vaccination (
    vaccination_id INT AUTO_INCREMENT PRIMARY KEY,
    mother_id INT NOT NULL,
    vaccine_name VARCHAR(150) NOT NULL,
    vaccination_date DATE,
    next_date DATE,
    status VARCHAR(30) DEFAULT 'Pending',
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 10. MEDICAL REPORT
-- =========================================================
CREATE TABLE medical_report (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    mother_id INT NOT NULL,
    report_title VARCHAR(200),
    report_file VARCHAR(255),
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctor_profile(doctor_id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 11. DIAGNOSIS
-- =========================================================
CREATE TABLE diagnosis (
    diagnosis_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    doctor_id INT NOT NULL,
    diagnosis TEXT NOT NULL,
    notes TEXT,
    diagnosis_date DATE NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor_profile(doctor_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- =========================================================
-- 12. PRESCRIPTION
-- =========================================================
CREATE TABLE prescription (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    diagnosis_id INT NOT NULL,
    medicine_name VARCHAR(150) NOT NULL,
    dosage VARCHAR(100),
    frequency VARCHAR(100),
    duration VARCHAR(100),
    FOREIGN KEY (diagnosis_id) REFERENCES diagnosis(diagnosis_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 13. REMINDER
-- =========================================================
CREATE TABLE reminder (
    reminder_id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_id INT,
    mother_id INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    reminder_date DATE NOT NULL,
    reminder_time TIME,
    status VARCHAR(30) DEFAULT 'Pending',
    FOREIGN KEY (prescription_id) REFERENCES prescription(prescription_id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 14. NOTIFICATION
-- =========================================================
CREATE TABLE notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    mother_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- 15. FEEDBACK
-- =========================================================
CREATE TABLE feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    mother_id INT NOT NULL,
    rating INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (mother_id) REFERENCES mother_profile(mother_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (rating IS NULL OR rating BETWEEN 1 AND 5)
);

-- =========================================================
-- 16. ADMIN CONTENT MANAGEMENT
-- =========================================================
CREATE TABLE article (
    article_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES admin_profile(admin_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE exercise_plan (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES admin_profile(admin_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE nutrition_guide (
    nutrition_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES admin_profile(admin_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- =========================================================
-- INDEXES
-- =========================================================
CREATE INDEX idx_user_role ON `user`(role);
CREATE INDEX idx_pregnancy_mother ON pregnancy(mother_id);
CREATE INDEX idx_appointment_mother ON appointment(mother_id);
CREATE INDEX idx_appointment_doctor ON appointment(doctor_id);
CREATE INDEX idx_assignment_midwife ON mother_assignment(midwife_id);
CREATE INDEX idx_assignment_mother ON mother_assignment(mother_id);
CREATE INDEX idx_health_pregnancy ON health_record(pregnancy_id);
CREATE INDEX idx_growth_pregnancy ON baby_growth(pregnancy_id);
CREATE INDEX idx_report_mother ON medical_report(mother_id);
CREATE INDEX idx_report_doctor ON medical_report(doctor_id);
CREATE INDEX idx_diagnosis_appointment ON diagnosis(appointment_id);
CREATE INDEX idx_prescription_diagnosis ON prescription(diagnosis_id);
CREATE INDEX idx_reminder_mother ON reminder(mother_id);
CREATE INDEX idx_notification_mother ON notification(mother_id);
CREATE INDEX idx_feedback_mother ON feedback(mother_id);

-- =========================================================
-- SAMPLE USERS
-- Passwords are plain-text only for initial testing.
-- PHP backend should use password_hash() for real accounts.
-- =========================================================

INSERT INTO `user`
(name, email, password, phone, address, date_of_birth, role)
VALUES
('System Admin', 'admin@mothercare.com', 'admin123', '0710000000',
 'Galle', '1990-01-01', 'ADMIN');

INSERT INTO admin_profile (user_id)
VALUES (LAST_INSERT_ID());

INSERT INTO `user`
(name, email, password, phone, address, date_of_birth, role)
VALUES
('Demo Doctor', 'doctor@mothercare.com', 'doctor123', '0720000000',
 'Galle', '1985-05-10', 'DOCTOR');

INSERT INTO doctor_profile (user_id, specialization)
VALUES (LAST_INSERT_ID(), 'Obstetrics');

INSERT INTO `user`
(name, email, password, phone, address, date_of_birth, role)
VALUES
('Demo Midwife', 'midwife@mothercare.com', 'midwife123', '0730000000',
 'Galle', '1988-03-15', 'MIDWIFE');

INSERT INTO midwife_profile (user_id, area)
VALUES (LAST_INSERT_ID(), 'Galle');

INSERT INTO `user`
(name, email, password, phone, address, date_of_birth, role)
VALUES
('Demo Mother', 'mother@mothercare.com', 'mother123', '0740000000',
 'Galle', '1998-01-15', 'MOTHER');

INSERT INTO mother_profile (user_id)
VALUES (LAST_INSERT_ID());

SHOW TABLES;
