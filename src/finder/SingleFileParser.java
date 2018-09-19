package finder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SingleFileParser {

    private List<String> resultList;
    private String[] words;

    public SingleFileParser(List<String> resultList, String[] words) {
        this.resultList = resultList;
        this.words = words;
    }

    public boolean parseSingleFile(String source) {

        try (Scanner scanner = new Scanner(new File(source))) {
            Pattern pattern = Pattern.compile("[\\?\\.\\!]");
            scanner.useDelimiter(pattern);
            while (scanner.hasNext()) {
                checkWordsInSentence(scanner.next(), words);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void checkWordsInSentence(String sentence, String[] words) {
        for (String word : words) {
            if (checkWordInSentence(sentence, word)) {
                saveResultSentence(sentence);
                break;
            }
        }
    }

    private boolean checkWordInSentence(String sentence, String word) {
        sentence = sentence.toLowerCase();
        return sentence.contains(word);

    }

    private void saveResultSentence(String sentence) {
        synchronized (resultList) {
            resultList.add(sentence);
        }
    }
}
