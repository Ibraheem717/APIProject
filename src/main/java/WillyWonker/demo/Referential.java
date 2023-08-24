package WillyWonker.demo;

public class Referential {
    private String category;
    private String question;
    private String answer;

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return ("Question: " + this.question + "\nAnswer: " + this.answer);
    }
}
