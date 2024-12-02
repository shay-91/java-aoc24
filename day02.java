import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Day02 {
    public void main(String[] args) {
        int result1 = 0;
        int result2 = 0;
        try {
            File input = new File("input02.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] levels = data.split(" ", 0);
                int[] numbers = Arrays.stream(levels).mapToInt(Integer::parseInt).toArray();
                boolean safe = true;
                boolean increasing = numbers[0] < numbers[1];
                for (int i = 0; i < numbers.length - 1; i++) {
                    if (increasing && numbers[i] >= numbers[i + 1]) {
                        safe = false;
                        break;
                    } else if (!increasing && numbers[i] <= numbers[i + 1]) {
                        safe = false;
                        break;
                    } else if (Math.abs(numbers[i] - numbers[i + 1]) > 3) {
                        safe = false;
                        break;
                    }
                }
                if (safe) {
                    result1++;
                    result2++;
                } else {
                    Integer[] objectNumbers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
                    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(objectNumbers));
                    for (int i = 0; i < numbers.length; i++) {
                        @SuppressWarnings("unchecked")
                        ArrayList<Integer> currentList = (ArrayList<Integer>) list.clone();
                        currentList.remove(i);
                        safe = true;
                        increasing = currentList.get(0) < currentList.get(1);
                        for (int j = 0; j < numbers.length - 2; j++) {
                            if (increasing && currentList.get(j) >= currentList.get(j + 1)) {
                                safe = false;
                                break;
                            } else if (!increasing && currentList.get(j) <= currentList.get(j + 1)) {
                                safe = false;
                                break;
                            } else if (Math.abs(currentList.get(j) - currentList.get(j + 1)) > 3) {
                                safe = false;
                                break;
                            }
                        }
                        if (safe) {
                            result2++;
                            break;
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("part 1: " + result1);
        System.out.println("part 2: " + result2);
    }
}