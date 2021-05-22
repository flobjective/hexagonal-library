CREATE TABLE users (
    id uuid not null primary key,
    username varchar(255) not null
);
CREATE TABLE circulation_book (
  id uuid not null primary key,
  inventory_number varchar(36) not null unique
);
CREATE TABLE circulation_book_issue (
  id uuid not null primary key,
  book_id uuid not null,
  user_id uuid not null,
  expiration_time timestamp,
  is_current boolean,
  CONSTRAINT fk_circulation_book_issue_bookId
    FOREIGN KEY(book_id)
    REFERENCES circulation_book(id),
  CONSTRAINT fk_circulation_book_issue_userId
    FOREIGN KEY(user_id)
    REFERENCES users(id)
);
CREATE TABLE circulation_book_issue_renewal (
  id uuid not null primary key,
  issue_id uuid not null,
  renewal_time timestamp,
  CONSTRAINT fk_circulation_book_issue_renewal_issueId
    FOREIGN KEY(issue_id)
    REFERENCES circulation_book_issue(id)
);
