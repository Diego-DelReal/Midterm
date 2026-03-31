import java.util.Queue;
import java.util.LinkedList;

/**
 * PrinterQueue class manages a queue of documents to be printed.
 */
public class PrinterQueue {
    private Queue<Document> queue;

    /**
     * Constructor for PrinterQueue.
     * Initializes an empty queue.
     */
    public PrinterQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds a document to the printer queue.
     * @param document the document to add
     * @throws IllegalArgumentException if document is null
     */
    public void addDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        queue.add(document);
    }

    /**
     * Removes a specific document from the printer queue.
     * @param document the document to remove
     * @return true if the document was removed, false if it was not in the queue
     * @throws IllegalArgumentException if document is null
     */
    public boolean removeDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        return queue.remove(document);
    }

    /**
     * Prints the document at the front of the printer queue and removes it.
     * @return the document that was printed, or null if queue is empty
     */
    public Document printDocument() {
        if (queue.isEmpty()) {
            return null;
        }
        Document document = queue.poll();
        System.out.println("Printing: " + document.toString());
        return document;
    }

    /**
     * Returns the current size of the printer queue.
     * @return the number of documents in the queue
     */
    public int getQueueSize() {
        return queue.size();
    }

    /**
     * Returns the printer queue.
     * @return the queue of documents
     */
    public Queue<Document> getQueue() {
        return new LinkedList<>(queue);
    }

    /**
     * Checks if the queue is empty.
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Clears all documents from the queue.
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Returns a string representation of the queue.
     * @return a string showing all documents in the queue
     */
    @Override
    public String toString() {
        if (queue.isEmpty()) {
            return "Queue is empty";
        }
        StringBuilder sb = new StringBuilder("Printer Queue:\n");
        int position = 1;
        for (Document doc : queue) {
            sb.append(position).append(". ").append(doc.toString()).append("\n");
            position++;
        }
        return sb.toString();
    }
}
