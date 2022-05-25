# jensen-library
Assignment for Testverktyg students in Stockholm 2022

# Problem Specification
Jensen Library wants to automate their working process to reduce operational costs and improve the users’ experience.

From `authors` they want to know the `first_name` and `last_name` which are strings of at most 60 characters.

From `books` they want to know the `isbn` which is a string of exactly 13 characters and the title which is a
string of at most 200 characters. They also want to know the lead author, which is, from all the authors of the
book, the one with the highest relevance. One author may be lead author of many books, but one book has exactly
one lead author.

From `customers` they want to know the `first_name`, `last_name` and `email` which are strings of at most 60
characters. They also need to know the `phone_number`, which is a string of at most 24 characters. Jensen Library
has an in-house printing allowing it to increase the stock of any book in real time. This means that one user may
loan many books and one book may be loaned by many users.

From the loans they want to know the `loan_date` and the number of days (`loan_days`) the user may read the book
before having to return it.
