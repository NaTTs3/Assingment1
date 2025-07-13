import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Gettery a settery
    public int getId() { return id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Year: %d", id, title, author, year);
    }

    public String toCSV() {
        return String.format("%d,%s,%s,%d", id, title, author, year);
    }
}
