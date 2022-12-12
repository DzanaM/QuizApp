import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Quiz implements IQuiz {

    static Scanner input = new Scanner(System.in);
    private ArrayList<Question> questions = new ArrayList<>();


    public Quiz() {
    }

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static Scanner getInput() {
        return input;
    }


    public void addAllQuestions() {

        String fileName = "QuestionsAndAnswers.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(row -> {
                if (!row.isEmpty()) {
                    //if(row.length() ) {
                    Question question = new Question();

                    String[] result = row.split(", ");  //string split with delimiters
                    List<String> arrayList = Arrays.asList(result);

                    arrayList = Arrays.asList(result);  //pretvoriti array stringova u arrayList

                    if (arrayList.size() != 4) {

                        question.setText(arrayList.get(0));

                        int listSize = arrayList.size();
                        String correctAnswer = arrayList.get(listSize - 1);


                        ArrayList<Answer> answers = new ArrayList<>();
                        for (int i = 1; i < arrayList.size() - 1; i++) {
                            Answer answer = new Answer();
                            String text = arrayList.get(i);
                            answer.setAnswersText(text);

                            //java compare two strings
                            boolean isCorrect = correctAnswer.equals(text);
                            answer.setItCorrect(isCorrect);

                            answers.add(answer);
                        }

                        question.setAnswers(answers); //setovati ovdje

                        questions.add(question);

                    }
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean checkIsItCorrect(int userChoice,Question question) {
        for (int i = 0; i < question.getAnswers().size(); i++) {
            if (question.getAnswers().get(i).isItCorrect() && userChoice == i + 1) {
                return true;
            }
        }
        return false;
    }


    public void startQuiz() {
        System.out.println("Enter the player name: ");
        String name = input.nextLine();

        System.out.println("Quiz begins, good luck " + name);

        int numberOfPoints = 0;

        for (Question question : questions) {
            question.displayAnswers();


            System.out.println("Your answer is: ");
            int choice = input.nextInt();
            boolean isCorrect = checkIsItCorrect(choice,question);

            if (isCorrect) {
                numberOfPoints++;
            }
        }

        System.out.println("The player " + name + " has won " + numberOfPoints);

    }

}












