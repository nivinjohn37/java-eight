import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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

        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("Apple", 3);
        unsortedMap.put("Banana", 1);
        unsortedMap.put("Kiwi", 4);
        unsortedMap.put("Cherry", 2);

        // Sort the HashMap by values in ascending order
        Map<String, Integer> sortedMapAsc = unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function for duplicate keys (not applicable here as keys are unique)
                        LinkedHashMap::new // Use LinkedHashMap to preserve order
                ));

        System.out.println("Sorted by value (ascending): " + sortedMapAsc);

        // Sort the HashMap by values in descending order
        Map<String, Integer> sortedMapDesc = unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("Sorted by value (descending): " + sortedMapDesc);
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

        //How do you sort the given list of decimals in reverse order?
        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
        decimalList.stream()
                //.sorted((s1, s2) -> Double.compare(s2, s1))
                .sorted(Comparator.reverseOrder())
                .toList()
                .forEach(System.out::println);

        //Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
        List<String> listOfStrings2 = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        listOfStrings2.stream()
                .map(s -> "[" + s + "],")
                .toList()
                .forEach(System.out::print);
        System.out.println();
        String joinedString = listOfStrings2.stream().collect(Collectors.joining(", ", "[", "]"));

        System.out.println(joinedString);


    }
}
