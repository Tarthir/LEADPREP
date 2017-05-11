package model;

/**
 * Created by tyler on 5/5/2017.
 * A singleton class for quizzes, since we only want one quiz at a time
 */
public class Quiz {
    private String[] currQuiz;
    private String[] currQuizAnswers;
    private String name;

    public Quiz(String name, String[] newQuiz,String[] newQAnswers) {
        this.name = name;
        this.currQuiz = newQuiz;
        this.currQuizAnswers = newQAnswers;
    }

    /**
     * Gets the current text at the given position and then increments the position counter
     * @param currPosition , the current position of the cards
     * @return String, The text for the current spot
     */
    public String getCurrQuestionText(int currPosition) {
        return currQuiz[currPosition];
    }

    /**Gets text for the answer to the current question
     * @param currPosition , the current position of the cards
     * @return String, the text for the answer*/
    public String getCurrAnswerText(int currPosition){
        return currQuizAnswers[currPosition];
    }

    public String getName() {
        return name;
    }

    public String[] getCurrQuiz() {
        return currQuiz;
    }

}

