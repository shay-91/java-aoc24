import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day08 {
    public static void main(String[] args) {
        ArrayList<char[]> lines = new ArrayList<>();
        try {
            File input = new File("input08.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                lines.add(data.toCharArray());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        char[][] antennaMap = lines.toArray(new char[lines.size()][]);
        boolean[][] antinodeMap = new boolean[antennaMap.length][antennaMap[0].length];
        boolean[][] antinodeMap2 = new boolean[antennaMap.length][antennaMap[0].length];
        HashMap<Character, ArrayList<int[]>> dict = new HashMap<>();
        for (int i = 0; i < antennaMap.length; i++) {
            for (int j = 0; j < antennaMap[i].length; j++) {
                Character c = antennaMap[i][j];
                if (c == '.') {
                    continue;
                }
                if (!dict.containsKey(c)) {
                    dict.put(c, new ArrayList<int[]>());
                }
                dict.get(c).add(new int[] { i, j });
            }
        }
        for (Character c : dict.keySet()) {
            ArrayList<int[]> l = dict.get(c);
            int[][] coords = l.toArray(new int[l.size()][]);
            for (int i = 0; i < coords.length - 1; i++) {
                for (int j = i + 1; j < coords.length; j++) {
                    int[] diff = new int[] { coords[j][0] - coords[i][0], coords[j][1] - coords[i][1] };
                    int[][] points = new int[][] { { coords[j][0] + diff[0], coords[j][1] + diff[1] },
                            { coords[i][0] - diff[0], coords[i][1] - diff[1] } };
                    for (int k = 0; k < 2; k++) {
                        if (points[k][0] >= 0 && points[k][0] < antinodeMap.length && points[k][1] >= 0
                                && points[k][1] < antinodeMap[0].length) {
                            antinodeMap[points[k][0]][points[k][1]] = true;
                        }
                    }
                    
                    // part 2
                    int[] point = coords[i].clone();
                     
                    while(point[0] >= 0 && point[0] < antinodeMap2.length &&  point[1] >= 0 && point[1] < antinodeMap2[0].length){
                        antinodeMap2[point[0]][point[1]] = true;
                        point[0] += diff[0];
                        point[1] += diff[1];
                    }
                    point = coords[i].clone();
                    while(point[0] >= 0 && point[0] < antinodeMap2.length &&  point[1] >= 0 && point[1] < antinodeMap2[0].length){
                        antinodeMap2[point[0]][point[1]] = true;
                        point[0] -= diff[0];
                        point[1] -= diff[1];
                    }
                }
            }
        }
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < antinodeMap.length; i++) {
            for (int j = 0; j < antinodeMap[i].length; j++) {
                if (antinodeMap[i][j]) {
                    result1++;
                }
                if (antinodeMap2[i][j]) {
                    result2++;
                }
            }
        }
        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
    }
}
