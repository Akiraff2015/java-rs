-- User tables
CREATE TABLE UserTable(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    register_date NOT NULL
);

-- Barrows
CREATE TABLE BarrowsTable (
    id INTEGER UNIQUE NOT NULL,
    name TEXT NOT NULL,
    price INTEGER NOT NULL,
    short_price TEXT NOT NULL,
    last_update TEXT NOT NULL
);