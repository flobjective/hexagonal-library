CREATE TABLE users (
    id uuid not null primary key,
    username varchar(255) not null
);
CREATE TABLE circulation_book_issue (
  id uuid not null primary key,
  user_id uuid not null,
  expiration_time timestamp,
  is_current boolean
);
CREATE TABLE circulation_book_issue_renewal (
  id uuid not null primary key,
  issue_id uuid not null,
  renewal_time timestamp,
  CONSTRAINT fk_circulation_book_issue_renewal_issueId
    FOREIGN KEY(issue_id)
    REFERENCES circulation_book_issue(id)
);
CREATE TABLE circulation_book_reservation (
      id uuid not null primary key,
      user_id uuid not null,
      expiration_time timestamp
);
CREATE TABLE circulation_book (
  id uuid not null primary key,
  inventory_number varchar(36) not null unique,
  current_book_issue_id uuid,
  current_book_reservation_id uuid,
  CONSTRAINT fk_circulation_book_current_issue
      FOREIGN KEY(current_book_issue_id)
      REFERENCES circulation_book_issue(id),
  CONSTRAINT fk_circulation_book_current_reservation
        FOREIGN KEY(current_book_reservation_id)
        REFERENCES circulation_book_reservation(id)
);
