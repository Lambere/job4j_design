import java.util.ArrayList;
import java.util.HashMap;

public class FindFirstCyclingNumber {
    public static int findNumber(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            if (list.contains(numbers[i])) {
                return i;
            }
            list.add(numbers[i]);
        }
        return -1; 
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 2, 3, 4};
        int result = findNumber(numbers);
        System.out.println(result);
    }
}

