package scientificcalculatorapp.scientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScientificCalculator extends AppCompatActivity {
    Button Add,Subtract,Multiply,Divide,zero,one,two,three,four,five,six,seven,eight,nine;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calculator);
        InitialiseViews();
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
               clicked=true;
            }
        });

        Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });

        Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });

        Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
            }
        });
    }
    void InitialiseViews()
    {
        try{
            Add=(Button)findViewById(R.id.buttonplus);
            Subtract=(Button)findViewById(R.id.buttonminus);
            Multiply=(Button)findViewById(R.id.buttonmultiply);
            Divide=(Button)findViewById(R.id.buttondivide);
            zero=(Button)findViewById(R.id.button0);
            one=(Button)findViewById(R.id.button1);
            two=(Button)findViewById(R.id.button2);
            three=(Button)findViewById(R.id.button3);
            four=(Button)findViewById(R.id.button4);
            five=(Button)findViewById(R.id.button5);
            six=(Button)findViewById(R.id.button6);
            seven=(Button)findViewById(R.id.button7);
            eight=(Button)findViewById(R.id.button8);
            nine=(Button)findViewById(R.id.button9);
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }
}
