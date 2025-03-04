package com.techxcel.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView qtext, resultText;
    private Button option1, option2, option3, option4, nextBtn;
    private int questionIndex = 0;
    private String[][] questions = {
            {"What is the capital of India?",  "Mumbai", "Kolkata", "Chennai","Delhi", "Delhi"},
            {"Who invented Java?", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum", "James Gosling"},
            {"What is 2 + 2?", "3", "4", "5", "6", "4"},
            {"What is the speed of light?", "150,000 km/s", "450,000 km/s","300,000 km/s","600,000 km/s", "300,000 km/s"},
            {"What is the national animal of India?","Elephant","Tiger","Lion","Peacock","Tiger"},
            {"Which planet is known as the red planet?","Venus","Mars","Jupiter","Saturn","Mars"},
            {"What is the chemical symbol for Gold","Ag","Fe","Au","Pb", "Au"},
            {"Who wrote the play 'Romeo and Juliet'?","William Shakesphere","Charles Dickens","Jane Austen","Mark Twain","William Shakesphere"},
            {"WHich is the largest ocean on Earth?","Atlantic Ocean","Indian Ocean","Arctic Ocean","Pacific Ocean","Pacific Ocean"}


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        qtext = findViewById(R.id.qtext);
        resultText = findViewById(R.id.resultText);
        nextBtn = findViewById(R.id.nextBtn);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        // Load the first question
        loadQuestion();

        // Set up the answer click listeners
        View.OnClickListener answerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button selectedBtn = (Button) v;
                checkAnswer(selectedBtn.getText().toString());
            }
        };

        option1.setOnClickListener(answerListener);
        option2.setOnClickListener(answerListener);
        option3.setOnClickListener(answerListener);
        option4.setOnClickListener(answerListener);

        // Next question button listener
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex < questions.length - 1) {
                    questionIndex++;
                    loadQuestion();
                    resultText.setText(""); // Clear result text for the next question
                } else {
                    qtext.setText("Quiz Completed!");
                    resultText.setText("");
                    option1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);
                    option4.setVisibility(View.GONE);
                    nextBtn.setEnabled(false);
                }
            }
        });
    }

    // Load the current question and its options
    private void loadQuestion() {
        qtext.setText(questions[questionIndex][0]);
        option1.setText(questions[questionIndex][1]);
        option2.setText(questions[questionIndex][2]);
        option3.setText(questions[questionIndex][3]);
        option4.setText(questions[questionIndex][4]);
    }

    // Check if the selected answer is correct
    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = questions[questionIndex][5];

        if (selectedAnswer.equals(correctAnswer)) {
            resultText.setText("Correct! ✅");
            resultText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            resultText.setText("Wrong! ❌ The correct answer is: " + correctAnswer);
            resultText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
}