Experimental app for exploring DDD tactical patterns and more...

## Overview

An application consisting of currently two modules:

- Inventory
- Circulation

See miro board: https://miro.com/app/board/o9J_lB2j3Ac=/?invite_link_id=718378742764

## Start the app

Start the setup with

```shell
docker-compose up // start dependencies
./mvnw mn:run // start the micronaut app
```

## Interacting with the API

### Curl commands

#### Adding a book to the inventory

This will add a book with an ISBN to the inventory and also replicates the book to be available for circulation:

```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"isbn": "0345418778"}' \
http://localhost:8080/book
```

#### Listing books available for circulation

This command will list all books that are currently available (e.g. not already issued to another reader).

```shell
curl http://localhost:8080/circulation/available
```

#### Issuing a book to a reader

Issuing a book to a reader using a bookId (e.g. `cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f`) and userId (
`3bfc01e8-b586-453c-923d-7776bfa39273` - this user always exists)

```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"userId": "3bfc01e8-b586-453c-923d-7776bfa39273"}' \
http://localhost:8080/circulation/available/cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f/issue 
```

#### Listing issued books for a specific reader

```shell
curl "http://localhost:8080/circulation/issued?userId=3bfc01e8-b586-453c-923d-7776bfa39273"
```

#### Returning a book from a reader

Returning a book from a reader using a bookId (e.g. `cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f`) and userId (
`3bfc01e8-b586-453c-923d-7776bfa39273`)

```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"userId": "3bfc01e8-b586-453c-923d-7776bfa39273"}' \
http://localhost:8080/circulation/issued/8db8f948-ada8-48b6-9a1d-ff3810bf6de7/return
```

#### Reserving a book to a reader

Reserving a book for a reader using a bookId (e.g. `cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f`) and userId (
`3bfc01e8-b586-453c-923d-7776bfa39273` - should be a different user)

```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"userId": "1bfc01e8-b586-453c-923d-7776bfa39273"}' \
http://localhost:8080/circulation/issued/8db8f948-ada8-48b6-9a1d-ff3810bf6de7/reserve
```


