package scientificcalculatorapp.scientificcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Show_History_Activity extends AppCompatActivity {
TextView HistoryOutput;
    //ArrayList<String> hashMap1=new ArrayList<String>();
    HashMap<String,String> hashMap=new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__history_);
        Intent mIntent = getIntent();
        //shouldn't this be String now?
        hashMap = (HashMap<String, String>) mIntent.getSerializableExtra("History_Store");
        //hashMap1 = (ArrayList<String>) getIntent().getSerializableExtra("History_Store_New");
        //RESTful API giving correct results.
        HistoryOutput = (TextView) findViewById(R.id.Output_History);
        if(!hashMap.isEmpty()) {
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                HistoryOutput.append(pair.getKey() + " = " + "\n" + pair.getValue() + "\n");
                // it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
    public void Send_Clear_History(View view) {
        try {
            hashMap.clear();
            //hashMap1.clear();
            HistoryOutput.setText("");
            //fos = openFileOutput("Internal_History", Context.MODE_PRIVATE);
            //sos = new ObjectOutputStream(fos);
            //sos.writeObject(hashMap);
            Intent intent = new Intent(this, ScientificCalculator.class);
            ScientificCalculator.SaveArrayList(this.getApplicationContext(),"Internal_History.dat",hashMap);
            intent.putExtra("hashmap", hashMap);
            //intent.putExtra("hashmap1", hashMap1);
            startActivity(intent);
            finish();
        }
        catch (Exception ex){

        }
    }
}
