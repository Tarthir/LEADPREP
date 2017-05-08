package model;

/**
 * Created by tyler on 5/5/2017.
 * A singleton class for quizzes, since we only want one quiz at a time
 */
public class Quiz {
    //private static Quiz ourInstance;
    private String[] currQuiz;
    private String[] currQuizAnswers;
    private int currPosition;
    private String name;

    public Quiz(String name, String[] newQuiz,String[] newQAnswers) {
        this.name = name;
        currPosition = 0;
        this.currQuiz = newQuiz;
        this.currQuizAnswers = newQAnswers;
    }

    /**
     * Gets the current text at the given position and then increments the position counter
     *
     * @return String, The text for the current spot
     */
    public String getCurrQuestionText() {
        return currQuiz[currPosition];
    }
    /**Gets text for the answer to the current question
     *
     * @return String, the text for the answer*/
    public String getCurrAnswerText(){
        return currQuizAnswers[currPosition];
    }

    public String getName() {
        return name;
    }

    public String[] getCurrQuiz() {
        return currQuiz;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public int getCurrPosition() {
        return currPosition;
    }
}

