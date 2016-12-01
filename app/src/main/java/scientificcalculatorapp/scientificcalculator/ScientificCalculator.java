//http://stackoverflow.com/questions/7170233/java-int-to-int
//TEST CASES: Only minues can be first element of input. Cant do a query like *7-2 etc.
package scientificcalculatorapp.scientificcalculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScientificCalculator extends AppCompatActivity {
    Button Add, Subtract, Multiply, Divide, zero, one, two, three, four, five, six, seven, eight, nine, C, equal, DecimalPoint;
    int[] numberbuttons={R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
    int[] operatorbuttons={R.id.buttonplus,R.id.buttonminus,R.id.buttonmultiply,R.id.buttondivide};
    EditText input1, input2;
    boolean clicked = false;
    List<Integer> numbers = new ArrayList();
    List<Integer> number2 = new ArrayList();
    List<Integer> decimalplaces = new ArrayList<>();
    boolean buttonoperatorpressed = false;
    boolean decimalbuttonpressed = false;
    char op;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calculator);
        InitialiseViews();
        getinput();
        getoperator();
    }

    /*private void getoperator() {
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                buttonoperatorpressed = true;
                op = '+';
                onEqualButtonClick();
            }
        });

        Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                buttonoperatorpressed = true;
                op = '-';
                onEqualButtonClick();
            }
        });

        Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                buttonoperatorpressed = true;
                op = '*';
                onEqualButtonClick();
            }
        });

        Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                buttonoperatorpressed = true;
                op = '/';
                onEqualButtonClick();
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                numbers.clear();
                buttonoperatorpressed = false;
                decimalbuttonpressed = false;
                output.setText("0");
                numbers.clear();
                number2.clear();
                decimalplaces.clear();
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                try {
                    if (numbers != null && number2 != null) {
                        clicked = true;
                        output.setText(Float.toString(ArithmeticLogic(op)));
                        float temp = ArithmeticLogic(op);
                        numbers.clear();
                        number2.clear();
                        numbers.add((int) temp);
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        });
        DecimalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                clicked = true;
                decimalbuttonpressed = true;
            }
        });

    }*/
    private void getoperator(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                output.append(button.getText());
            }
        };
        for (int id : operatorbuttons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void getinput() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                output.append(button.getText());
            }
        };
        for (int id : numberbuttons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    void InitialiseViews() {
        try {
            output = (TextView) findViewById(R.id.Output);
        } catch (Exception ex) {
            throw ex;
        }

    }

    void GetFirstandSecondNumbers() {
        //firstnumber=ConvertIntArrayToInt(first);
        //secondnumber=ConvertIntArrayToInt(second);
    }

    int ConvertIntArrayToInt(int[] inputArray) {
        StringBuilder str = new StringBuilder();
        for (int num : inputArray) {
            str.append(num);
        }
        int finalint = Integer.parseInt(str.toString());
        return finalint;
    }

    float ArithmeticLogic(char op) {
        float one=0f;
        float two=0f;
        float decimal=0f;
        if(decimalplaces.size()>0)
        {
            int[] dec=new int[decimalplaces.size()];
            for (int i = 0; i < decimalplaces.size(); i++)
                dec[i] = decimalplaces.get(i);
            decimal = ConvertIntArrayToInt(dec);
        }
        if(numbers.size()>0) {
            int[] num1 = new int[numbers.size()];
            for (int i = 0; i < numbers.size(); i++)
                num1[i] = numbers.get(i);
            one = ConvertIntArrayToInt(num1);
            if(decimalplaces.size()>0)
            {
                one+=(decimal*Math.pow(decimalplaces.size(),10));
            }
        }
        if(number2.size()>0) {
            int[] num2 = new int[number2.size()];
            for (int i = 0; i < number2.size(); i++)
                num2[i] = number2.get(i);
             two = ConvertIntArrayToInt(num2);
            if(buttonoperatorpressed){
                two+=(decimal*Math.pow(decimalplaces.size(),10));
            }
        }
        if (op == '+') {
            float finalres = one + two;
            return finalres;
        } else if (op == '-') {
            float finalres = one - two;
            return finalres;
        } else if (op == '*') {
            if(two==0f)
            {
                float finalres=one*1;
                return  finalres;
            }
            float finalres = one * two;
            return finalres;
        } else if (op == '/') {
            if(two==0f)
            {
                float finalres=one/1;
                return  finalres;
            }
            float finalres = one / two;
            return finalres;
        }
        else return 0;
    }
    void onEqualButtonClick()
    {
        output.setText(Float.toString(ArithmeticLogic(op)));
        float temp=ArithmeticLogic(op);
        numbers.clear();number2.clear();decimalplaces.clear();
        numbers.add((int)temp);
    }
}
