CREATE TABLE event (
    id uuid not null primary key,
    event_name varchar(255) not null,
    event_payload jsonb,
    creation_date timestamptz not null,
    published_date timestamptz
);