import java.util.Scanner;
import java.util.Queue;

/**
 * PrinterQueueDriver class demonstrates the functionality of the PrinterQueue class.
 * Provides an interactive menu for managing printer documents.
 */
public class PrinterQueueDriver {
    private static PrinterQueue printerQueue;
    private static Scanner scanner;

    /**
     * Main method to run the printer queue management system.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        printerQueue = new PrinterQueue();
        scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("    Printer Queue Management System");
        System.out.println("======================================\n");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    addDocumentToQueue();
                    break;
                case 2:
                    removeDocumentFromQueue();
                    break;
                case 3:
                    printDocumentFromQueue();
                    break;
                case 4:
                    displayQueueSize();
                    break;
                case 5:
                    displayQueue();
                    break;
                case 6:
                    running = false;
                    System.out.println("\nExiting Printer Queue Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu.
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a document to the queue");
        System.out.println("2. Remove a document from the queue");
        System.out.println("3. Print document at the front of the queue");
        System.out.println("4. Display queue size");
        System.out.println("5. Display entire queue");
        System.out.println("6. Exit the program");
        System.out.print("Enter your choice (1-6): ");
    }

    /**
     * Gets a valid menu choice from the user.
     * @return user's menu choice
     */
    private static int getMenuChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
        }
        return choice;
    }

    /**
     * Adds a document to the printer queue.
     */
    private static void addDocumentToQueue() {
        System.out.println("\n--- Add Document ---");

        System.out.print("Enter document name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Document name cannot be empty.");
            return;
        }

        System.out.print("Enter number of pages: ");
        int pages;

        try {
            pages = Integer.parseInt(scanner.nextLine().trim());

            if (pages <= 0) {
                System.out.println("Error: Number of pages must be greater than 0.");
                return;
            }

            Document document = new Document(name, pages);
            printerQueue.addDocument(document);
            System.out.println("✓ Document added successfully!");
            System.out.println("  Added: " + document.toString());
            System.out.println("  Current queue size: " + printerQueue.getQueueSize());

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for pages.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Removes a document from the printer queue.
     */
    private static void removeDocumentFromQueue() {
        if (printerQueue.isEmpty()) {
            System.out.println("\nError: Queue is empty. No documents to remove.");
            return;
        }

        System.out.println("\n--- Remove Document ---");
        displayQueue();

        System.out.print("Enter document name to remove: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Document name cannot be empty.");
            return;
        }

        System.out.print("Enter number of pages: ");
        int pages;

        try {
            pages = Integer.parseInt(scanner.nextLine().trim());

            if (pages <= 0) {
                System.out.println("Error: Number of pages must be greater than 0.");
                return;
            }

            Document document = new Document(name, pages);
            boolean removed = printerQueue.removeDocument(document);

            if (removed) {
                System.out.println("✓ Document removed successfully!");
                System.out.println("  Removed: " + document.toString());
                System.out.println("  Current queue size: " + printerQueue.getQueueSize());
            } else {
                System.out.println("Error: Document not found in queue.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for pages.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the document at the front of the queue.
     */
    private static void printDocumentFromQueue() {
        System.out.println("\n--- Print Document ---");

        if (printerQueue.isEmpty()) {
            System.out.println("Error: Queue is empty. No documents to print.");
            return;
        }

        Document document = printerQueue.printDocument();

        if (document != null) {
            System.out.println("✓ Successfully printed!");
            System.out.println("  Document details: " + document.toString());
            System.out.println("  Documents remaining in queue: " + printerQueue.getQueueSize());
        }
    }

    /**
     * Displays the current size of the queue.
     */
    private static void displayQueueSize() {
        System.out.println("\n--- Queue Size ---");
        int size = printerQueue.getQueueSize();
        System.out.println("Current queue size: " + size);
        if (size == 0) {
            System.out.println("The queue is empty.");
        } else if (size == 1) {
            System.out.println("There is 1 document in the queue.");
        } else {
            System.out.println("There are " + size + " documents in the queue.");
        }
    }

    /**
     * Displays all documents in the queue.
     */
    private static void displayQueue() {
        System.out.println("\n--- Current Queue ---");

        if (printerQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            Queue<Document> queue = printerQueue.getQueue();
            int position = 1;
            System.out.println("Total documents: " + queue.size());
            System.out.println("-".repeat(50));
            for (Document doc : queue) {
                System.out.printf("%d. Name: %-30s Pages: %d%n", position, doc.getName(), doc.getPages());
                position++;
            }
            System.out.println("-".repeat(50));
        }
    }
}
