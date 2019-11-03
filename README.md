


    
    
    HELLO WORLD!!!! 



    Keep the following priorities in mind while you implementing - in the mentioned order:
    
       1. Code quality
       2. Usage of object oriented methods
       3. Functionality
    
    
    ### Main tasks
    
    1. Your software should read all data from the given CSV files in a meaningful structure.
    
    2. Print out all books and magazines (could be a GUI, console, …) with all their details (with a meaningful output format).
    
       > **Hint**: Do not call `printBooks(...)` first and then `printMagazines(...)` ;-)
    
    3. Find a book or magazine by its `isbn`.
    
    4. Find all books and magazines by their `bookAuthors`’ email.
    
    5. Print out all books and magazines with all their details sorted by `title`.
       This sort should be done for books and magazines together.
    
    ### Optional tasks
    
    > **Hint:** Optional means optional.
    
    1. Write Unit tests for one or more methods.
    
    2. Implement an interactive user interface for one or more of the main tasks mentioned above.
       This could be done by a website, on the console, etc.
    
    3. Add a book and a magazine to the data structure of your software and export it to a new CSV files.
    
    
    
    
    ## Run your application?
    
    1. By IDE:<br/>
       Just click with right mouse on the [`MainApp`](src/main/java/org/echocat/kata/java/part1/MainApp.java) class and
       then on _Run_ or _Debug_.
    2. By command line:
       ```bash
       ./mvnw package exec:java -Dexec.mainClass=org.echocat.kata.java.part1.MainApp
       ```
    ##### How to run your tests?
    
    1. By IDE:<br/>
       Just click with right mouse on the root of your project tree and click on
       then on _Run all tests_ or _Debug all tests_.
    2. By command line:
       ```bash
       ./mvnw test
       ```
