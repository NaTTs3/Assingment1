import java.io.*;
import java.util.*;

public class LibraryManager {
    private List<Book> books;
    private final String fileName = "books.dat";

    public LibraryManager() {
        books = new ArrayList<>();
        loadFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveToFile();
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        books.forEach(System.out::println);
    }

    public Book searchById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        books.removeIf(book -> book.getId() == id);
        saveToFile();
    }

    public void updateBook(int id, String newTitle, String newAuthor, int newYear) {
        Book book = searchById(id);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setYear(newYear);
            saveToFile();
        }
    }

    public void sortByTitle() {
        books.sort(Comparator.comparing(b -> b.toString().toLowerCase()));
    }

    public void sortByYear() {
        books.sort(Comparator.comparingInt(Book::getYear));
    }

    public void exportToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ID,Title,Author,Year");
            for (Book b : books) {
                writer.println(b.toCSV());
            }
            System.out.println("Exported to " + filename);
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            books = (List<Book>) in.readObject();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(books);
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }
}
