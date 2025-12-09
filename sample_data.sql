-- Sample Data for PC Sale POS System
-- Run this after importing the main database structure

-- Insert default admin user (password: admin123)
INSERT INTO users (username, full_name, password, role, status) VALUES 
('admin', 'Administrator', 'admin123', 'admin', 'active'),
('cashier1', 'John Cashier', 'cashier123', 'cashier', 'active'),
('manager1', 'Jane Manager', 'manager123', 'manager', 'active');

-- Insert categories
INSERT INTO categories (name, description) VALUES 
('Processors', 'CPU and processors for desktop and laptop computers'),
('Graphics Cards', 'GPU and video cards for gaming and professional work'),
('Memory', 'RAM modules and memory kits'),
('Storage', 'HDD, SSD and NVMe storage drives'),
('Motherboards', 'Desktop and server motherboards'),
('Power Supplies', 'PSU units for desktop computers'),
('Cases', 'Computer cases and chassis'),
('Cooling', 'CPU coolers, fans and liquid cooling systems'),
('Peripherals', 'Keyboards, mice, and other input devices'),
('Monitors', 'LCD, LED and gaming monitors');

-- Insert suppliers
INSERT INTO suppliers (name, contact_person, phone, email, address) VALUES 
('Tech Distributors Inc', 'Mike Johnson', '555-0101', 'mike@techdist.com', '123 Tech Street, Silicon Valley, CA'),
('PC Components Ltd', 'Sarah Williams', '555-0102', 'sarah@pccomp.com', '456 Hardware Ave, Austin, TX'),
('Global Electronics', 'David Chen', '555-0103', 'david@globalelec.com', '789 Circuit Blvd, Seattle, WA');

-- Insert sample products
INSERT INTO products (barcode, name, category_id, supplier_id, cost_price, selling_price, stock_quantity, reorder_level, unit, status) VALUES 
-- Processors
('CPU001', 'Intel Core i9-13900K', 1, 1, 550.00, 650.00, 12, 5, 'pcs', 'active'),
('CPU002', 'Intel Core i7-13700K', 1, 1, 380.00, 470.00, 18, 8, 'pcs', 'active'),
('CPU003', 'Intel Core i5-13600K', 1, 1, 280.00, 350.00, 25, 10, 'pcs', 'active'),
('CPU004', 'AMD Ryzen 9 7950X', 1, 2, 600.00, 700.00, 10, 5, 'pcs', 'active'),
('CPU005', 'AMD Ryzen 7 7700X', 1, 2, 350.00, 430.00, 20, 8, 'pcs', 'active'),
('CPU006', 'AMD Ryzen 5 7600X', 1, 2, 250.00, 310.00, 30, 10, 'pcs', 'active'),

-- Graphics Cards
('GPU001', 'NVIDIA GeForce RTX 4090', 2, 1, 1600.00, 1800.00, 5, 3, 'pcs', 'active'),
('GPU002', 'NVIDIA GeForce RTX 4080', 2, 1, 1100.00, 1300.00, 8, 4, 'pcs', 'active'),
('GPU003', 'NVIDIA GeForce RTX 4070 Ti', 2, 1, 750.00, 900.00, 12, 6, 'pcs', 'active'),
('GPU004', 'NVIDIA GeForce RTX 4060 Ti', 2, 1, 380.00, 480.00, 20, 8, 'pcs', 'active'),
('GPU005', 'AMD Radeon RX 7900 XTX', 2, 2, 900.00, 1050.00, 10, 5, 'pcs', 'active'),
('GPU006', 'AMD Radeon RX 7900 XT', 2, 2, 750.00, 880.00, 12, 6, 'pcs', 'active'),
('GPU007', 'AMD Radeon RX 7800 XT', 2, 2, 480.00, 580.00, 15, 7, 'pcs', 'active'),

-- Memory
('RAM001', 'Corsair Vengeance 32GB (2x16GB) DDR5-6000', 3, 1, 140.00, 180.00, 40, 15, 'pcs', 'active'),
('RAM002', 'G.Skill Trident Z5 32GB (2x16GB) DDR5-6400', 3, 2, 160.00, 200.00, 35, 15, 'pcs', 'active'),
('RAM003', 'Kingston Fury Beast 16GB (2x8GB) DDR4-3200', 3, 1, 50.00, 70.00, 60, 20, 'pcs', 'active'),
('RAM004', 'Crucial Ballistix 32GB (2x16GB) DDR4-3600', 3, 3, 95.00, 130.00, 45, 18, 'pcs', 'active'),

