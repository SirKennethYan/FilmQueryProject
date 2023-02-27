# Film Query App

# Description
This Java program is an application that allows users to search for films in a database. It provides a user interface in the console where users can select from three options:

1. Look up a film by its id.
2. Look up a film by a search keyword.
3. Exit the application.

The program uses a DatabaseAccessorObject to access the database and retrieve film data.

If the user selects option 1, they are prompted to enter a film ID. The program uses the DatabaseAccessorObject to find the film by its ID and displays its title, release year, rating, and description. If no film is found with the given ID, the program informs the user and returns to the main menu.

If the user selects option 2, they are prompted to enter a search keyword. The program uses the DatabaseAccessorObject to find all films whose title or description contains the keyword, and displays their title, release year, rating, language, description, and actors. If no films are found matching the keyword, the program informs the user and returns to the main menu.

If the user selects option 3, the program exits.

If the user enters an invalid input, the program informs the user and returns to the main menu.

# Technologies Used
1. Eclipse
2. Java
3. MySQL
4. Sublime Text
5. Maven
6. MAMP
7. Terminal

# Lessons Learned
During the project, I learned how to use Java in conjunction with JDBC to connect and interact with a database. At first, it was challenging to combine my knowledge of Java with the new concepts I was learning about SQL and databases. However, as I continued to work on the project, I found myself becoming more comfortable with the idea of using Java to query a database.

One of the more valuable things I learned was how to handle SQLExceptions and use Try/Catch statements to handle exceptions. This knowledge will be useful in future projects where I may need to troubleshoot issues with database connections or queries.

Overall, this project helped me see how Java is used in real-life scenarios, and it gave me a better appreciation for the language. I feel more confident in my ability to use Java, and I'm excited to continue building my skills in this area.