ALTER TABLE registrations
    ADD COLUMN checked_in BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE registrations
    ADD COLUMN checked_in_at DATETIME(6);