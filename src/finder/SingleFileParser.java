package finder;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class SingleFileParser implements Callable<Set<String>> {

    private String source;
    private String[] words;
    private Set<String> sentences = new HashSet<>();

    public SingleFileParser(String source, String[] words) {
        this.words = words;
        this.source = source;
    }

    @Override
    public Set<String> call() throws Exception {
        try (Scanner scanner = new Scanner(new File(source))) {
            Pattern pattern = Pattern.compile("[\\?\\.\\!]");
            scanner.useDelimiter(pattern);
            while (scanner.hasNext()) {
                StringBuilder sb  = new StringBuilder();
                String sentence = scanner.next().replace(System.getProperty("line.separator"), "");

                Arrays.stream(words)
                        .filter(word -> sentence.toLowerCase().contains(word.toLowerCase()))
                        .forEach(x -> {
                            sb.append(sentence.trim())
                                    .append(scanner.findInLine("[?.!]"));
                            sentences.add(sb.toString());
                        });
            }
        } return sentences;
    }

}
