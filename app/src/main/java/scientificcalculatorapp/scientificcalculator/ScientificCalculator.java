//http://stackoverflow.com/questions/7170233/java-int-to-int
//TEST CASES: Only minues can be first element of input. Cant do a query like *7-2 etc.
package scientificcalculatorapp.scientificcalculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
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
        findViewById(R.id.buttonequal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualButtonClick();
            }
        });
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
    void onEqualButtonClick()
    {
        String expressiontoevaluate=output.getText().toString();
        Expression expression = new ExpressionBuilder(expressiontoevaluate).build();
        double result=expression.evaluate();
        output.setText(Double.toString(result));
    }
}
