//http://stackoverflow.com/questions/7170233/java-int-to-int
//TEST CASES: Only minues can be first element of input. Cant do a query like *7-2 etc.
package scientificcalculatorapp.scientificcalculator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ScientificCalculator extends AppCompatActivity {
    int[] numberbuttons={R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
    int[] operatorbuttons={R.id.buttonplus,R.id.buttonminus,R.id.buttonmultiply,R.id.buttondivide};
    boolean buttonoperatorpressed = false;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calculator);
        InitialiseViews();
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getinput();
        getoperator();
        findViewById(R.id.buttonequal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualButtonClick();
            }
        });
        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                output.setText("");
            }
        });
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                buttonoperatorpressed = true;
                output.append(".");
            }
        });
    }

    private void getoperator(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                buttonoperatorpressed=true;
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
                buttonoperatorpressed=false;
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
        try {
            if (!buttonoperatorpressed) {
                String expressiontoevaluate = output.getText().toString();
                Expression expression = new ExpressionBuilder(expressiontoevaluate).build();
                double result = expression.evaluate();
                output.setText(Double.toString(result));
            }
        }
        catch(Exception ex){
            output.setText("SYNTAX ERROR");
        }
    }
}
