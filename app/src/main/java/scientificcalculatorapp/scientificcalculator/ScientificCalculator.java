//WELCOME TO SCIENTIFIC CALCULATOR SOURCE CODE
package scientificcalculatorapp.scientificcalculator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.tokenizer.Tokenizer;

import java.util.HashMap;


public class ScientificCalculator extends AppCompatActivity {
    int[] numberbuttons = {R.id.button_PI,R.id.button_FACTORIAL, R.id.button_INVERSE, R.id.button_NEGATIVE, R.id.button_LOG_e, R.id.button_LOG_10, R.id.button_LOG_2, R.id.button_CARROT_MARK, R.id.button_SQR, R.id.button_SQR_ROOT, R.id.buttonSIN, R.id.buttonCOS, R.id.buttonTAN, R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button_OPEN_BRACKET, R.id.button_CLOSE_BRACKET, R.id.button_COMMA};
    int[] operatorbuttons = {R.id.buttonplus, R.id.buttonminus, R.id.buttonmultiply, R.id.buttondivide};
    boolean buttonoperatorpressed = false;
    boolean trigopressed = false;
    boolean factorialpress = false;
    private ClipboardManager clipBoard;
    private boolean addedToClipboard = false;
    EditText output;
    HashMap<String, String> History_Store;

