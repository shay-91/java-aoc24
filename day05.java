import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day05 {
    public static void main(String[] args){
        int[][] matrix = new int[100][100];
        try {
            File input = new File("input05.txt");
            Scanner scanner = new Scanner(input);
            // rules
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if(data.equals("")){
                    break;
                }
                String[] line = data.split("\\|");
                int[] rule = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
                matrix[rule[0]][rule[1]] = 1;
                matrix[rule[1]][rule[0]] = -1;
            }
            // updates
            int result1 = 0;
            int result2 = 0;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] line = data.split(",");
                int[] update = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
                if(isOrdered(matrix, update)){
                    result1 += update[update.length / 2];
                }else{
                    result2 += orderedValue(matrix, update);
                }
            }
            System.out.println("Part 1: " + result1);
            System.out.println("Part 2: " + result2);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    static boolean isOrdered(int[][] matrix, int[] update){
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> notRight = new ArrayList<>();
        for(int i = 0; i < update.length; i++){
            Integer number = update[i];
                for(int j = 0; j < matrix[number].length; j++){
                    if(matrix[number][j] == -1){
                        notRight.add(j);
                    }
                    if(matrix[number][j] == 1 && left.contains(j)){
                        return false;
                    }                
                }
                if(notRight.contains(number)){
                    return false;
                }
            left.add(number);
        }
        return true;
    }

    static int orderedValue(int[][] matrix, int[] update){
        int[] ordered = new int[update.length];
        ArrayList<ArrayList<Integer>> before = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < update.length; i++){
            before.add(new ArrayList<Integer>());
            for(int j = 0; j < update.length; j++){
                if (i == j){
                    continue;
                }
                if(matrix[update[i]][update[j]] == -1){
                    before.get(i).add(update[j]);
                }
            }
        }
        for(int i = 0; i < update.length; i++){
            for(int j = 0; j < update.length; j++){
                if(before.get(j).isEmpty()){
                    Integer number = update[j];
                    ordered[i] = number;
                    before.get(j).add(-1);
                    for(int k = 0; k < update.length; k++){
                        if(before.get(k).contains(number)){
                            before.get(k).remove(number);
                        }
                    }
                    break;
                }
            }
        }
        return ordered[ordered.length / 2];
    }
}