import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomTestTwo {
    public static <T> void soutSpace(T t){
        System.out.print(t + " ");
    }
    public static void soutLine(){
        System.out.println("\n==================");
    }
    public static void main(String[] args) {
        //Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
        int[] nums = new int[]{10,15,8,49,25,98,32};
        Arrays.stream(nums).boxed()
                .filter(x -> x % 2 == 0)
                .sorted()
                .forEach(CustomTestTwo::soutSpace);
        soutLine();

        //Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
        myList.stream()
                .map(String::valueOf)
                .filter(x -> x.charAt(0)== '1')
                .forEach(CustomTestTwo::soutSpace);


        IntStream.of(2,2,2,3,3)
                .boxed()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0))
        ;
    }

}
