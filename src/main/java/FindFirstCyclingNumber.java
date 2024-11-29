import java.util.HashMap;

public class FindFirstCyclingNumber {
    public static int findNumber(int[] numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                return i;
            }
            map.put(numbers[i], i);
        }
        return -1; 
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 2, 3, 4};
        int result = findNumber(numbers);
        System.out.println(result);
    }
}

