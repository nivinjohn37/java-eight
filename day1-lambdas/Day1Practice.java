
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 📝 Day 1 Practice
 *
 * Easy
 *
 * Write a lambda to check if a number is even.
 *
 * Use Consumer<String> to print each string in a list.
 *
 * Medium
 *
 * Create a list of names, then filter those longer than 4 characters using Predicate.
 *
 * Use Function<Integer, Integer> to compute squares of numbers in a list.
 *
 * Hard
 * Build a mini phonebook app where:
 *
 * Contacts are stored as a list of objects.
 *
 * Use Predicate to search by name prefix.
 *
 * Use Consumer to print results.
 *
 * Use Function to transform a contact object into a display string.
 */
public class Day1Practice {

    public static void main(String[] args) {
        // ---------- Easy Exercises ----------
        easyExercises();

        // ---------- Medium Exercises ----------
        mediumExercises();

        // ---------- Hard Exercise ----------
        hardExercisePhoneBook();

        // ---------- Custom Exercise ----------
        customExercise();
    }

    private static void customExercise() {
        class Student{
            String name;
            int age;

            Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }

        List<Student> studentList = List.of(
                new Student("studone", 12),
                new Student("studtwo", 14),
                new Student("studseven", 16),
                new Student("studfour", 18));

        List<String> studentsStringResult = studentList.stream()
                .filter(s -> s.name.length() > 8)
                .map(s -> s.name.concat("win"))
                .toList();
        studentsStringResult.forEach(System.out::println);

        List<Student> students = studentList.stream()
                .filter(s -> s.name.length() > 8)
                .map(s ->{
                    Student student = new Student(s.name+"win", s.age);
                    return student;
                }).toList();
        students.forEach(s -> System.out.println(s.name + " " + s.age));

        List<String> stringList = new ArrayList<>(List.of( "orange", "papayas","apple1", "mango"));
        stringList.forEach(System.out::println);
        System.out.println("==============");
        stringList.sort(Comparator.comparing(String::length));
        stringList.forEach(System.out::println);
        System.out.println("==============");
        stringList.sort(Comparator.comparingInt(String::length).reversed());
        stringList.forEach(System.out::println);
        System.out.println("==============");


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .forEach(System.out::println);
        //stringList.sort(String::compareTo);
        //stringList.forEach(System.out::println);

    }

    // -------------------------------
    // EASY LEVEL
    // -------------------------------
    private static void easyExercises() {
        System.out.println("=== EASY EXERCISES ===");

        // 1. Lambda to check if a number is even
        Predicate<Integer> isEven = x -> x % 2 == 0; // TODO: implement with lambda
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));

        // 2. Consumer<String> to print each string in a list
        Consumer<String> printName = System.out::println; // TODO: implement with lambda
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(printName);
    }

    // -------------------------------
    // MEDIUM LEVEL
    // -------------------------------
    private static void mediumExercises() {
        System.out.println("\n=== MEDIUM EXERCISES ===");

        // 1. Filter names longer than 4 characters using Predicate
        List<String> names = Arrays.asList("Alan", "Shyam", "Lance", "Mohan");
        Predicate<String> longName = s -> s.length() > 4; // TODO: implement with lambda
        names.stream()
             .filter(longName)
             .forEach(System.out::println);

        // 2. Function<Integer, Integer> to compute squares
        Function<Integer, Integer> square = x -> x * 2; // TODO: implement with lambda
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8);
        numbers.stream()
               .map(square)
               .forEach(System.out::println);
    }

    // -------------------------------
    // HARD LEVEL
    // -------------------------------
    private static void hardExercisePhoneBook() {
        System.out.println("\n=== HARD EXERCISE: PHONEBOOK ===");

        class Contact {
            String name;
            String phone;

            Contact(String name, String phone) {
                this.name = name;
                this.phone = phone;
            }
        }

        List<Contact> phoneBook = Arrays.asList(
            new Contact("Alice", "1234"),
            new Contact("Bob", "5678"),
            new Contact("Charlie", "9999"),
            new Contact("Anand", "1111")
        );

        // TODO 1: Predicate<Contact> to search by name prefix
        Predicate<Contact> startsWithA = s -> s.name.startsWith("A");

        // TODO 2: Consumer<Contact> to print contact details
        Consumer<Contact> printContact = s -> System.out.println(s.name + " " + s.phone);

        // TODO 3: Function<Contact, String> to transform to display string
        Function<Contact, String> toDisplayString = s -> s.name + " " + s.phone;

        // Example usage
        phoneBook.stream()
                 .filter(startsWithA)
                 .forEach(printContact);

        phoneBook.stream()
                 .map(toDisplayString)
                 .forEach(System.out::println);
    }
}
