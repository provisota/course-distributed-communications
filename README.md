# Getting Started

### Build and Deploy

* run `mvn clean install` from the root path of the project to build the project
* run `docker-compose up --build` from the root path of the project to Deploy locally all required services

### Running the application

After services will be deployed the following API will be exposed for you

* **GET** http://localhost:8091/api/v1/authors to get the list of all authors
  ```
    [
      {
        "id": 1,
        "firstName": "Loreth Anne",
        "lastName": "White - v2"
      },
      {
        "id": 2,
        "firstName": "Lisa",
        "lastName": "Regan - v2"
      },
      {
        "id": 3,
        "firstName": "Ty",
        "lastName": "Patterson - v2"
      }
    ]
  ```
* **GET** http://localhost:8092/api/v1/books to get the list of all books
  ```
    [
      {
        "id": 1,
        "title": "Semiosis: A Novel - v2",
        "pages": 326,
        "authorId": 1
      },
      {
        "id": 2,
        "title": "The Loosening Skin - v2",
        "pages": 132,
        "authorId": 1
      },
      {
        "id": 3,
        "title": "Ninefox Gambit - v2",
        "pages": 384,
        "authorId": 2
      },
      {
        "id": 4,
        "title": "Raven Stratagem - v2",
        "pages": 400,
        "authorId": 3
      },
      {
        "id": 5,
        "title": "Revenant Gun - v2",
        "pages": 466,
        "authorId": 3
      }
    ]
  ```
* **GET** http://localhost:8093/api/v1/dashboard/rest to get an object contained all authors and all books aggregated from corresponding services via **REST**
* **GET** http://localhost:8093/api/v1/dashboard/grpc to get an object contained all authors and all books aggregated from corresponding services via **GRPC** 
* **PUT** http://localhost:8092/api/v1/books to add new Book with the Author
  ####Body
  ```
    {
        "title": "New Book",
        "pages": 501,
        "authorId": 4,
        "firstName": "Omar",
        "lastName": "Khayyam"
    }
  ```
  in response, you'll receive a newly created Book.

Once a new book is added new event with BookAndAuthor info should be posted to the RabbitMQ queue.

Authors MS should consume an event, that contains the BookAndAuthor object, from the RabbitMQ and check if the author exists in the local storage (check AuthorService class). 
If the author is absent – it should be added to the local storage.


