package model;

/**
 * Created by tyler on 5/4/2017.
 */

public enum Quiz {
    CHAINOFCOMMAND(0),
    MAJCOM(1),
    MISSION(2),
    CODE(4),
    QUOTES(5),
    SONG(6),
    CREED(7),
    MyQuiz(8);

    private int quizNum;

    Quiz(int num){
        quizNum = num;
    }

    public int getQuizNum() {
        return quizNum;
    }
}
