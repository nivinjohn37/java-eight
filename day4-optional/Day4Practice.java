import javax.swing.text.html.Option;
import java.time.*;
import java.util.*;
import java.util.stream.Stream;

public class Day4Practice {
    public static void main(String[] args) {
        easyExercises();
        mediumExercises();
        hardExerciseLibrarySystem();
    }

    private static void easyExercises() {
        /**
         * Create an Optional<String> with value "Java".
         *
         * Print it in uppercase if present.
         *
         * Create an empty Optional and print "Default" if empty.
         */
        System.out.println("=== EASY EXERCISES ===");

        // 1. Optional basics
        Optional.of("java").ifPresent(s -> System.out.println(s.toUpperCase()));
        Optional.ofNullable(null).orElse("default");
        // 2. LocalDate practice
        /**
         * Get today’s date.
         *
         * Create a LocalDate for your birthday.
         *
         * Print the day of the week you were born on.
         */
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(1993, 01, 01);
        System.out.println(birthDate.getDayOfWeek().getValue());
        java.time.format.TextStyle textStyle = java.time.format.TextStyle.FULL;
        java.util.Locale locale = java.util.Locale.ENGLISH;
        String fullDayName = birthDate.getDayOfWeek().getDisplayName(textStyle, locale);
        System.out.println("The full name of the day is: " + fullDayName);

        // 3. Default method
        /**
         * Create an interface Greetable with a default void greet() that prints "Hello!".
         *
         * Implement it in a class and call the method.
         */
        interface Greetable {
            default void greet() {
                System.out.println("Hello");
            }
        }
        class GreetableImpl implements Greetable {
            @Override
            public void greet() {
                System.out.println("Hi");
            }
        }

        GreetableImpl greetable = new GreetableImpl();
        greetable.greet();
    }

    private static void mediumExercises() {
        System.out.println("\n=== MEDIUM EXERCISES ===");

        // 1. Optional with filter/map
        Optional<String> name = Optional.of("Charlie");
        name.filter(s -> s.length() > 5)
                .map(String::toUpperCase)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Name too short"));

        // 2. Age calculator
        /**
         * Using LocalDate, calculate a person’s age given their birthdate.
         *
         * Print "You are X years, Y months, Z days old."
         */
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(1993, 01, 01);
        Period period = Period.between(birthDate, today);
        System.out.printf("You are %s years, %s months, %s days old", period.getYears(), period.getMonths(), period.getDays());

        // 3. Static method in interface
        /**
         * Static Method in Interface
         *
         * Create an interface MathUtils with a static method isEven(int n).
         *
         * Call it without creating an object.
         */
        interface MathUtils {
            static boolean isEven(int n) {
                return n % 2 == 0;
            }
        }

        class MathUtilsImpl implements MathUtils {
            public boolean isEven(int n) {
                return MathUtils.isEven(n);
            }

        }

        MathUtilsImpl mathUtils = new MathUtilsImpl();
        System.out.println(mathUtils.isEven(10));
    }

    private static void hardExerciseLibrarySystem() {
        System.out.println("\n=== HARD EXERCISE: LIBRARY SYSTEM ===");

        // TODO: Implement Book class
        // TODO: Create sample books
        // TODO: Use Optional to get book starting with "A"
        // TODO: Use Date/Time API to calculate years since published
        // TODO: Implement BookPrinter interface with default + static methods

        class Book {
            String title;
            String author;
            LocalDate publishedDate;

            Book(String title, String author, LocalDate publishedDate) {
                this.title = title;
                this.author = author;
                this.publishedDate = publishedDate;
            }

            @Override
            public String toString() {
                return title + " by " + author + " (" + publishedDate + ")";
            }
        }

        Book book1 = new Book("AJava Book", "Author", LocalDate.now());
        Book book2 = new Book("Java Book", "Author", LocalDate.of(1993, 01, 01));

        Optional<Book> book = Stream.of(book1, book2)
                .filter(b -> b.title.startsWith("A"))
                .findFirst();
        System.out.println(book);

        LocalDate today = LocalDate.now();
        LocalDate bookPublishedDate = book.isPresent() ? book.get().publishedDate : today;
        Period period = Period.between (bookPublishedDate, today);
        System.out.printf("this book named - %s was published %d years ago", book.isPresent()? book.get().title:"null", period.getYears());

        interface  BookPrinter{
            default void printBook(Book book) {
                System.out.println(book.title + " by " + book.author);
            }
            static void printLibraryInfo() {
                System.out.println("Library contains great books!");
            }
        }

        class BookPrinterImpl implements BookPrinter {

        }

        BookPrinter bookPrinter = new BookPrinterImpl();
        bookPrinter.printBook(book1);
        BookPrinter.printLibraryInfo();



    }
}
