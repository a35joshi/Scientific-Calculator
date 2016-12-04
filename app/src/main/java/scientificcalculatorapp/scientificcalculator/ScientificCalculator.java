//http://stackoverflow.com/questions/7170233/java-int-to-int
// √
package scientificcalculatorapp.scientificcalculator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;


public class ScientificCalculator extends AppCompatActivity {
    int[] numberbuttons={R.id.button_SQR,R.id.button_SQR_ROOT,R.id.buttonSIN,R.id.buttonCOS,R.id.buttonTAN,R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button_OPEN_BRACKET,R.id.button_CLOSE_BRACKET,R.id.button_COMMA};
    int[] operatorbuttons={R.id.buttonplus,R.id.buttonminus,R.id.buttonmultiply,R.id.buttondivide};
    boolean buttonoperatorpressed = false;
    boolean trigopressed=false;
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
                    trigopressed=false;
                    buttonoperatorpressed = false;
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
                    if(!((str.equals("SYNTAX ERROR") || str.equals("sin(") || str.equals("cos(") || str.equals("tan(")) || str.equals("sqrt("))){
                        str = str.substring(0, str.length() - 1);
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
                if(button.getText().equals("sin")||button.getText().equals("cos")||button.getText().equals("tan")||button.getText().equals("sqrt")){
                    output.append(button.getText()+"(");
                    trigopressed=true;
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
            output.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        } catch (Exception ex) {
            throw ex;
        }

    }
    private Function[] TrigFunctions = {
            new Function("sin"){public double apply(double... args){return Math.sin(Math.toRadians(args[0]));}},
            new Function("cos"){public double apply(double... args){return Math.cos(Math.toRadians(args[0]));}},
            new Function("tan"){public double apply(double... args){return Math.tan(Math.toRadians(args[0]));}}
    };
    private Function[] SquareFunctions = {
            new Function("square"){public double apply(double... args){return (args[0]*args[0]);}}
    };
    void onEqualButtonClick()
    {
        try {
            if (!buttonoperatorpressed) {
                String expressiontoevaluate = output.getText().toString();
                if(trigopressed){
                    Expression expression = new ExpressionBuilder(expressiontoevaluate).functions(TrigFunctions).build();
                    double result = expression.evaluate();
                    output.setText(Double.toString(result));
                }
                else {
                    Expression expression = new ExpressionBuilder(expressiontoevaluate).build();
                    double result = expression.evaluate();
                    output.setText(Double.toString(result));
                }
                findViewById(R.id.buttonDEL).setClickable(false);
            }
            else{
                output.setText("SYNTAX ERROR");
            }
        }
        catch(Exception ex){
            output.setText("SYNTAX ERROR");
        }
    }
}
