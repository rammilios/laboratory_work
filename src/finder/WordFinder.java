package finder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class WordFinder {

    private List<String> resultList = new ArrayList<>();

    public void getOccurences(String[] sources, String[] words, String res) {

        ExecutorService threadPool = Executors.newFixedThreadPool(sources.length);
        SingleFileParser singleFileParser = new SingleFileParser(resultList, words);

        List<Future<Boolean>> futures = new ArrayList<>();
        for (int i = 0; i < sources.length ; i++) {
            final int j = i;
            futures.add(CompletableFuture.supplyAsync(() -> singleFileParser.parseSingleFile(sources[j]), threadPool));
        }

        String result = "";
        for (Future<Boolean> future : futures) {
            try {
                result += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();

        System.out.println(resultList);
        save("result.txt");
    }

    private void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : resultList) {
                writer.write(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
