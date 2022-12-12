import java.util.ArrayList;

public class Question implements IQuestion {


    private String text;
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question() {
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }


    public void displayAnswers() {

        System.out.println(text);

        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ". " + answers.get(i).getAnswersText());

        }
    }


}




