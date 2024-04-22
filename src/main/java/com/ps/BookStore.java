package com.ps;
import java.util.*;

public class BookStore {
    public final Book[] inventory;
    public int nextId;

    // Constructor for BookStore Class
    public BookStore(int capacity) { // Initializes the inventory array with this given capacity.
        inventory = new Book[capacity];
        nextId = 1; // Initializes nextId to 1, adding books to inventory using addBook method.
        addBook("978-8-84-529261-3", "The Lord of the Rings");
        addBook("978-0-721-40812-5", "Dracula");
        addBook("978-1-565-11770-9", "Of Mice and Men");
        addBook("978-0-306-40615-7", "To Killing a Mockingbird");
        addBook("978-0-545-01022-1", "Harry Potter and the Chamber of Secrets");
        addBook("978-0-261-10201-7", "The Hobbit");
        addBook("978-0-7432-7356-5", "The Great Gatsby");
        addBook("978-0-06-076489-6", "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe");
        addBook("978-0-486-28273-4", "Frankenstein");
        addBook("978-1-4516-2447-6", "Fahrenheit 451");
        addBook("978-0-399-51263-7","Lord of the Flies");
        addBook("978-0-06-083702-0","The Bell Jar");
        addBook("978-0-00-675402-2","The Hobbit");
        addBook("978-1-51-942559-1","The Scarlet Letter");
        addBook("978-0-43-27356-5","The Great Gatsby");
        addBook("978-0-55-321311-9","Moby-Dick");
        addBook("978-0-15-60302-0","Life of Pi");
        addBook("978-0-06-441093-9","Charlotte's Web");
        addBook("978-0-39-482337-9","The Lorax");
        addBook("978-0-14-240733-2","The Outsiders");
    }

    // Method to add book to Inventory
    public void addBook(String isbn, String title) {
        if (nextId <= inventory.length) {
            // This creates a new book object and stores it in the inventory array.
            inventory[nextId - 1] = new Book(nextId++, isbn, title);
        }
    }

    // Method to show the homes screen and to handle user input
    public void showHomeScreen() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            // Displays all the options on the home screen
            System.out.println("\n Welcome to Book Store. ");
            System.out.println("1. Press 1 to Show Available Books. ");
            System.out.println("2. Press 2 to Show Checked out Books. ");
            System.out.println("3. Press 3 to Exit. ");
            System.out.print("Select an option: ");
            int option = scanner.nextInt(); // Reads the user input for option selection.
            switch (option) {
                case 1:
                    showAvailableBooks(scanner); // This calls the method to show the available books.
                    break;
                case 2:
                    showCheckedOutBooks(scanner); // This calls the method to show the checked out books.
                    break;
                case 3:
                    exit = true; // This sets the exit flag to true, so that it can exit the while loop.
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
            }
        }
    }

    // Method displaying the available books and handles the user input
    public void showAvailableBooks(Scanner scanner) {
        System.out.println("\n Available Books. ");
        // Iterate this through inventory array and displays the available books
        for (Book books : inventory) {
            if (books != null && !books.isCheckedOut()) {
                System.out.println("ID: " + books.getId() + ", ISBN: " + books.getIsbn() + ", Title: " + books.getTitle());
            }
        }
        System.out.println("\n1. Please check out a book. ");
        System.out.println("2. Going back to Home Screen...");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        if (option == 1) {
            checkOutBook(scanner); // Calls method to check out a book.
        }
    }

    // Method to display the books that have been checked out books and also handles user input.
    public void showCheckedOutBooks(Scanner scanner) {
        System.out.println("\n Checked out Books ");
        // Iterates the inventory array and displays the checked out books.
        for (Book books : inventory) {
            if (books != null && books.isCheckedOut()) {
                System.out.println("ID: " + books.getId() + ", ISBN: " + books.getIsbn() + ", Title: " + books.getTitle() + ", Checked out to: " + books.getCheckedOutTo());
            }
        }
        System.out.println("\n1. Please check in a book.");
        System.out.println("2. Returning to Home Screen...");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        if (option == 1) {
            checkInBook(scanner); // Calls the method to check inside a box.
        }
    }

    // A method to check out a book
    public void checkOutBook(Scanner scanner) {
        System.out.print("Please enter the ID of the book you want to check out: ");
        int bookId = scanner.nextInt();
        Book books = findBookById(bookId);
        if (books != null && !books.isCheckedOut()) {
            scanner.nextLine(); // Consumes newline.
            System.out.print("Please enter your name: ");
            String name = scanner.nextLine();
            books.checkOut(name); // Checks out a book.
            System.out.println("Congratulations. Book checked out successfully.");
        } else {
            System.out.println("Book is not available or already checked out.");
        }
    }

    // This is a method to check in a book.
    public void checkInBook(Scanner scanner) {
        System.out.print("Please enter the ID of the book you want to check in: ");
        int bookId = scanner.nextInt();
        Book books = findBookById(bookId);
        if (books != null && books.isCheckedOut()) {
            books.checkIn(); // Checks in a book.
            System.out.println("Congratulations. Book checked in successfully.");
        } else {
            System.out.println("Book was not found or already checked in.");
        }
    }

    // This is a method to find a book by its ID.
    public Book findBookById(int id) {
        for (Book books : inventory) {
            if (books != null && books.getId() == id) {
                return books;
            }
        }
        return null; // Returns null if the book has not been found.
    }

    // This is a main method to start the application.
    public static void main(String[] args) {
        BookStore store = new BookStore(20); // Create a Bookstore object with the capacity of 20.
        store.showHomeScreen(); // Shows home screen.
    }
}

// Book class with the properties and methods related to a book.
class Book {
    public int id;
    public String isbn;
    public String title;
    public boolean isCheckedOut;
    public String checkedOutTo;

    // Constructor to initialize a book object.
    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
    }

    // Getter and setter methods for book properties.
    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void checkOut(String name) {
        isCheckedOut = true;
        checkedOutTo = name;
    }

    public void checkIn() {
        isCheckedOut = false;
        checkedOutTo = "";
    }
}

