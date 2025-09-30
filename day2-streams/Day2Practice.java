import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * 📝 Day 2 Practice Problems
 * ✅ Easy
 *
 * Use method reference instead of lambda:
 *
 * Print all names in a list using System.out::println.
 *
 * Convert a list of strings to uppercase using String::toUpperCase.
 *
 * Use constructor reference:
 *
 * Create an ArrayList<String> using Supplier<ArrayList<String>> and ArrayList::new.
 *
 * ⚡ Medium
 *
 * Given a list of integers, filter out even numbers, square the odd ones, and collect into a new list.
 * Example: [1, 2, 3, 4, 5] → [1, 9, 25]
 *
 * From a list of names, keep only those starting with "A" and print them using a method reference.
 *
 * Given a list of strings, convert them to lengths (map(String::length)) and collect into a list.
 *
 * 🚀 Hard
 *
 * Mini Project – Word Processing
 *
 * You are given a list of sentences:
 *
 * List<String> sentences = Arrays.asList(
 *     "Java is fun",
 *     "Lambdas are powerful",
 *     "Streams make life easier",
 *     "Method references are neat"
 * );
 *
 *
 * Tasks:
 *
 * Split sentences into words (hint: flatMap(s -> Arrays.stream(s.split(" ")))).
 *
 * Convert all words to lowercase.
 *
 * Remove duplicates.
 *
 * Sort alphabetically.
 *
 * Collect results into a List<String> and print them.
 *
 * Expected output (sorted unique words):
 *
 * [are, easier, fun, is, java, lambdas, life, make, method, neat, powerful, references, streams]
 */
public class Day2Practice {
   public static Predicate<String> startsWithPrefix(String prefix) {
        return c -> c.startsWith(prefix);
    }
    public static void main(String[] args) {
        easyExercises();
        mediumExercises();
        hardExerciseWordProcessing();
    }

    private static void easyExercises() {
        System.out.println("=== EASY EXERCISES ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 1. Print using method reference
        names.forEach(System.out::println);

        // 2. Convert to uppercase using method reference
        Function<String, String> toUpperCase = String::toUpperCase;
        names.stream().map(toUpperCase).forEach(System.out::println);

        // 3. Constructor reference for ArrayList
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> namesList = listSupplier.get();
    }

    private static void mediumExercises() {
        System.out.println("\n=== MEDIUM EXERCISES ===");

        // 1. Odd squares
        IntStream.range(1, 5)
                .filter(i -> i % 2 != 0)
                .map(x -> x * x)
                .forEach(System.out::println);

        // 2. Names starting with A
        Predicate<String> startsWithPrefix= startsWithPrefix("A");
        Stream.of("Alice", "Bob", "Charlie")
                .filter(startsWithPrefix)
                .forEach(System.out::println);

        // 3. Convert to lengths
        Stream.of("Alice", "Bob", "Charlie")
                .map(String::length)
                .forEach(System.out::println);

    }

    private static void hardExerciseWordProcessing() {
        System.out.println("\n=== HARD EXERCISE: WORD PROCESSING ===");

        List<String> sentences = Arrays.asList(
            "Java is fun",
            "Lambdas are powerful",
            "Streams make life easier",
            "Method references are neat"
        );

        // TODO: flatMap + map + distinct + sorted + collect
        List<String> result = sentences.stream()
                .flatMap(s -> Stream.of(s.split(" ")))
                .map(String::toLowerCase)
                .distinct()
                .sorted(String::compareTo)
                .toList();
        result.forEach(s -> System.out.print(s + " "));

    }
}
