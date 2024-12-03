import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day03 {
    public static void main(String[] args) {
        StringBuilder lines = new StringBuilder();
        try {
            File input = new File("input03.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                lines.append(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String memory = lines.toString();

        boolean enabled = true;
        int result1 = 0;
        int result2 = 0;
        int i = 0;
        while (i < memory.length()) {
            if (memory.length() >= i + 4 && memory.substring(i, i + 4).equals("do()")){
                i+=4;
                enabled = true;
                continue;
            }
            if (memory.length() >= i + 7 && memory.substring(i, i + 7).equals("don't()")){
                i+=7;
                enabled = false;
                continue;
            }
            if (memory.length() < i + 4 || !memory.substring(i, i + 4).equals("mul(")){
                i++;
                continue;
            }
            i += 4;
            int j = 0;
            String fst = "";
            while(memory.length() > i+j && Character.isDigit(memory.charAt(i+j))){
                fst += memory.charAt(i+j);
                j++;
            }
            i += j;
            if(fst.length() == 0 || fst.length() > 3){
                continue;        
            }
            if(memory.length() <= i || !(memory.charAt(i) == ',')){
                continue;
            }
            i++;
            j = 0;
            String snd = "";
            while(memory.length() > i+j && Character.isDigit(memory.charAt(i+j))){
                snd += memory.charAt(i+j);
                j++;
            }
            i += j;
            if(snd.length() == 0 || snd.length() > 3){
                continue;
            }
            if(memory.length() <= i || !(memory.charAt(i) == ')')){
                continue;
            }
            i++;
            int product = Integer.parseInt(fst) * Integer.parseInt(snd);
            result1 += product;
            if(enabled){
                result2 += product;
            }
        }
        System.out.println("Part 1: " + result1);
        System.out.println("Part 2: " + result2);
    }
} 
         
