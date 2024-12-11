import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class day07{
    static long numbers[];
    public static void main(String[] args){
        try {
            long result1 = 0;
            long result2 = 0;
            File input = new File("input07.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] splitted = data.split(": ");
                long res = Long.parseLong(splitted[0]);
                numbers = Arrays.stream(splitted[1].split(" ")).mapToLong(Long::parseLong).toArray();
                if(isPossible(res, numbers[0], 1)){
                    result1 += res;
                }
                if(isPossible2(res, numbers[0], 1)){
                    result2 += res;
                }
            }
            System.out.println("Result 1: "+ result1);
            System.out.println("Result 2: "+ result2);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static boolean isPossible(long res, long sum, int i){
        if(res < sum){
            return false;
        }
        if(i == numbers.length){
            return res == sum;
        }
        return isPossible(res, sum + numbers[i], i+1) || isPossible(res, sum * numbers[i], i+1);
    }

    static boolean isPossible2(long res, long sum, int i){
        if(res < sum){
            return false;
        }
        if(i == numbers.length){
            return res == sum;
        }
        long newSum = Long.parseLong("" + sum + numbers[i]);
        return isPossible2(res, sum + numbers[i], i+1) || isPossible2(res, sum * numbers[i], i+1) || isPossible2(res, newSum, i+1);
    }
}