    /*
     @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Copy") {
            String stringYouExtracted = output.getText().toString();
            if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(stringYouExtracted);
                Toast.makeText(getApplicationContext(), "Text Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
            else {
                //ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                //clipboard.setText(stringYouExtracted); }
                if (stringYouExtracted.equals("")) {
                    Toast.makeText(getApplicationContext(), "Nothing to Copy", Toast.LENGTH_SHORT).show();
                } else {
                    ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", stringYouExtracted);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Text Copied to Clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if (item.getTitle() == "Paste") {
            String pasteText;
            // TODO Auto-generated method stub
            if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB){
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                pasteText = clipboard.getText().toString();
                output.append(pasteText);

            }else{
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if(clipboard.hasPrimaryClip()== true){
                    ClipData.Item item1 = clipboard.getPrimaryClip().getItemAt(0);
                    pasteText = item1.getText().toString();
                    output.append(pasteText);

                }else{
                    Toast.makeText(getApplicationContext(), "Nothing to Paste", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //user has long pressed your TextView
        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0,v.getId(),0,"Paste");
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calculator);
        InitialiseViews();
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getinput();
        getoperator();
        clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipBoard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                addedToClipboard = true;
            }
        });
        output.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (addedToClipboard == true) {
                    String yourCopiedString = output.getText().toString();
                    int length = yourCopiedString.length();
                    Spannable spannable = new SpannableString(yourCopiedString);//set color
                    spannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannable.setSpan(new RelativeSizeSpan(1.0f), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    addedToClipboard = false;
                    output.setText(spannable);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
        //HASH MAP TO STORE HISTORY
        History_Store = new HashMap<String, String>();
        // valuating
        output.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                return false;
            }
        });
        findViewById(R.id.button_PI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            output.append(Double.toString(Math.PI));
            }
        });
        findViewById(R.id.button_FACTORIAL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!output.getText().toString().equals("SYNTAX ERROR") || !output.getText().toString().equals("MATH ERROR")) {
                    factorialpress = true;
                    int start = Math.max(output.getSelectionStart(), 0);
                    int end = Math.max(output.getSelectionEnd(), 0);
                    output.getText().replace(Math.min(start, end), Math.max(start, end), "!", 0, 1);
                    output.setTextColor(Color.BLACK);
                }
            }
        });
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
                    trigopressed = false;
                    buttonoperatorpressed = false;
                    factorialpress = false;
                    output.setTextColor(Color.BLACK);
                    output.setText("");
                } catch (Exception ex) {
                }
            }
        });
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click detected!
                try {
                    if (!output.getText().toString().equals("SYNTAX ERROR") || !output.getText().toString().equals("MATH ERROR")) {
                        buttonoperatorpressed = true;
                        output.append(".");
                        output.setTextColor(Color.BLACK);
                    }
                } catch (Exception ex) {
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
                    buttonoperatorpressed = false;
                    int start = Math.max(output.getSelectionStart(), 0);
                    int end = Math.max(output.getSelectionEnd(), 0);
                    //if((start!=str.length()||end!=str.length()) && !((str.equals("SYNTAX ERROR") || str.equals("sin(") || str.equals("cos(") || str.equals("tan(")) || str.equals("sqrt(")||str.equals("log10(")||str.equals("log2(")||str.equals("log("))){
                    //   str = str.substring(0,start)+ str.substring(end+1);
                    //   output.setText(str);
                    //   output.setSelection(output.getText().length());
                    // }
                    // else {
                    //     if(!((str.equals("SYNTAX ERROR") || str.equals("sin(") || str.equals("cos(") || str.equals("tan(")) || str.equals("sqrt(")||str.equals("log10(")||str.equals("log2(")||str.equals("log(")))
                    //   str=str.substring(0,str.length()-1);
                    // output.setText(str);
                    // }
                    // }
                    if (str.equals("SYNTAX ERROR") || str.equals("MATH ERROR")) {
                        //do nothing
                    } else if (str.contains("sin(") || str.contains("cos(") || str.contains("tan(") || str.contains("sqrt(") || str.contains("log10(") || str.contains("log2(") || str.contains("log(")) {
                        int lengthView = output.getText().length();
                        String viewCalString = output.getText().toString();
                        if (lengthView > 0 && lengthView <= 1)
                            output.getText().delete(lengthView - 1, lengthView);
                        else {
                            if (lengthView > 1) {
                                String last4Char = viewCalString.substring(lengthView - 4, lengthView);

                                switch (last4Char) {
                                    case "sin(":
                                        output.getText().delete(lengthView - 4, lengthView);
                                        break;

                                    case "cos(":
                                        output.getText().delete(lengthView - 4, lengthView);
                                        break;
                                    case "tan(":
                                        output.getText().delete(lengthView - 4, lengthView);
                                        break;
                                    case "log(":
                                        output.getText().delete(lengthView - 4, lengthView);
                                        break;
                                    case "qrt(":
                                        output.getText().delete(lengthView - 5, lengthView);
                                        break;
                                    case "g10(":
                                        output.getText().delete(lengthView - 6, lengthView);
                                        break;
                                    case "og2(":
                                        output.getText().delete(lengthView - 5, lengthView);
                                        break;
                                    default:
                                        if (start == end) {
                                            String dialled_nos = output.getText().toString();
                                            int remove_index_position = output.getSelectionStart() - 1;
                                            StringBuilder dialled_nos_builder = new StringBuilder(dialled_nos);
                                            if (remove_index_position >= 0) {
                                                dialled_nos_builder.deleteCharAt(remove_index_position);
                                                output.setText(dialled_nos_builder.toString());
                                                output.setSelection(remove_index_position);
                                            }
                                        } else {
                                            int remove_index_position = output.getSelectionStart();
                                            String selectedStr = output.getText().toString().substring(start, end);    //getting the selected Text
                                            output.setText(output.getText().toString().replace(selectedStr, ""));    //replacing the selected text with empty String and setting it to EditText
                                            output.setSelection(remove_index_position);
                                        }
                                        break;
                                }
                            }
                        }
                    } else {

                        if (start == end) {
                            String dialled_nos = output.getText().toString();
                            int remove_index_position = output.getSelectionStart() - 1;
                            StringBuilder dialled_nos_builder = new StringBuilder(dialled_nos);
                            if (remove_index_position >= 0) {
                                dialled_nos_builder.deleteCharAt(remove_index_position);
                                output.setText(dialled_nos_builder.toString());
                                output.setSelection(remove_index_position);
                            }
                        } else {
                            int remove_index_position = output.getSelectionStart();
                            String selectedStr = output.getText().toString().substring(start, end);    //getting the selected Text
                            output.setText(output.getText().toString().replace(selectedStr, ""));    //replacing the selected text with empty String and setting it to EditText
                            output.setSelection(remove_index_position);
                        }
                    }
                    output.setTextColor(Color.BLACK);
                } catch (Exception ex) {

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (resultCode == Activity.RESULT_OK) {
           // History_Store = (HashMap<String, Double>) data.getSerializableExtra("hashMap");
            History_Store.clear();
    }

    public void ShowHistory(View view)
    {
        Intent intent = new Intent(this, Show_History_Activity.class);
        intent.putExtra("History_Store", History_Store);
        startActivity(intent);
    }
    private void getoperator(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.buttonDEL).setClickable(true);
                Button button = (Button) v;
                if (!output.getText().toString().equals("SYNTAX ERROR")&&!(output.getText().toString().equals("MATH ERROR"))) {
                    int start = Math.max(output.getSelectionStart(), 0);
                    int end = Math.max(output.getSelectionEnd(), 0);
                    output.getText().replace(Math.min(start, end), Math.max(start, end), button.getText(), 0, button.getText().length());
                    output.setTextColor(Color.BLACK);
                    buttonoperatorpressed = true;
                }
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
                if (!(output.getText().toString().equals("SYNTAX ERROR"))&&!(output.getText().toString().equals("MATH ERROR"))) {
                    if (button.getText().equals("(-)")) {
                        int start = Math.max(output.getSelectionStart(), 0);
                        int end = Math.max(output.getSelectionEnd(), 0);
                        output.getText().replace(Math.min(start, end), Math.max(start, end), "-", 0, 1);
                        //output.append("-");
                    }
                    if (button.getText().equals("sin") || button.getText().equals("cos") || button.getText().equals("tan") || button.getText().equals("sqrt") || button.getText().equals("log10") || button.getText().equals("log") || button.getText().equals("log2")) {
                        int start = Math.max(output.getSelectionStart(), 0);
                        int end = Math.max(output.getSelectionEnd(), 0);
                        output.getText().replace(Math.min(start, end), Math.max(start, end), button.getText() + "(", 0, button.getText().length() + 1);
                        //output.append(button.getText()+"(");
                        trigopressed = true;
                    } else {
                        if (!button.getText().equals("(-)")) {
                            int start = Math.max(output.getSelectionStart(), 0);
                            int end = Math.max(output.getSelectionEnd(), 0);
                            output.getText().replace(Math.min(start, end), Math.max(start, end), button.getText(), 0, button.getText().length());
                            //output.append(button.getText());
                        }
                    }
                    output.setTextColor(Color.BLACK);
                    buttonoperatorpressed = false;
                }
            }
        };
        for (int id : numberbuttons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    void InitialiseViews() {
        try {
            output = (EditText) findViewById(R.id.Output);
            output.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            registerForContextMenu(output);
            output.setTextColor(Color.BLACK);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

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
    Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

        @Override
        public double apply(double... args) {
            final int arg = (int) args[0];
            if ((double) arg != args[0]) {
                throw new IllegalArgumentException("Operand for factorial has to be an integer");
            }
            if (arg < 0) {
                throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
            }
            double result = 1;
            for (int i = 1; i <= arg; i++) {
                result *= i;
            }
            return result;
        }
    };
boolean verify(double result){
  if(Double.toString(result).equals("-Infinity")||Double.toString(result).equals("Infinity")||Double.toString(result).equals("NaN")){
      return true;
  }
    return false;
}
    void onEqualButtonClick()
    {
           try {
                String expressiontoevaluate = output.getText().toString();
            if(factorialpress){
                double result = new ExpressionBuilder(expressiontoevaluate).operator(factorial).build().evaluate();
                if(verify(result)){
                    output.setText("MATH ERROR");//SOMETHING WRONG IN INPUT
                }else {
                    if(Double.toString(result).contains("E")){
                        String result1=Double.toString(result).replace("E","*10^");
                        output.setText(result1);
                        History_Store.put(expressiontoevaluate,result1);
                    }
                    else {
                        output.setText(Double.toString(result));
                        History_Store.put(expressiontoevaluate,Double.toString(result));
                    }
                }
            }
              else  if(trigopressed){
                if(!expressiontoevaluate.contains(")")){
                    int counter = 0;
                    for( int i=0; i<expressiontoevaluate.length(); i++ ) {
                        if( expressiontoevaluate.charAt(i) == '(' ) {
                            counter++;
                        }
                    }
                    for( int i=0; i<counter; i++ ) {
                        expressiontoevaluate+=")";
                    }
                }
                    Expression expression = new ExpressionBuilder(expressiontoevaluate).functions(TrigFunctions).build();
                    double result = expression.evaluate();
                if(verify(result)){
                    output.setText("MATH ERROR");//SOMETHING WRONG IN INPUT
                }else {
                    if(Double.toString(result).contains("E")){
                      String result1=Double.toString(result).replace("E","*10^");
                        output.setText(result1);
                        History_Store.put(expressiontoevaluate,result1);
                    }
                    else {
                        output.setText(Double.toString(result));
                        History_Store.put(expressiontoevaluate,Double.toString(result));
                    }
                }
            }
                    else if(!trigopressed && !factorialpress) {
                            Expression expression = new ExpressionBuilder(expressiontoevaluate).build();
                            double result = expression.evaluate();
                            if(verify(result)){
                                output.setText("MATH ERROR");//SOMETHING WRONG IN INPUT
                            }else {
                                if(Double.toString(result).contains("E")){
                                    String result1=Double.toString(result).replace("E","*10^");
                                    output.setText(result1);
                                    History_Store.put(expressiontoevaluate,result1);
                                }
                                else {
                                    output.setText(Double.toString(result));
                                    History_Store.put(expressiontoevaluate,Double.toString(result));
                    }
                }
            }
                findViewById(R.id.buttonDEL).setClickable(false);
        }
        catch(Exception ex){
            //output.setText(ex.toString());
            output.setText("SYNTAX ERROR");
        }
        //http://stackoverflow.com/questions/6438478/sethinttextcolor-in-edittext
        output.setSelection(output.getText().length());
        if(output.getText().toString().equals("SYNTAX ERROR")||output.getText().toString().equals("MATH ERROR")){
            output.setTextColor(Color.RED);
        }
    }
}
