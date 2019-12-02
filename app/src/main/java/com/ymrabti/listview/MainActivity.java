package com.ymrabti.listview;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;import android.widget.Toast;
import org.w3c.dom.Document;import org.w3c.dom.Element;import org.w3c.dom.NodeList;import java.io.InputStream;import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity{
    ArrayList<String> elements ;
    ArrayList<String> color ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.list);
        elements = new ArrayList<>();color = new ArrayList<>();
        try {
            InputStream in = getResources().openRawResource(R.raw.valeurs);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(in,null);
            NodeList valeurs = document.getElementsByTagName("color");
            for(int i=0;i<valeurs.getLength();i++){
                elements.add(((Element) valeurs.item(i)).getAttribute("name"));
                color.add(((Element) valeurs.item(i)).getAttribute("value"));
            }
            in.close();
        }
        catch (Throwable throwable){
            Toast.makeText(this,"exception ...",Toast.LENGTH_LONG).show();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, elements);
        list.setAdapter(adapter);
        final Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);startActivity(i);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedColorItem = color.get(position);
                b.setText(selectedColorItem);
                /*
                String selectedItem = (String) parent.getItemAtPosition(position);Intent launchApp = getPackageManager().getLaunchIntentForPackage(selectedItem);
                Toast.makeText(view.getContext(),"Ouverture de L'application ...",Toast.LENGTH_SHORT).show();
                startActivity(launchApp);*/
            }
        });
    }
}