-- Storage
('SSD001', 'Samsung 990 Pro 2TB NVMe', 4, 1, 180.00, 230.00, 30, 12, 'pcs', 'active'),
('SSD002', 'Samsung 980 Pro 1TB NVMe', 4, 1, 110.00, 145.00, 45, 18, 'pcs', 'active'),
('SSD003', 'WD Black SN850X 2TB NVMe', 4, 2, 190.00, 240.00, 25, 10, 'pcs', 'active'),
('SSD004', 'Crucial P5 Plus 1TB NVMe', 4, 3, 90.00, 120.00, 50, 20, 'pcs', 'active'),
('SSD005', 'Samsung 870 EVO 2TB SATA', 4, 1, 140.00, 180.00, 35, 15, 'pcs', 'active'),
('HDD001', 'Seagate BarraCuda 4TB 7200RPM', 4, 2, 85.00, 115.00, 40, 15, 'pcs', 'active'),

-- Motherboards
('MB001', 'ASUS ROG Maximus Z790 Hero', 5, 1, 520.00, 640.00, 10, 5, 'pcs', 'active'),
('MB002', 'MSI MPG Z790 Gaming Carbon WiFi', 5, 2, 420.00, 520.00, 12, 6, 'pcs', 'active'),
('MB003', 'Gigabyte X670E AORUS Master', 5, 1, 450.00, 560.00, 8, 4, 'pcs', 'active'),
('MB004', 'ASRock B650 Steel Legend', 5, 3, 180.00, 240.00, 20, 8, 'pcs', 'active'),

-- Power Supplies
('PSU001', 'Corsair RM1000x 1000W 80+ Gold', 6, 1, 160.00, 200.00, 25, 10, 'pcs', 'active'),
('PSU002', 'EVGA SuperNOVA 850W 80+ Gold', 6, 2, 120.00, 160.00, 30, 12, 'pcs', 'active'),
('PSU003', 'Seasonic Focus GX-750 750W 80+ Gold', 6, 3, 95.00, 130.00, 35, 15, 'pcs', 'active'),

-- Cases
('CASE001', 'Lian Li O11 Dynamic EVO', 7, 1, 130.00, 170.00, 15, 6, 'pcs', 'active'),
('CASE002', 'NZXT H7 Flow', 7, 2, 110.00, 145.00, 18, 8, 'pcs', 'active'),
('CASE003', 'Corsair 4000D Airflow', 7, 1, 85.00, 115.00, 25, 10, 'pcs', 'active'),

-- Cooling
('COOL001', 'Noctua NH-D15 CPU Cooler', 8, 1, 85.00, 110.00, 30, 12, 'pcs', 'active'),
('COOL002', 'Corsair iCUE H150i Elite LCD', 8, 2, 220.00, 280.00, 15, 6, 'pcs', 'active'),
('COOL003', 'Arctic Liquid Freezer II 280', 8, 3, 95.00, 125.00, 20, 8, 'pcs', 'active'),

-- Peripherals
('KB001', 'Logitech G Pro X Mechanical Keyboard', 9, 1, 110.00, 145.00, 25, 10, 'pcs', 'active'),
('KB002', 'Razer BlackWidow V3 Pro', 9, 2, 180.00, 230.00, 18, 8, 'pcs', 'active'),
('MOUSE001', 'Logitech G502 X Plus', 9, 1, 120.00, 155.00, 30, 12, 'pcs', 'active'),
('MOUSE002', 'Razer DeathAdder V3 Pro', 9, 2, 110.00, 145.00, 28, 12, 'pcs', 'active'),

-- Monitors
('MON001', 'ASUS ROG Swift PG279QM 27" 240Hz', 10, 1, 580.00, 720.00, 8, 4, 'pcs', 'active'),
('MON002', 'LG UltraGear 27GP950-B 27" 4K 144Hz', 10, 2, 650.00, 800.00, 6, 3, 'pcs', 'active'),
('MON003', 'Samsung Odyssey G7 32" Curved 240Hz', 10, 3, 520.00, 650.00, 10, 5, 'pcs', 'active');

-- Insert sample customers
INSERT INTO customers (customer_code, name, phone, email, address, points) VALUES 
('CUST00001', 'John Smith', '555-1001', 'john.smith@email.com', '123 Main St, Apt 4B, New York, NY', 250),
('CUST00002', 'Emily Johnson', '555-1002', 'emily.j@email.com', '456 Oak Ave, Los Angeles, CA', 180),
('CUST00003', 'Michael Brown', '555-1003', 'mbrown@email.com', '789 Elm Street, Chicago, IL', 320),
('CUST00004', 'Sarah Davis', '555-1004', 'sarahd@email.com', '321 Pine Rd, Houston, TX', 150),
('CUST00005', 'David Wilson', '555-1005', 'dwilson@email.com', '654 Maple Dr, Phoenix, AZ', 420);

-- Note: To create actual sales, use the POS system interface
-- This ensures proper stock management and transaction integrity

COMMIT;
