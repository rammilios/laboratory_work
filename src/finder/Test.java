package finder;

public class Test {
    public static void main(String[] args) {
        WordFinder finder = new WordFinder();

        String[] files = new String[5];
        for (int i = 1; i <= 5 ; i++) {
            files[i - 1] = "C:\\Users\\user\\Desktop\\Java\\text " + i + ".txt";
        }
        String words[] = {"tmom", "mfa", "migpcv"};
        finder.getOccurences(files, words, "C:\\Users\\user\\Desktop\\Java\\JavaItis5\\wordfinder\\result.txt");
    }
}
