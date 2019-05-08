import jdk.nashorn.api.scripting.URLReader;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int score = 0;
        final int correctScore = 5;
        final int wrongScore = -1;
        Scanner cin = new Scanner(System.in);

        try
        {
            URL res = Driver.class.getResource("mcq.csv");
            File file = Paths.get(res.toURI()).toFile();
            MCQGenerator mcqGenerator = new MCQGenerator(file);
            String nextQuest;
            while ((nextQuest = mcqGenerator.nextQuestion()) != null) {
                System.out.println(nextQuest);
                List<String> options = mcqGenerator.nextOptions();
                for (int i = 0; i < 4; i++) {
                    System.out.println((i + 1) + ". " + options.get(i));
                }
                if (cin.nextInt() == mcqGenerator.nextAnswer()) {
                    score += correctScore;
                } else score += wrongScore;
            }
            System.out.println("Your score: " + score);
         } catch (Exception e){
             e.printStackTrace();
        }
    }
}
