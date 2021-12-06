Experimental app for exploring DDD tactical patterns and more...

## Overview

Hexagonal architecture consisting of currently modules:

- Inventory
- Circulation

See miro board: https://miro.com/app/board/o9J_lB2j3Ac=/?invite_link_id=718378742764

## Start the app

Start the setup with 

```shell
docker-compose up // dependencies
./mvnw mn:run // start the micronaut app
```


## Interacting with the API

Curl commands

Add a book with an ISBN to the inventory (and circulation per event):
```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"isbn": "0345418778"}' \
http://localhost:8080/book/add
```

Issueing a book to a user using a bookId (cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f) and userId (3bfc01e8-b586-453c-923d-7776bfa39273)

```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"userId": "3bfc01e8-b586-453c-923d-7776bfa39273"}' \
http://localhost:8080/circulation/available/cd3b56c8-a8bd-4727-8b6d-9d1a1649ea5f/issue 
```

List books for circulation:

```shell
curl http://localhost:8080/circulation/available // currently available books
curl http://localhost:8080/circulation/issued?user={userId} // currently issued (=not available) books for a specific reader
```