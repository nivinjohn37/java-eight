import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class CustomTest {
    public static void main(String[] args) {
        //Given a list of integers, separate odd and even numbers
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87, 22, 12);
        listOfIntegers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0))
                .forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------");

        listOfIntegers.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd"))
                .forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------");


        listOfIntegers.sort(Integer::compareTo);

        listOfIntegers.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd",
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> {                 // Then sort the list
                                    list.sort(Comparator.naturalOrder());
                                    return list;
                                }))).forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------");

        listOfIntegers.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd",
                        Collectors.collectingAndThen(Collectors.counting(), count -> count > 5)))
                .forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------");


        listOfIntegers.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd",
                        Collectors.counting()))
                .values()
                .forEach(System.out::println);
        System.out.println("---------");


        Map<String, Long> resultCountMap = listOfIntegers.stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd",
                        Collectors.counting()));
        resultCountMap.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("---------");

        resultCountMap.entrySet().stream()
                .filter(e -> e.getValue() > 5)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
        System.out.println("==================================");

        //How do you remove duplicate elements from a list using Java 8 streams?
        //List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87, 22, 12);
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        listOfStrings.stream().distinct().toList().forEach(x -> System.out.print(x + " "));
        System.out.println("\n==================================");

        //How do you find frequency of each character in a string using Java 8 streams?
        String inputString = "Java Concept Of The Day";
        Map<Character, Long> resultMap = inputString.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != ' ')
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<Character, Long>::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        resultMap.forEach((k, v) -> System.out.println(k + "->" + v));
        System.out.println("==================================");

        String[] inputSentences = {"Java Concept Of The Day", "Java Concept Of The Month"};
        Map<Character, Long> charFrequencies = Arrays.stream(inputSentences)
                // 1. Convert the array of Strings into a stream of individual characters
                .flatMapToInt(s -> s.toLowerCase().chars())

                // 2. Map the int (ASCII) to Character objects
                .mapToObj(c -> (char) c)

                // 3. Filter out space characters
                .filter(c -> c != ' ')

                // 4. Group the characters and count the occurrences
                .collect(Collectors.groupingBy(
                        Function.identity(), // Grouping key is the character itself
                        Collectors.counting() // Count the elements in each group
                ));

        System.out.println(charFrequencies);
        System.out.println("==================================");

        //How do you find frequency of each element in an array or a list?
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
        stationeryList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("==================================");


    }
}
