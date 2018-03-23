package com.cc.quizapp;
//import required java classes
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//declare a CustomAdapter class RecyclerView
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    //Data Set to populate adapter
    private String[][] myDataSet;

    //View holder class to create holders for items in RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView question;
        private RadioGroup answers;
        private RadioButton choice1, choice2, choice3;
        private int correctAnswer;
        //constructor to create layout for each item
        ViewHolder(CardView mCardView){
            //adding all views
            super(mCardView);
            question = mCardView.findViewById(R.id.question);
            answers = mCardView.findViewById(R.id.answers);
            choice1 = mCardView.findViewById(R.id.choice1);
            choice2 = mCardView.findViewById(R.id.choice2);
            choice3 = mCardView.findViewById(R.id.choice3);
            //set onClickListener for radio button to validate answer
            answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //validate answer and display the toast correctly
                    //correctAnswer stores resource id of correct choice radio button
                    //therefore for validation it is compared with id of the clicked radio button
                    if(checkedId == correctAnswer)
                        Toast.makeText(group.getContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(group.getContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //method to set correct AnswerId
        void setCorrectAnswer(int answerID){correctAnswer = answerID;}
    }
    //constructor to create adapter from DataSet
    CustomAdapter(String[][] mDataSet){
        myDataSet = mDataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        CardView cardView = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.question_layout, parent, false);
        return new ViewHolder(cardView);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get element from your dataSet at this position & replace the contents of the view with that element
        holder.question.setText(myDataSet[position][0]);
        holder.choice1.setText(myDataSet[position][1]);
        holder.choice2.setText(myDataSet[position][2]);
        holder.choice3.setText(myDataSet[position][3]);
        holder.setCorrectAnswer(Integer.parseInt(myDataSet[position][4]));
    }
    //return number of items in Adapter
    @Override
    public int getItemCount(){
        return myDataSet.length;
    }
}