//http://stackoverflow.com/questions/7170233/java-int-to-int

package scientificcalculatorapp.scientificcalculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScientificCalculator extends AppCompatActivity {
    Button Add,Subtract,Multiply,Divide,zero,one,two,three,four,five,six,seven,eight,nine,C,equal;
    boolean clicked=false;
    List numbers=new ArrayList();
    List number2=new ArrayList();
    boolean buttonoperatorpressed=false;
    int firstnumber=1;
    int secondnumber=1;
    int finalresult;
    TextView output;
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
                buttonoperatorpressed=true;

            }
        });

        Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                buttonoperatorpressed=true;
             }
        });

        Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                buttonoperatorpressed=true;
                 }
        });

        Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                buttonoperatorpressed=true;
                 }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(1);
                }
                else
                {
                    numbers.add(1);
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(2);
                }
                else
                {

                    numbers.add(2);
                }
            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {   number2.add(3);

                }
                else
                {

                    numbers.add(3);
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(4);

                }
                else
                {

                    numbers.add(4);
                }
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(5);

                }
                else
                {

                    numbers.add(5);
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(6);

                }
                else
                {

                    numbers.add(6);
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(7);

                }
                else
                {

                    numbers.add(7);
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(8);

                }
                else
                {

                    numbers.add(8);
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                if(buttonoperatorpressed)
                {
                    number2.add(9);

                }
                else
                {

                    numbers.add(9);
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked=true;
                numbers.clear();
                buttonoperatorpressed=false;
                firstnumber=0;
                secondnumber=0;
                finalresult=0;
                output.setText("0");
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                try {
                    clicked = true;
                    output.setText(Integer.toString(finalresult));
                }
                catch (Exception ex)
                {
                    throw  ex;
                }
                }
        });
    }
    void InitialiseViews()
    {
        try{
            C=(Button)findViewById(R.id.buttonC);
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
            equal=(Button)findViewById(R.id.buttonequal);
            output=(TextView)findViewById(R.id.Output);
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }
    void GetFirstandSecondNumbers(){
        //firstnumber=ConvertIntArrayToInt(first);
        //secondnumber=ConvertIntArrayToInt(second);
    }
    int ConvertIntArrayToInt(int[] inputArray){
        StringBuilder str=new StringBuilder();
        for(int num:inputArray)
        {
            str.append(num);
        }
        int finalint=Integer.parseInt(str.toString());
        return finalint;
    }
}
