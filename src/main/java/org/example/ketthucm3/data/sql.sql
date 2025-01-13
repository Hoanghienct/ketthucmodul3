-- Tạo cơ sở dữ liệu
CREATE DATABASE TComplexManagement;

-- Sử dụng cơ sở dữ liệu
USE TComplexManagement;

-- Tạo bảng spaces
CREATE TABLE spaces (
    id VARCHAR(10) PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    area FLOAT CHECK (area > 20),
    floor INT CHECK (floor >= 1 AND floor <= 15),
    type VARCHAR(50) NOT NULL,
    price FLOAT CHECK (price > 1000000),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CHECK (TIMESTAMPDIFF(MONTH, start_date, end_date) >= 6)
);

-- Thêm một số dữ liệu mẫu
INSERT INTO spaces (id, status, area, floor, type, price, start_date, end_date)
VALUES

('002-01-05', 'Hạ tầng', 30, 7, 'Văn phòng trọn gói', 7000000, '2024-03-01', '2024-09-01');
