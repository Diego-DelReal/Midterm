import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.Queue;

/**
 * JUnit test class for PrinterQueue.
 */
public class PrinterQueueTest {
    private PrinterQueue printerQueue;
    private Document doc1;
    private Document doc2;
    private Document doc3;

    /**
     * Setup method run before each test.
     */
    @Before
    public void setUp() {
        printerQueue = new PrinterQueue();
        doc1 = new Document("Report.pdf", 10);
        doc2 = new Document("Resume.pdf", 5);
        doc3 = new Document("Presentation.pptx", 20);
    }

    /**
     * Cleanup method run after each test.
     */
    @After
    public void tearDown() {
        printerQueue.clear();
    }

    /**
     * Test adding a single document to the queue.
     */
    @Test
    public void testAddDocument() {
        assertEquals(0, printerQueue.getQueueSize());
        printerQueue.addDocument(doc1);
        assertEquals(1, printerQueue.getQueueSize());
    }

    /**
     * Test adding multiple documents to the queue.
     */
    @Test
    public void testAddMultipleDocuments() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        printerQueue.addDocument(doc3);
        assertEquals(3, printerQueue.getQueueSize());
    }

    /**
     * Test that adding null document throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDocument() {
        printerQueue.addDocument(null);
    }

    /**
     * Test removing a document from the queue.
     */
    @Test
    public void testRemoveDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        assertEquals(2, printerQueue.getQueueSize());
        
        boolean removed = printerQueue.removeDocument(doc1);
        assertTrue(removed);
        assertEquals(1, printerQueue.getQueueSize());
    }

    /**
     * Test removing a document that is not in the queue.
     */
    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNonExistentDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.removeDocument(doc2);
    }

    /**
     * Test that removing null document throws IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullDocument() {
        printerQueue.removeDocument(null);
    }

    /**
     * Test printing a document from the queue.
     */
    @Test
    public void testPrintDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        
        Document printed = printerQueue.printDocument();
        assertNotNull(printed);
        assertEquals(doc1, printed);
        assertEquals(1, printerQueue.getQueueSize());
    }

    /**
     * Test printing multiple documents in order (FIFO).
     */
    @Test
    public void testPrintMultipleDocuments() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        printerQueue.addDocument(doc3);
        
        Document first = printerQueue.printDocument();
        assertEquals(doc1, first);
        
        Document second = printerQueue.printDocument();
        assertEquals(doc2, second);
        
        Document third = printerQueue.printDocument();
        assertEquals(doc3, third);
        
        assertEquals(0, printerQueue.getQueueSize());
    }

    /**
     * Test printing from an empty queue.
     */
    @Test
    public void testPrintFromEmptyQueue() {
        Document printed = printerQueue.printDocument();
        assertNull(printed);
        assertEquals(0, printerQueue.getQueueSize());
    }

    /**
     * Test getting the queue size.
     */
    @Test
    public void testGetQueueSize() {
        assertEquals(0, printerQueue.getQueueSize());
        printerQueue.addDocument(doc1);
        assertEquals(1, printerQueue.getQueueSize());
        printerQueue.addDocument(doc2);
        assertEquals(2, printerQueue.getQueueSize());
    }

    /**
     * Test getting the queue.
     */
    @Test
    public void testGetQueue() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        
        Queue<Document> queue = printerQueue.getQueue();
        assertNotNull(queue);
        assertEquals(2, queue.size());
    }

    /**
     * Test that getQueue returns a copy, not the original queue.
     */
    @Test
    public void testGetQueueReturnsCopy() {
        printerQueue.addDocument(doc1);
        Queue<Document> queue = printerQueue.getQueue();
        queue.add(doc2);
        
        // Original queue should not be affected
        assertEquals(1, printerQueue.getQueueSize());
    }

    /**
     * Test isEmpty method.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(printerQueue.isEmpty());
        printerQueue.addDocument(doc1);
        assertFalse(printerQueue.isEmpty());
        printerQueue.printDocument();
        assertTrue(printerQueue.isEmpty());
    }

    /**
     * Test FIFO (First-In-First-Out) behavior.
     */
    @Test
    public void testFIFOBehavior() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        printerQueue.addDocument(doc3);
        
        Queue<Document> queue = printerQueue.getQueue();
        Document first = queue.poll();
        assertEquals(doc1, first);
        
        Document second = queue.poll();
        assertEquals(doc2, second);
        
        Document third = queue.poll();
        assertEquals(doc3, third);
    }

    /**
     * Test Document class with invalid pages.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDocumentWithInvalidPages() {
        new Document("Invalid.pdf", -5);
    }

    /**
     * Test Document class with zero pages.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDocumentWithZeroPages() {
        new Document("Invalid.pdf", 0);
    }

    /**
     * Test Document equals and toString methods.
     */
    @Test
    public void testDocumentEqualsAndToString() {
        Document doc1Copy = new Document("Report.pdf", 10);
        assertEquals(doc1, doc1Copy);
        assertNotNull(doc1.toString());
    }
}
