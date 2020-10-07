package com.codecademy.unquote;
 /**
 @author c0untry-c0der@github
 @date 9/23/20
 **/
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int totalCorrect;
    int totalQuestions;
    int currentQuestionIndex;
    ArrayList<Question> questions;

    ImageView questionImageView = findViewById(R.id.iv_main_question_image);
    TextView questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
    TextView questionTextView = findViewById(R.id.tv_main_question_title);
    Button answer0Button = findViewById(R.id.btn_main_answer_0);
    Button answer1Button = findViewById(R.id.btn_main_answer_1);
    Button answer2Button = findViewById(R.id.btn_main_answer_2);
    Button answer3Button = findViewById(R.id.btn_main_answer_3);
    Button submitButton = findViewById(R.id.btn_main_submit_answer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

// TODO 4-E: set onClickListener for each answer Button

// TODO 5-A: set onClickListener for the submit answer Button
        startNewGame();
}
/*
        void displayQuestion(Question question) {
            questionImageView.setImageResource(question.imageId);
            questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
            answer1Button.setText(question.answer1);
            answer2Button.setText(question.answer2);
            answer3Button.setText(question.answer3);
        }
*/
        void displayQuestionsRemaining(int questionsRemaining) {
            questionsRemainingTextView.setText(questions.size());
        }

// TODO 4-A: onAnswerSelected(int answerSelected) {..}

        void onAnswerSubmission () {
            Question currentQuestion = getCurrentQuestion();
            if (currentQuestion.isCorrect()) {
                displayQuestionsRemaining(questions.size());
                totalCorrect++;
            }
            questions.remove(currentQuestion);
            displayQuestionsRemaining(questions.size());

            if (questions.size() == 0) {
                String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);
                System.out.println(gameOverMessage);
            } else {
                chooseNewQuestion();
//                displayQuestion(getCurrentQuestion());
            }
        }

        void startNewGame () {
            Question q0 = new Question(R.drawable.img_quote_0,
                "Pretty good advice, and perhaps a scientist did say it...Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
            Question q1 = new Question(R.drawable.img_quote_1,
                "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
            Question q2 = new Question(R.drawable.img_quote_2,
                "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
            Question q3 = new Question(R.drawable.img_quote_3,
                "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?!", "Nelson Mandela", "Harriet Tubman1", "Mahatma Gandhi", "Nicholas Klein", 3);
            Question q4 = new Question(R.drawable.img_quote_4,
                "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
            Question q5 = new Question(R.drawable.img_quote_5,
                "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria",
                0);
            questions = new ArrayList();
            questions.add(q0);
            questions.add(q1);
            questions.add(q2);
            questions.add(q3);
            questions.add(q4);
            questions.add(q5);
            totalCorrect = 0;
            totalQuestions = questions.size();
            Question firstQuestion = chooseNewQuestion();
            displayQuestionsRemaining(questions.size());
//            displayQuestion(firstQuestion);
        }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int)result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}