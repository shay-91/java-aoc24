import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Day01 {
    public void main(String[] args) {
        // part 1
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        try {
            File input = new File("input01.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] ids = data.split("   ", 0);
                int[] numbers = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
                list1.add(numbers[0]);
                list2.add(numbers[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Collections.sort(list1);
        Collections.sort(list2);
        int result = 0;
        for (int i = 0; i < list1.size(); i++) {
            result += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println("part 1: " + result);

        // part 2
        int predecessor = -1;
        int predecessorScore = 0;
        int i = 0;
        int j = 0;
        int score = 0;
        result = 0;
        while (i < list1.size()) {
            if (score == 0 && predecessor == list1.get(i)) {
                result += predecessorScore;
                i++;
            } else if (j == list2.size() || list1.get(i) < list2.get(j)) {
                result += score;
                predecessor = list1.get(i);
                predecessorScore = score;
                score = 0;
                i++;
            } else if (list1.get(i).equals(list2.get(j))) {
                score += list1.get(i);
                j++;
            } else {
                j++;
            }
        }
        System.out.println("part 2: " + result);
    }
}