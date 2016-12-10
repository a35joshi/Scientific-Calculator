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
        hashMap = (HashMap<String, Double>) mIntent.getSerializableExtra("History_Store");
        //RESTful API giving correct results.
        HistoryOutput = (TextView) findViewById(R.id.Output_History);
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                HistoryOutput.append(pair.getKey() + " = "+ "\n" + pair.getValue()+"\n");
                // it.remove(); // avoids a ConcurrentModificationException
            }
        findViewById(R.id.button_CLEAR_HISTORY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
            try{
                hashMap.clear();
                HistoryOutput.setText("");

                Intent resultIntent = new Intent();
                // TODO Add extras or a data URI to this intent as appropriate.
                resultIntent.putExtra("hashMap",hashMap);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
            catch (Exception ex){

            }

            }
        });
    }
}
