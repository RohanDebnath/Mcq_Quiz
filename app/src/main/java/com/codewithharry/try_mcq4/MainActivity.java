package com.codewithharry.try_mcq4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = "https://raw.githubusercontent.com/HyperPritu/App-Development/main/mcq.json";
    RequestQueue requestQueue;
    //Rohan ek chutiya hai
    Button button1, button2,button3, button4,next;
    TextView textView_question,Score_vi,high_score,NO_Q;
    ArrayList<Question>  questionitem = new ArrayList<>();
    int questionIndex=0;
    int correct=0,wrong=0,score=0;
    private Pref pref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        button1=findViewById(R.id.Answer1);
        button2=findViewById(R.id.Answer2);
        button3=findViewById(R.id.Answer3);
        button4=findViewById(R.id.Answer4);
        next=findViewById(R.id.next);
        textView_question=findViewById(R.id.quesyion_text);
        Score_vi=findViewById(R.id.score_view);
        high_score=findViewById(R.id.Highest_score);
        NO_Q=findViewById(R.id.no_of_q);
        pref = new Pref(MainActivity.this);

        high_score.setText(MessageFormat.format("HighestScore:{0}",String.valueOf(pref.getHighScore())));

        requestQueue.add(request);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_question.setText(questionitem.get(questionIndex).getQuestion());
                button1.setText(questionitem.get(questionIndex).getOp1());
                button2.setText(questionitem.get(questionIndex).getOp2());
                button3.setText(questionitem.get(questionIndex).getOp3());
                button4.setText(questionitem.get(questionIndex).getOp4());
                quesIndprogression();
                NO_Q.setText((questionIndex +"/"+questionitem.size()));
                pref.saveHighestScore(score);
                 Log.d("score_high","onCreate"+pref.getHighScore());
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionitem.get(questionIndex-1).getOp1()
                        .equals(questionitem.get(questionIndex-1).getCorrect()))
                {
                    //Correct
                    Toast.makeText(MainActivity.this,"Correct !",Toast.LENGTH_LONG).show();
                    score++;
                    correct++;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bhag sala vul bole wara",Toast.LENGTH_LONG).show();
                    wrong++;
                    score--;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));

                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionitem.get(questionIndex-1).getOp2()
                        .equals(questionitem.get(questionIndex-1).getCorrect()))
                {
                    //Correct
                    Toast.makeText(MainActivity.this,"Correct !",Toast.LENGTH_LONG).show();
                    score++;
                    correct++;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bhag sala vul bole wara",Toast.LENGTH_LONG).show();
                    wrong++;
                    score--;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));

                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionitem.get(questionIndex-1).getOp3()
                        .equals(questionitem.get(questionIndex-1).getCorrect()))
                {
                    //Correct
                    Toast.makeText(MainActivity.this,"Correct !",Toast.LENGTH_LONG).show();
                    score++;
                    correct++;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bhag sala vul bole wara",Toast.LENGTH_LONG).show();
                    wrong++;
                    score--;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));

                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionitem.get(questionIndex-1).getOp4()
                        .equals(questionitem.get(questionIndex-1).getCorrect()))
                {
                    //Correct
                    Toast.makeText(MainActivity.this,"Correct !",Toast.LENGTH_LONG).show();
                    score++;
                    correct++;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bhag sala vul bole wara",Toast.LENGTH_LONG).show();
                    wrong++;
                    score--;
                    Score_vi.setText(MessageFormat.format("Score{0}", String.valueOf(score)));
                }
            }

        });
    }

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jsonArray = response.getJSONArray("questions");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.d("try4", "QUes" + response.toString());
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String quesTion = obj.getString("ques");
                    Log.d("TheQare", "QUes" + quesTion);
                    String option1 = obj.getString("op1");
                    String option2 = obj.getString("op2");
                    String option3 = obj.getString("op3");
                    String option4 = obj.getString("op4");
                    String correct_ans = obj.getString("correct");

                    questionitem.add(new Question(
                            quesTion,
                            option1,option2,option3,option4,
                            correct_ans
                    ));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("try4","Failed");
        }
    });
    private void setQuestionScreen(int number)
    {
        textView_question.setText(questionitem.get(number).getQuestion());
        button1.setText(questionitem.get(number).getOp1());
        button2.setText(questionitem.get(number).getOp2());
        button3.setText(questionitem.get(number).getOp3());
        button4.setText(questionitem.get(number).getOp4());

    }
    private void quesIndprogression() {
        if(questionIndex<questionitem.size()-1)
        {
            questionIndex=(questionIndex+1)% questionitem.size();
        }
    }
}

