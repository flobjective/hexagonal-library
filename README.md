Experimental app for exploring DDD tactical patterns and more...

## Overview

Hexagonal architecture consisting of currently modules:

- Inventory
- Circulation

See miro board: https://miro.com/app/board/o9J_lB2j3Ac=/?invite_link_id=718378742764

## Setup

Start the setup with 

```shell
docker-compose up
./mvnw mn:run
```


## Interacting with the API

Curl commands

Add a book with an ISBN to the inventory (and circulation per event):
```shell
curl -X POST -H "Content-Type: application/json" \
-d '{"isbn": "0345418778"}' \
http://localhost:8080/book/add
```

List books for circulation:

```shell
curl http://localhost:8080/circulation/available // currently available books
curl http://localhost:8080/circulation/issued // currently issued (=not available) books

```