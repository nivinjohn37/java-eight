import java.util.*;
import java.util.stream.*;
import java.util.function.*;

import static java.util.Arrays.stream;

/**
 * ✅ Easy
 * <p>
 * List to Set
 * Convert a list of names into a Set<String> using Collectors.toSet().
 * <p>
 * Joining Strings
 * Given ["Java", "8", "Streams"], use Collectors.joining(" ") to produce:
 * <p>
 * Java 8 Streams
 * <p>
 * <p>
 * Summarizing Integers
 * From [3, 7, 2, 10], get summary statistics (count, sum, min, max, average).
 * <p>
 * ⚡ Medium
 * <p>
 * Grouping by First Letter
 * Given a list of words: ["apple", "apricot", "banana", "blueberry", "cherry"], group them by first character into a Map<Character, List<String>>.
 * <p>
 * Partitioning Even/Odd
 * Partition [1,2,3,4,5,6,7,8] into even and odd using Collectors.partitioningBy().
 * <p>
 * Word Frequency Counter
 * Given ["apple", "banana", "apple", "orange", "banana", "apple"], count the frequency of each word into a Map<String, Long>.
 * <p>
 * 🚀 Hard
 * <p>
 * Mini Project – Student Grade Analysis
 * <p>
 * You are given a list of students with name, subject, and score.
 * <p>
 * class Student {
 * String name;
 * String subject;
 * int score;
 * <p>
 * Student(String name, String subject, int score) {
 * this.name = name;
 * this.subject = subject;
 * this.score = score;
 * }
 * }
 * <p>
 * <p>
 * Data:
 * <p>
 * List<Student> students = Arrays.asList(
 * new Student("Alice", "Math", 90),
 * new Student("Bob", "Math", 70),
 * new Student("Charlie", "Physics", 85),
 * new Student("David", "Math", 60),
 * new Student("Eve", "Physics", 95),
 * new Student("Frank", "Chemistry", 75),
 * new Student("Grace", "Chemistry", 80)
 * );
 * <p>
 * Tasks:
 * <p>
 * Group students by subject → Map<String, List<Student>>.
 * <p>
 * Average score per subject → Map<String, Double>.
 * <p>
 * Top scorer per subject → Map<String, Optional<Student>>.
 * <p>
 * Parallel Processing Challenge:
 * Compute the total of all scores using a parallel stream.
 */
public class Day3Practice {
    public static void main(String[] args) {
        easyExercises();
        mediumExercises();
        hardExerciseStudentAnalysis();
    }

    private static void easyExercises() {
        System.out.println("=== EASY EXERCISES ===");

        // 1. List to Set
        List<String> names = List.of("apple", "banana", "orange", "apple");
        names.stream().collect(Collectors.toSet()).forEach(System.out::println);
        // 2. Joining strings
        String[] namesArray = new String[]{"Java", "8", "Streams"};
        System.out.println(stream(namesArray).collect(Collectors.joining(",")));

        // 3. Summarizing integers
        // * From [3, 7, 2, 10], get summary statistics (count, sum, min, max, average).
        int[] nums = new int[]{3, 7, 2, 10};
        IntSummaryStatistics summaryStatistics = Arrays.stream(nums).boxed().collect(Collectors.summarizingInt(x -> x));
        System.out.println("count = " + summaryStatistics.getCount());
        System.out.println("min = " + summaryStatistics.getMin());
        System.out.println("max = " + summaryStatistics.getMax());
        System.out.println("sum = " + summaryStatistics.getSum());
        System.out.println("average = " + summaryStatistics.getAverage());
    }

    private static void mediumExercises() {
        System.out.println("\n=== MEDIUM EXERCISES ===");

        // 1. Grouping by first letter
        String[] fruits = new String[]{"apple", "apricot", "banana", "blueberry", "cherry"};
        stream(fruits)
                .collect(Collectors.partitioningBy(x -> x.startsWith("a")))
                .forEach((k, v) -> System.out.println(k + " " + v));

        stream(fruits)
                .collect(Collectors.groupingBy(x -> x.charAt(1)))
                .forEach((k, v) -> System.out.println(k + " " + v));
        // 2. Partitioning even/odd
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        stream(nums)
                .boxed()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0))
                .forEach((k, v) -> System.out.println(k + " " + v));

        // 3. Word frequency counter
        String[] words = new String[]{"apple", "banana", "apple", "orange", "banana", "apple"};
        stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " " + v));
    }

    private static void hardExerciseStudentAnalysis() {
        System.out.println("\n=== HARD EXERCISE: STUDENT GRADE ANALYSIS ===");

        class Student {
            String name;
            String subject;
            int score;

            Student(String name, String subject, int score) {
                this.name = name;
                this.subject = subject;
                this.score = score;
            }

            @Override
            public String toString() {
                return name + " (" + subject + ": " + score + ")";
            }
        }

        List<Student> students = Arrays.asList(
                new Student("Alice", "Math", 90),
                new Student("Bob", "Math", 70),
                new Student("Charlie", "Physics", 85),
                new Student("David", "Math", 60),
                new Student("Eve", "Physics", 95),
                new Student("Frank", "Chemistry", 75),
                new Student("Grace", "Chemistry", 80)
        );

        // 1. Group students by subject
        students.stream()
                .collect(Collectors.groupingBy(s -> s.subject))
                .forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("====================");

        students.stream()
                .collect(Collectors.groupingBy(s -> s.name, Collectors.summarizingInt(s -> s.score)))
                .forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("====================");

        // 2. Average score per subject
        students.stream()
                .collect(Collectors.groupingBy(s -> s.subject, Collectors.averagingInt(s -> s.score)))
                .forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("====================");

        // 3. Top scorer per subject
        students.stream()
                .collect(Collectors.groupingBy(s -> s.subject, Collectors.maxBy(Comparator.comparingInt(s -> s.score))))
                .forEach((subject, optionalStudent) -> {
                    optionalStudent.ifPresent(topStudent -> {
                        System.out.printf("%s: %s %d %n", subject, topStudent.name, topStudent.score);
                    });
                });
        System.out.println("====================");


        // 4. Parallel stream total score
        System.out.println(students.parallelStream()
                .collect(Collectors.summingInt(s -> s.score)));

    }
}