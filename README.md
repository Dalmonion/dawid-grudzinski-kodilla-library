# Table of contents
* [General info](#general-info)
* [Entities content](#entities-content)
* [Services](#services)
* [Structure](#structure)
* [Tests](#tests)
* [Technologies](#technologies)
* [Setup](#setup)

# General info
I present to you a REST API which serves as library system.

# Entities content
The application consist of following entities:
* user entity - contains user id, firstname, lastname and the date of creating an account,
* book entity - contains book id, title, author name and date of publish,
* book record entity - contains record id, book entity id and status with following options to choose (RENTED, AVAILABLE, LOST),
* rental record entity - contains the book record id, user id, the date of borrowing and date of expected returning.

Example: There are 5 pieces of exactly the same book in the database. In book table there will be only 1 book because there
is no need to copy 5 times the same title. On the other hand in book records table there will be 5 rows, each with it own
status which represents is the book rented, available or lost.

# Services
The application contains following programmed services:
* /createUser - creates user (accepts input as JSON format object with arguments: 'firstname', 'lastname and' , 
  'userCreationDate' as YYYY-MM-DD),
* /UpdateUser - updates user (accepts input as JSON format object with arguments: 'userId', 'firstname', 
  'lastname and', 'userCreationDate' as YYYY-MM-DD),
* /DeleteUser - deletes user (accepts input as query param: 'userId'),
* /getUser - returns list of specific user (accepts input as query param: 'userId'),
* /getUsers - returns list of all users,
* /createBook - creates book row (accepts input as JSON format object with arguments: 'title', 'author', 'releaseDate'),
* /createRecord - creates record row (accepts input as JSON format object with arguments: 'status' and 'book' object),
* /updateRecordStatus - updates record row (accepts input as query params: 'recordId' and 'status'),
* /getAvailableRecords - returns list of specific records with status 'AVAILABLE' (accepts input as query params: 
  'bookTitle'),
* /rentTheBook - creates row in rentalRecord table, (accepts input as query params: 'userId', 'bookTitle' and 
  'rentUntil' in a format YYYY-MM-DD),
* /getBookByTitle - returns book object in JSON format, with the appropriate record's status,
* /getRentalRecord - returns rental object in JSON format(accepts input as query params: 'rentRecordId'),
* /returnBook - deletes rental record, specified in the parameter and changes corresponding book record status to 
  'AVAILABLE'(accepts input as query params: 'rentRecordId').

# Structure
The structure is based on a book, bookRecord, bookRentalRecord and status classes which all also are marked as entities.
Each of above has second class, marked with a note 'Dto' for transferring data between requests and database and also
CRUD type repositories. Requests are accepted by a controller which uses mappers objects in order next to save or read 
from a MySQL database.

# Tests
The application is accompanied by the couple very basic tests for adding specific records into tables. 

# Technologies
Project is created with:
* IntelliJ IDEA version: 2020.2.3 x64
* JUnit Jupiter Engine version: 5.7.1
* JUnit Jupiter API version: 5.7.1
* Mockito JUnit Jupiter version: 3.3.3
* Gradle version: 6.7.1
* MySQL Connector version: 8.0.23
* Hibernate Core Relocation version: 5.4.29
* Spring Boot version 2.4.4
* Spring JDBC version 5.3.5
* Spring Boot Starter Data JPA version 2.4.6
* Lombok version 1.18.16

# Setup
To run this game, simply run main method

