package finder;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        String[] files = new String[3];
        for (int i = 1; i <= 3 ; i++) {
            files[i - 1] = "C:\\Users\\user\\Desktop\\Java\\text " + i + ".txt";
        }
        String words[] = {"Muso", "idk", "ker"};
        WordFinder.getOccurrences(files, words, "C:\\Users\\user\\Desktop\\Java\\JavaItis5\\wordfinder\\result.txt");
    }
}
