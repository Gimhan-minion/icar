package com.uom.icar.ui.quiz;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uom.icar.R;
import com.uom.icar.ui.home.HomeFragment;
import com.uom.icar.ui.register.RegisterFragment;

public class Quiz_Fragment extends Fragment {

    private QuizViewModel mViewModel;

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mScoreView,txtEnd;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button quit;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public static Quiz_Fragment newInstance() {
        return new Quiz_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_quiz_, container, false);


        mScoreView = view.findViewById(R.id.score);
        mQuestionView = view.findViewById(R.id.question);
        mButtonChoice1 = view.findViewById(R.id.choice1);
        mButtonChoice2 = view.findViewById(R.id.choice2);
        mButtonChoice3 = view.findViewById(R.id.choice3);
        quit = view.findViewById(R.id.quit);
        txtEnd= view.findViewById(R.id.txtEnd);

        txtEnd.setVisibility(View.GONE);

        updateQuestion();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment fragment = new HomeFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();

            }
        });
        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(getActivity().getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(getActivity().getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(getActivity().getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button3




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        // TODO: Use the ViewModel
    }

    private void updateQuestion(){
        if(mQuestionNumber<4){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else{
            mQuestionView.setVisibility(View.GONE);
            mButtonChoice1.setVisibility(View.GONE);
            mButtonChoice3.setVisibility(View.GONE);
            mButtonChoice2.setVisibility(View.GONE);
            txtEnd.setVisibility(View.VISIBLE);

        }

    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

}