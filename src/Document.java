/**
 * Document class representing a document to be printed.
 * A document has a name and a number of pages.
 */
public class Document {
    private String name;
    private int pages;

    /**
     * Constructor for Document.
     * @param name the name of the document
     * @param pages the number of pages in the document
     */
    public Document(String name, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        this.name = name;
        this.pages = pages;
    }

    /**
     * Gets the name of the document.
     * @return the document name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of pages in the document.
     * @return the number of pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Sets the name of the document.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the number of pages in the document.
     * @param pages the new number of pages
     */
    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        this.pages = pages;
    }

    /**
     * Returns a string representation of the document.
     * @return the document details
     */
    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                '}';
    }

    /**
     * Checks if two documents are equal based on name and pages.
     * @param obj the object to compare
     * @return true if documents are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Document document = (Document) obj;
        return pages == document.pages && name.equals(document.name);
    }

    /**
     * Returns the hash code of the document.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() + Integer.hashCode(pages);
    }
}
