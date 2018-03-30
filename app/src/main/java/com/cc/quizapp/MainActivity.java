package com.cc.quizapp;

//importing required java classes
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//declaring activity class
public class MainActivity extends AppCompatActivity {
    //declaring variables to manage Recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //array to store questions and answers
    private String[][] myDataSet;
    //onCreate method is called upon creation of activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //store the recycler view
        mRecyclerView = findViewById(R.id.recycler_view);
        //optimize recycler view for fixed size items
        mRecyclerView.setHasFixedSize(true);
        //create layout manager to arrange elements of Recycler view
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //fill the string array with data
        getMyDataSet();
        //create and set the adapter on Recycler view
        mAdapter = new CustomAdapter(myDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }
    //method to generate questions and answers
    public void getMyDataSet() {
        //declare string array
        myDataSet = new String[3][5];
        //obtain questions
        String questions[] = getResources().getStringArray(R.array.questions);
        //obtain choices
        String choices[] = getResources().getStringArray(R.array.choices);
        int choiceNumber = 0;
        //loop to fill questions and answers
        for(int i=0;i<3;i++) {
            myDataSet[i][0] = questions[i];
            for (int j = 1; j < 4; j++) {
                myDataSet[i][j] = choices[choiceNumber];
                choiceNumber++;
            }
        }
            //add correct answers to array for validation
            myDataSet[0][4] = String.valueOf(R.id.choice2);
            myDataSet[1][4] = String.valueOf(R.id.choice1);
            myDataSet[2][4] = String.valueOf(R.id.choice1);
    }
}
