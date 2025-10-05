import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodingQns {
    public static void soutLine() {
        System.out.println("================");

    }

    public static void main(String[] args) {
        /**
         * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
         * Input: nums = [1,2,3,1]
         * Output: true
         *
         * Input: nums = [1,2,3,4]
         * Output: false
         * */
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
        myList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        /**
         * Input: nums = [1,2,3,4]
         * Output: false
         */
        int[] nums1 = new int[]{1, 2, 3, 1};
        int[] nums2 = new int[]{1, 2, 3, 4};

        System.out.println(Arrays.stream(nums1).distinct().count() != nums1.length);
        System.out.println(Arrays.stream(nums2).distinct().count() != nums1.length);

        /**
         * Java 8 program to perform cube on list elements and filter numbers greater than 50.
         * List<Integer> integerList = Arrays.asList(4,5,6,7,1,2,3);
         * Output:
         * 64
         * 125
         * 216
         * 343
         */
        List<Integer> integerList = Arrays.asList(4, 5, 6, 7, 1, 2, 3);
        integerList.stream()
                .map(x -> x * x * x)
                .filter(x -> x > 50)
                .forEach(System.out::println);


        /**
         *  Write a program to print the count of each character in a String?
         * 			Input: String s = "string data to count each character";
         * 			Output: {s=1, t=5, r=3, i=1, n=2, g=1,  =5, d=1, a=5, o=2, c=4, u=1, e=2, h=2}
         */
        String s = "string data to count each character";
        s.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " " + v));
        soutLine();
        /**
         * Find first non-repeated character in a string?
         * 	String inputString = "Java Concept Of The Day";
         * 	output: j
         */
        String inputString = "Java Concept Of The Day";
        inputString.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " " + v));
        Map<Character, Long> resultMap = inputString.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        resultMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(System.out::println);
        soutLine();

        /**
         Reverse each word of a string using Java 8 streams?
         String str = "Java Concept Of The Day";
         Output : avaJ tpecnoC fO ehT yaD
         */
        String str = "Java Concept Of The Day";
        String resultStr = Arrays.stream(str.split(" "))
                .map(s2 -> new StringBuilder(s2).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println("Output : " + resultStr);
        soutLine();

        /**
         * Find the age of a person in years if the birthday has given?
         * 1985, 01, 23
         */
        LocalDate today = LocalDate.now();
        LocalDate birthdate = LocalDate.of(1985, 01, 23);
        Period period = Period.between(birthdate, today);
        System.out.println("Age : " + period.getYears());

    }
}
