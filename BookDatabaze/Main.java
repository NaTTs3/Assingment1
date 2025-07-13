import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryManager manager = new LibraryManager();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add book");
            System.out.println("2. Display all books");
            System.out.println("3. Search book by ID");
            System.out.println("4. Delete book by ID");
            System.out.println("5. Update book by ID");
            System.out.println("6. Sort books by title");
            System.out.println("7. Sort books by year");
            System.out.println("8. Export to CSV");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> manager.displayAllBooks();
                case 3 -> searchBook();
                case 4 -> deleteBook();
                case 5 -> updateBook();
                case 6 -> {
                    manager.sortByTitle();
                    System.out.println("Books sorted by title.");
                }
                case 7 -> {
                    manager.sortByYear();
                    System.out.println("Books sorted by year.");
                }
                case 8 -> manager.exportToCSV("books.csv");
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addBook() {
        System.out.print("Enter ID: ");
        int id = getIntInput();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = getIntInput();
        manager.addBook(new Book(id, title, author, year));
    }

    private static void searchBook() {
        System.out.print("Enter ID to search: ");
        int id = getIntInput();
        Book book = manager.searchById(id);
        System.out.println(book != null ? book : "Book not found.");
    }

    private static void deleteBook() {
        System.out.print("Enter ID to delete: ");
        int id = getIntInput();
        manager.deleteById(id);
        System.out.println("Book deleted (if found).");
    }

    private static void updateBook() {
        System.out.print("Enter ID to update: ");
        int id = getIntInput();
        Book book = manager.searchById(id);
        if (book != null) {
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new author: ");
            String author = scanner.nextLine();
            System.out.print("Enter new year: ");
            int year = getIntInput();
            manager.updateBook(id, title, author, year);
        } else {
            System.out.println("Book not found.");
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
