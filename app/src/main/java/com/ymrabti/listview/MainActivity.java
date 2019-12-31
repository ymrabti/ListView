package com.ymrabti.listview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ListView malist;
    Dialog myDialog;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> installedPackages = getInstalledAppsPackageNameList();

        myDialog = new Dialog(this);
        malist = findViewById(R.id.list);
        String[] T = installedPackages.toArray(new String[1]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,T);
        malist.setAdapter(adapter);
        malist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), selectedItem+" !",Toast.LENGTH_SHORT).show();
            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        textview = findViewById(R.id.text);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override public void onAdLoaded() {textview.setText(R.string.loaded); }
            @Override public void onAdFailedToLoad(int errorCode) {textview.setText(R.string.failed+errorCode); }
            @Override public void onAdOpened() {textview.setText(R.string.opened);  }
            @Override public void onAdClicked() {textview.setText(R.string.clicked);  }
            @Override public void onAdLeftApplication() {textview.setText(R.string.leftapp);  }
            @Override public void onAdClosed() { textview.setText(R.string.closed); }
        });
    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    protected List<String> getInstalledAppsPackageNameList(){
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent,0);
        List<String> packageNameList = new ArrayList<>();
        for(ResolveInfo resolveInfo: resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            packageNameList.add(activityInfo.applicationInfo.packageName);
        }
        return packageNameList;
    }
}
/*Button abutton = findViewById(R.id.button);
        abutton.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Activity 2 !",Toast.LENGTH_LONG).show();

            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }

        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

