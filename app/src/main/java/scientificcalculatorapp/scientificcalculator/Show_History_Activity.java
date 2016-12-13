package scientificcalculatorapp.scientificcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Show_History_Activity extends AppCompatActivity {
TextView HistoryOutput;
    HashMap<String, Double> hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__history_);
        Intent mIntent = getIntent();
        //shouldn't this be String now?
        hashMap = (HashMap<String, Double>) mIntent.getSerializableExtra("History_Store");
        //RESTful API giving correct results.
        HistoryOutput = (TextView) findViewById(R.id.Output_History);
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                HistoryOutput.append(pair.getKey() + " = "+ "\n" + pair.getValue()+"\n");
                // it.remove(); // avoids a ConcurrentModificationException
            }
    }
    public void Send_Clear_History(View view) {
        try {
            hashMap.clear();
            HistoryOutput.setText("");
            Intent intent = new Intent(this, ScientificCalculator.class);
            intent.putExtra("hashmap", hashMap);
            startActivity(intent);
        }
        catch (Exception ex){

        }
    }
}
