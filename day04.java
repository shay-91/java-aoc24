import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day04 {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File input = new File("input04.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                lines.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        char[][] matrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            matrix[i] = lines.get(i).toCharArray();
        }
        // part 1
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = 0; k < directions.length; k++) {
                    String test = "";
                    for (int p = 0; p < 4; p++) {
                        if (i + directions[k][0] * p < 0 || i + directions[k][0] * p >= matrix.length
                                || j + directions[k][1] * p < 0 || j + directions[k][1] * p >= matrix[i].length) {
                            break;
                        }
                        test += matrix[i + directions[k][0] * p][j + directions[k][1] * p];
                    }
                    if (test.equals("XMAS")) {
                        result++;
                    }
                }
            }
        }
        System.out.println("Part 1: " + result);
        // part 2
        int[][] directions2 = { { 1, 1 }, { 1, -1 } };
        result = 0;
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                String test1 = "";
                String test2 = "";
                for (int p = -1; p < 2; p++) {
                    test1 += matrix[i + directions2[0][0] * p][j + directions2[0][1] * p];
                    test2 += matrix[i + directions2[1][0] * p][j + directions2[1][1] * p];
                }
                if((test1.equals("MAS") || test1.equals("SAM")) && (test2.equals("MAS") || test2.equals("SAM"))){
                    result++;
                }
            }
        }
        System.out.println("Part 2: " + result);
    }
}
