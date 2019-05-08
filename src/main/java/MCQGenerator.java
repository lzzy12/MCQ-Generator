import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sun.tools.jdeprscan.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MCQGenerator {
    private List<String> questions;
    private List<List<String>> options; // 2-D List
    private List<Integer> answers;
    private int quesPos;
    private int answerPos;
    private int optionPos;
    MCQGenerator(String csvPath){
        quesPos = 0;
        answerPos = 0;
        optionPos = 0;
        questions = new ArrayList<>();
        options = new ArrayList<>();
        answers = new ArrayList<>();
        try
        {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvPath)).withSkipLines(1).build();
            init(csvReader);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    MCQGenerator(File file){
        try
        {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();
            init(csvReader);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void init(CSVReader csvReader){
        try {
            for (String[] str : csvReader.readAll()) {
                questions.add(str[0]);
                options.add(Arrays.asList(str[1], str[2], str[3], str[4]));
                answers.add(Integer.valueOf(str[5]));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    String nextQuestion(){
        if(quesPos >= questions.size())
            return null;
        return questions.get(quesPos++);
    }
    List<String> nextOptions(){
        return options.get(optionPos++);
    }
    Integer nextAnswer(){
        return answers.get(answerPos++);
    }
}
