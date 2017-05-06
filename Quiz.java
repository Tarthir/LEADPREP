package model;

/**
 * Created by tyler on 5/5/2017.
 * A singleton class for quizzes, since we only want one quiz at a time
 */
public class Quiz {
    //private static Quiz ourInstance;
    private String[] currQuiz;
    private int currPosition;
    private String name;

    public Quiz(String name, String[] newQuiz) {
        this.name = name;
        currPosition = 0;
        this.currQuiz = newQuiz;
    }

    /**
     * Gets the current text at the given position and then increments the position counter
     *
     * @return The text for the current spot
     */
    public String getCurrPositionText() {
        return currQuiz[currPosition];
    }

    public void incrementPos(){
        currPosition++;
    }
    public  void decrementPos(){
        currPosition--;
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
}

