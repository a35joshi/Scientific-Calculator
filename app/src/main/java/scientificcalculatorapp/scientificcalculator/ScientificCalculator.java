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
    int[] numberbuttons={R.id.buttonSIN,R.id.buttonCOS,R.id.buttonTAN,R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button_OPEN_BRACKET,R.id.button_CLOSE_BRACKET,R.id.button_COMMA};
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
        findViewById(R.id.buttonANS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualButtonClick();
            }
        });
        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                try {
                    output.setText("");
                }
                catch (Exception ex) {
                }
            }
        });
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                try{
                buttonoperatorpressed = true;
                output.append(".");
                }
                catch(Exception ex){
                }
            }
        });
        findViewById(R.id.buttonDEL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //click detected!
                    //DELETE THE LAST NUMBER/OPERATOR INPUTTED(except SYNTAX ERROR)
                    String str = output.getText().toString();
                    System.out.println(str);
                    buttonoperatorpressed=false;
                    if(!str.equals("SYNTAX ERROR")) {
                        str = str.substring(0, str.length() - 1);
                        // Now set this Text to your edit text
                        output.setText(str);
                    }
                }
                catch(Exception ex){

                }
            }
        });
    }

    private void getoperator(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.buttonDEL).setClickable(true);
                Button button = (Button) v;
                output.append(button.getText());
                buttonoperatorpressed=true;
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
                findViewById(R.id.buttonDEL).setClickable(true);
                Button button = (Button) v;
                if(button.getText().equals("sin")||button.getText().equals("cos")||button.getText().equals("tan")){
                    output.append(button.getText()+"(");
                }
                else {
                    output.append(button.getText());
                }
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
                findViewById(R.id.buttonDEL).setClickable(false);
            }
        }
        catch(Exception ex){
            output.setText("SYNTAX ERROR");
        }
    }
}
