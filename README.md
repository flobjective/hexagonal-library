##

Curl commands

Add a book with an ISBN to the inventory :
```
curl -X POST -H "Content-Type: application/json" \
-d '{"isbn": "0345418778"}' \
http://localhost:8080/book/add
```