import java.util.*;

public class Library {
    public static void main(String[] args) {

        Map<Integer, Map<String, String>> books = new HashMap<>();

        Map<String, String> book1 = new HashMap<>();
        book1.put("bookName", "Harry Potter");
        book1.put("author", "R.R.Martin");
        book1.put("price", "2000");
        book1.put("year", "2005");
        books.put(1, book1);

        Map<String, String> book2 = new HashMap<>();
        book2.put("bookName", "Lord of the Rings");
        book2.put("author", "Michael Bay");
        book2.put("price", "5000");
        book2.put("year", "2010");
        books.put(2, book2);

        Map<String, String> book3 = new HashMap<>();
        book3.put("bookName", "Stranger Things");
        book3.put("author", "Duffer Brothers");
        book3.put("price", "8000");
        book3.put("year", "2014");
        books.put(3, book3);

        Scanner sc = new Scanner(System.in);

        System.out.println("---- Books Ecommerce Site----");
        System.out.println("1. Search by Book Name");
        System.out.println("2. Search by Author");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        if (ch == 1) {
            System.out.print("Enter Book Name: ");
            String bookName = sc.nextLine();

            for (Map<String, String> book : books.values()) {
                if (book.get("bookName").equalsIgnoreCase(bookName)) {
                    System.out.println("\nBook Found!");
                    printBookDetails(book);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("\nBook not found.");
            }

        } else if (ch == 2) {
            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            for (Map<String, String> book : books.values()) {
                if (book.get("author").equalsIgnoreCase(author)) {
                    System.out.println("\nBook Found!");
                    printBookDetails(book);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("\nNo books found for this author.");
            }

        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }

    static void printBookDetails(Map<String, String> book)
    {
        System.out.println("Book Name : " + book.get("bookName"));
        System.out.println("Author    : " + book.get("author"));
        System.out.println("Price    : " + book.get("price"));
        System.out.println("Year      : "+book.get("year"));
        System.out.println("-----------------------------");
    }
}
