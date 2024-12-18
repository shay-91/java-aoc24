import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day09 {
    public static void main(String[] args){
        char[] data = new char[0];
        try {
            File input = new File("input09.txt");
            Scanner scanner = new Scanner(input);
            data = scanner.nextLine().toCharArray();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // part 1
        ArrayList<int[]> positionMap = new ArrayList<>();
        ArrayList<Integer> free = new ArrayList<>();
        int position = 0;
        for(int i = 0; i < data.length; i++){
            if(i % 2 == 0){
                int length = Character.getNumericValue(data[i]);
                int[] positions = new int[length];
                for(int j = 0; j < length; j++){
                    positions[j] = position++;
                }
                positionMap.add(positions);
            }else{
                int length = Character.getNumericValue(data[i]);
                for(int j = 0; j < length; j++){
                    free.add(position++);
                }
            }
        }
        int id = positionMap.size() - 1;
        int block = positionMap.get(id).length - 1; // last position of id i
        int freeBlock = 0; // next free space
        while(positionMap.get(id)[block] > free.get(freeBlock)){
            positionMap.get(id)[block--] = free.get(freeBlock++);
            if(block < 0){
                id--;
                block = positionMap.get(id).length - 1;
            }
        }
        long checkSum = 0;
        for(int p = 0; p < positionMap.size(); p++){
            for(int q = 0; q < positionMap.get(p).length; q++){
                checkSum += p * positionMap.get(p)[q];
            }
        }
        System.out.println("Part 1: " + checkSum);

        // part 2
        ArrayList<int[]> fileMap = new ArrayList<>();
        ArrayList<int[]> freeMap = new ArrayList<>();
        position = 0;
        for(int i = 0; i < data.length; i++){
            if(i % 2 == 0){
                int length = Character.getNumericValue(data[i]);
                int[] positions = new int[]{position, length};
                fileMap.add(positions);
                position += length;
            }else{
                int length = Character.getNumericValue(data[i]);
                int[] freePositions = new int[]{position, length};
                freeMap.add(freePositions);
                position += length;
            }
        }
        for(int i = fileMap.size() - 1; i >= 0; i--){
            int size = fileMap.get(i)[1];
            for(int j = 0; j < freeMap.size(); j++){
                // accidentally moved files further right :/
                if(fileMap.get(i)[0] < freeMap.get(j)[0]){
                    break;
                }
                if(size <= freeMap.get(j)[1]){
                    fileMap.get(i)[0] = freeMap.get(j)[0];
                    freeMap.get(j)[1] -= size;
                    freeMap.get(j)[0] += size;
                    break;
                }
            }
        }
        checkSum = 0;
        for(int i = 0; i < fileMap.size(); i++){
            int start = fileMap.get(i)[0];
            for(int j = 0; j < fileMap.get(i)[1]; j++){
                checkSum += i * (start + j);
            }
        }
        System.out.println("Part 2: "+ checkSum);
    }
}
