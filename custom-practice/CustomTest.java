import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        System.out.println("---------2");

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
        System.out.println("==================================");

        //Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
        List<String> listOfStrings2 = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        listOfStrings2.stream()
                .map(s -> "[" + s + "],")
                .toList()
                .forEach(System.out::print);
        System.out.println();
        String joinedString = listOfStrings2.stream().collect(Collectors.joining(", ", "[", "]"));

        System.out.println(joinedString);
        System.out.println("==================================");

        //From the given list of integers, print the numbers which are multiples of 5?
        List<Integer> listOfIntegers2 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        listOfIntegers2.stream()
                .filter(s -> s % 5 == 0)
                .toList()
                .forEach(System.out::println);
        System.out.println("==================================");

        //Given a list of integers, find maximum and minimum of those numbers?
        List<Integer> listOfIntegers3 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        int max = listOfIntegers3.stream()
                .max(Comparator.naturalOrder())
                .get();
        int min = listOfIntegers3.stream()
                .min(Comparator.naturalOrder())
                .get();
        System.out.println(max + " " + min);
        System.out.println("==================================");

        //How do you merge two unsorted arrays into single sorted array using Java 8 streams?
        int[] a = new int[]{4, 2, 7, 1};
        int[] b = new int[]{8, 3, 9, 5};
        List.of(a, b).stream()
                .flatMapToInt(x -> Arrays.stream(x))
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n---------");

        Stream.of(a, b)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n---------");


        int[] reverseSortedMergedArray = List.of(a, b).stream()
                // 1. Flatten the Stream<int[]> into a single IntStream
                .flatMapToInt(Arrays::stream)

                // 2. Box the IntStream to Stream<Integer> to use a Comparator
                .boxed()

                // 3. Apply the reverse Comparator
                .sorted(Comparator.reverseOrder())

                // 4. Convert back to IntStream (unbox)
                .mapToInt(i -> i) // or .mapToInt(Integer::intValue)

                // 5. Convert the sorted stream back to an int array
                .toArray();

        System.out.println(Arrays.toString(reverseSortedMergedArray));
        System.out.println("==================================");

        //How do you merge two unsorted arrays into single sorted array without duplicates?
        int[] c = new int[]{4, 2, 7, 1};
        int[] d = new int[]{8, 1, 9, 2};

        int[] result = Stream.of(c, d)
                .flatMapToInt(Arrays::stream)
                .distinct()
                .sorted()
                .peek(x -> System.out.print(x + ""))
                .toArray();
        System.out.println("\n---------");

        stream(result).forEach(x -> System.out.print(x + " "));
        System.out.println("\n==================================");

        Consumer<Integer> soutspace = x -> System.out.print(x + " ");

        //How do you get three maximum numbers and three minimum numbers from the given list of integers?
        System.out.println("How do you get three maximum numbers and three minimum numbers from the given list of integers?");
        List<Integer> listOfIntegers4 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        List<Integer> sortedList = listOfIntegers4.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        sortedList.forEach(soutspace);
        System.out.println("\n---------");

        sortedList.stream()
                .limit(3)
                .forEach(soutspace);
        System.out.println("\n---------");

        sortedList.stream()
                .skip(sortedList.size() - 3)
                .limit(sortedList.size() - 3)
                .forEach(soutspace);
        System.out.println("\n==================================");

        System.out.println("Java 8 program to check if two strings are anagrams or not?");
        String s1 = "RaceCar";
        String s2 = "CarRace";
        //System.out.println(s1.toLowerCase().chars().mapToObj(ch -> (char) ch)
        //.sorted()
        // .collect(Collectors.toCollection(a)));
        System.out.println(s2.toLowerCase().chars().mapToObj(ch -> (char) ch)
                .sorted()
                .toString());

        s1.toLowerCase().chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println(s1.toLowerCase().chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .equals(s2.toLowerCase().chars().mapToObj(ch -> (char) ch)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))));


        String resultS1 = stream(s1.split(""))
                .map(String::toLowerCase)
                .sorted().collect(Collectors.joining());
        String resultS2 = stream(s2.split(""))
                .map(String::toLowerCase)
                .sorted().collect(Collectors.joining());
        System.out.println(resultS1.equals(resultS2));
        System.out.println("==================================");

        //Find sum of all digits of a number in Java 8?
        System.out.println("Find sum of all digits of a number in Java 8?");
        int i = 15623;
        int sum = stream(String.valueOf(i).split(""))
                .mapToInt(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println("Sum = " + sum);
        System.out.println("\n---------");
        System.out.println("Sum = " + (Integer) stream(String.valueOf(i)
                .split("")).mapToInt(Integer::parseInt).sum());
        System.out.println("==================================");

        //Find second largest number in an integer array?
        System.out.println("Find second largest number in an integer array?");
        List<Integer> listOfIntegers5 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        listOfIntegers5.stream()
                .sorted(Comparator.reverseOrder())
                .toList()
                .forEach(soutspace);
        System.out.println();
        listOfIntegers5.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
       listOfIntegers5.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().ifPresent(s -> System.out.print(s + " "));
        System.out.println("\n==================================");

        //Given a list of strings, sort them according to increasing order of their length?
        System.out.println("Given a list of strings, sort them according to increasing order of their length?");
        List<String> listOfStrings6 = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
        listOfStrings6.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        listOfStrings6.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toMap(
                        s-> s,
                        String::length,
                        (r1, r2) -> r1,
                        LinkedHashMap::new
                )).forEach((key, value) -> System.out.println(key + " " + value));


        //How do you find common elements between two arrays?
        List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
        List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
        Stream.of(list1, list2).flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " " + value));


        list1.stream().filter(list2::contains).forEach(System.out::println);
        list1.stream().filter(l -> list2.contains(l)).forEach(System.out::println);
        System.out.println("\n==================================");

        //Reverse each word of a string using Java 8 streams?
        String str = "Java Concept Of The Day";
        System.out.println(Stream.of(str.split(" "))
                .map(word -> new StringBuffer(word).reverse())
                .collect(Collectors.joining(" ")));
        System.out.println("==================================");


        //How do you find sum of first 10 natural numbers?
        System.out.println(IntStream.rangeClosed(1,10).sum());

        //Reverse an integer array
        int[] array = new int[] {5, 1, 7, 3, 9, 6};
        array = stream(array)
                .boxed()
                .collect(Collectors.toList())
                .reversed()
                .stream()
                .mapToInt(x -> x)
                .toArray();
        System.out.println(Arrays.toString(array));


        array = Arrays.stream(array) // Start with IntStream
                .boxed() // Convert to Stream<Integer> to use Comparator
                .sorted(Comparator.reverseOrder())
                .mapToInt(i1 -> i1) // CONVERSION FIX: Convert back to IntStream
                .toArray(); // Returns int[]
        System.out.println(Arrays.toString(array));

        //sort in reverse order and print
        array = stream(array)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(x -> x)
                .toArray();
        System.out.println(Arrays.toString(array));
        System.out.println("==================================");

        //Print all the even numbers in the range of 1 - 10
        IntStream.rangeClosed(1,10)
                .filter(integer -> integer % 2 == 0)
                .boxed()
                .forEach(soutspace);
        System.out.println("\n==================================");

        //Print first 10 even numbers
        IntStream.rangeClosed(1,10)
                .map(integer -> integer * 2)
                .boxed()
                .forEach(soutspace);
        System.out.println("\n==================================");

        //How do you find the most repeated element in an array?
        List<String> listOfStrings7 = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
        Map<String, Long> resultMap2 = listOfStrings7.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String ,Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        resultMap2.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("---------");

        Map.Entry<String, Long> resultEntry = listOfStrings7.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String ,Long>comparingByValue().reversed())
                .findFirst()
                .orElse(null);
        System.out.println(resultEntry.getKey() + " " + resultEntry.getValue());
        //.findFirst()
                //.orElseThrow(() -> new IllegalStateException("List cannot be empty"));
        System.out.println("==================================");






    }


}


