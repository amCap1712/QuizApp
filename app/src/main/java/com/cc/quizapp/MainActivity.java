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
        //loop to populate array by dynamically generating string resource ids
        for(int i=0;i<3;i++){
            //questionID to generate string resource ID of each question string
            //each string question resource is of form question_<Question number>
            String questionID = "question_";
            questionID += String.valueOf(i+1);
            //get resource ID of string dynamically
            int resID = getResources().getIdentifier(questionID,"string",getPackageName());
            //add question to the array
            myDataSet[i][0] = getResources().getString(resID);
            // loop to fill choices in the array
            for(int j=1;j<4;j++) {
                //answerID to generate each answer choice resource ID
                //each string answer resource ID is of form question_<QuestionNumber>_choice_<ChoiceNumber>
                String answerID = questionID + "_choice_";
                answerID += String.valueOf(j);
                //get resource ID of string choice dynamically
                resID = getResources().getIdentifier(answerID,"string",getPackageName());
                //add choice to array
                myDataSet[i][j] = getResources().getString(resID);
            }
            //add correct answers to array for validation
            myDataSet[0][4] = String.valueOf(R.id.choice2);
            myDataSet[1][4] = String.valueOf(R.id.choice1);
            myDataSet[2][4] = String.valueOf(R.id.choice1);
        }
    }
}