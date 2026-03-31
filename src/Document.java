public class Document {
    private String name;
    private int pages;

    public Document(String name, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Document document = (Document) obj;
        return pages == document.pages && name.equals(document.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + Integer.hashCode(pages);
    }
}
