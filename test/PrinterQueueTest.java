import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.Queue;

public class PrinterQueueTest {
    private PrinterQueue printerQueue;
    private Document doc1;
    private Document doc2;
    private Document doc3;

    @Before
    public void setUp() {
        printerQueue = new PrinterQueue();
        doc1 = new Document("Report.pdf", 10);
        doc2 = new Document("Resume.pdf", 5);
        doc3 = new Document("Presentation.pptx", 20);
    }

    @After
    public void tearDown() {
        printerQueue.clear();
    }

    @Test
    public void testAddDocument() {
        assertEquals(0, printerQueue.getQueueSize());
        printerQueue.addDocument(doc1);
        assertEquals(1, printerQueue.getQueueSize());
    }

    @Test
    public void testAddMultipleDocuments() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        printerQueue.addDocument(doc3);
        assertEquals(3, printerQueue.getQueueSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDocument() {
        printerQueue.addDocument(null);
    }

    @Test
    public void testRemoveDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        assertEquals(2, printerQueue.getQueueSize());
        
        boolean removed = printerQueue.removeDocument(doc1);
        assertTrue(removed);
        assertEquals(1, printerQueue.getQueueSize());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNonExistentDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.removeDocument(doc2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullDocument() {
        printerQueue.removeDocument(null);
    }

    @Test
    public void testPrintDocument() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        
        Document printed = printerQueue.printDocument();
        assertNotNull(printed);
        assertEquals(doc1, printed);
        assertEquals(1, printerQueue.getQueueSize());
    }

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

    @Test
    public void testPrintFromEmptyQueue() {
        Document printed = printerQueue.printDocument();
        assertNull(printed);
        assertEquals(0, printerQueue.getQueueSize());
    }

    @Test
    public void testGetQueueSize() {
        assertEquals(0, printerQueue.getQueueSize());
        printerQueue.addDocument(doc1);
        assertEquals(1, printerQueue.getQueueSize());
        printerQueue.addDocument(doc2);
        assertEquals(2, printerQueue.getQueueSize());
    }

    @Test
    public void testGetQueue() {
        printerQueue.addDocument(doc1);
        printerQueue.addDocument(doc2);
        
        Queue<Document> queue = printerQueue.getQueue();
        assertNotNull(queue);
        assertEquals(2, queue.size());
    }

    @Test
    public void testGetQueueReturnsCopy() {
        printerQueue.addDocument(doc1);
        Queue<Document> queue = printerQueue.getQueue();
        queue.add(doc2);
        
        // Original queue should not be affected
        assertEquals(1, printerQueue.getQueueSize());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(printerQueue.isEmpty());
        printerQueue.addDocument(doc1);
        assertFalse(printerQueue.isEmpty());
        printerQueue.printDocument();
        assertTrue(printerQueue.isEmpty());
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testDocumentWithInvalidPages() {
        new Document("Invalid.pdf", -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDocumentWithZeroPages() {
        new Document("Invalid.pdf", 0);
    }

    @Test
    public void testDocumentEqualsAndToString() {
        Document doc1Copy = new Document("Report.pdf", 10);
        assertEquals(doc1, doc1Copy);
        assertNotNull(doc1.toString());
    }
}
