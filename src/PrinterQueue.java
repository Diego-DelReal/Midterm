import java.util.Queue;
import java.util.LinkedList;

public class PrinterQueue {
    private Queue<Document> queue;

    public PrinterQueue() {
        this.queue = new LinkedList<>();
    }

    public void addDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        queue.add(document);
    }

    public boolean removeDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        if (!queue.contains(document)) {
            throw new java.util.NoSuchElementException("Document not found in queue");
        }
        return queue.remove(document);
    }

    public Document printDocument() {
        if (queue.isEmpty()) {
            return null;
        }
        Document document = queue.poll();
        System.out.println("Printing: " + document.toString());
        return document;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public Queue<Document> getQueue() {
        return new LinkedList<>(queue);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        queue.clear();
    }

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
