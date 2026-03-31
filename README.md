# Printer Queue Management System

A comprehensive Java application for managing a printer queue system with document handling, queue operations, and an interactive user interface. This project includes unit tests and a menu-driven driver application.

## Project Structure

```
Midterm/
├── src/
│   ├── Document.java              # Document class with name and pages attributes
│   ├── PrinterQueue.java          # PrinterQueue class managing the queue operations
│   └── PrinterQueueDriver.java    # Interactive menu-driven application
├── test/
│   └── PrinterQueueTest.java      # JUnit test cases for PrinterQueue
├── pom.xml                        # Maven build configuration
└── README.md                      # This file
```

## Classes

### Document Class
Represents a document to be printed with the following attributes:
- **name** (String): Name of the document
- **pages** (int): Number of pages in the document

**Key Methods:**
- `Document(String name, int pages)`: Constructor with validation
- `getName()`: Get document name
- `getPages()`: Get number of pages
- `setName(String name)`: Set document name
- `setPages(int pages)`: Set number of pages
- `toString()`: String representation
- `equals(Object)`: Equality comparison
- `hashCode()`: Hash code generation

### PrinterQueue Class
Manages a queue of documents using FIFO (First-In-First-Out) principle.

**Key Methods:**
- `addDocument(Document document)`: Adds a document to the queue
- `removeDocument(Document document)`: Removes a specific document from the queue
- `printDocument()`: Prints and removes the document at the front of the queue
- `getQueueSize()`: Returns the current size of the queue
- `getQueue()`: Returns a copy of the printer queue
- `isEmpty()`: Checks if the queue is empty
- `clear()`: Clears all documents from the queue
- `toString()`: Displays all documents in the queue

### PrinterQueueDriver Class
Interactive menu-driven application demonstrating the functionality of PrinterQueue.

**Menu Options:**
1. Add a document to the queue
2. Remove a document from the queue
3. Print document at the front of the queue
4. Display queue size
5. Display entire queue
6. Exit the program

## JUnit Test Cases

Comprehensive test coverage in `PrinterQueueTest.java` including:
- Adding single and multiple documents
- Removing documents (valid and invalid scenarios)
- Printing documents (FIFO behavior)
- Queue size operations
- Queue retrieval (copy verification)
- Empty queue handling
- Document validation
- Error handling with exceptions

**Total Test Cases:** 19 comprehensive tests

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6.0 or higher

## Building the Project

### Using Maven

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Package the application
mvn package

# Run all (compile, test, and package)
mvn clean package
```

### Using Java Compiler (Manual)

```bash
# Compile source files
javac -d target/classes src/*.java

# Compile test files
javac -cp target/classes:test -d target/test-classes test/*.java
```

## Running the Application

### Using Maven

```bash
# Run the interactive driver
mvn exec:java -Dexec.mainClass="PrinterQueueDriver"
```

### Using Java (Manual)

```bash
# Run the driver application
java -cp src PrinterQueueDriver
```

## Running Tests

### Using Maven

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=PrinterQueueTest
```

### Using JUnit (Manual)

```bash
# Compile and run tests
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar src/*.java test/*.java
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore PrinterQueueTest
```

## Usage Example

```java
// Create a new printer queue
PrinterQueue queue = new PrinterQueue();

// Create documents
Document doc1 = new Document("Report.pdf", 10);
Document doc2 = new Document("Resume.pdf", 5);

// Add documents to queue
queue.addDocument(doc1);
queue.addDocument(doc2);

// Check queue size
System.out.println("Queue size: " + queue.getQueueSize()); // Output: 2

// Print documents (FIFO)
Document printed = queue.printDocument(); // Prints doc1, returns it
System.out.println("Printed: " + printed.toString());

// Check remaining queue
System.out.println("Queue size: " + queue.getQueueSize()); // Output: 1
```

## Features

✓ Document validation (pages must be > 0)  
✓ FIFO queue implementation  
✓ Complete error handling with custom exceptions  
✓ Comprehensive JUnit testing (19 test cases)  
✓ Interactive command-line menu  
✓ Queue display and navigation  
✓ Null safety checks  
✓ Document equality and hashing  
✓ Copy-safe queue retrieval  

## Test Coverage

The project includes 19 comprehensive JUnit test cases covering:
- Basic operations (add, remove, print)
- Edge cases (empty queue, null documents)
- FIFO behavior verification
- Data validation
- Queue operations
- Exception handling

## Author

This project was developed as a midterm assignment demonstrating OOP principles, queue data structures, and Java testing practices.

## License

This project is provided as-is for educational purposes.