package com.example.tschausepp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonEinzelspieler, buttonMehrspieler, buttonBeenden;
    private ImageView ivStartBild;
    private ImageButton imageButtonHilfe;
    private final String preferenceFirststart = "firstAppStart";
    private final String databaseName = "database.db";
    private final String databaaseTabelTest = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Toast.makeText(getApplicationContext(), "Minimum API: " + Build.VERSION_CODES.N, Toast.LENGTH_SHORT).show();
            System.out.println("Minimum API: " + Build.VERSION_CODES.N);
            System.exit(0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBeenden = findViewById(R.id.buttonBeenden);
        buttonEinzelspieler = findViewById(R.id.buttonEinzelspieler);
        buttonMehrspieler = findViewById(R.id.buttonMehrspieler);
        imageButtonHilfe = findViewById(R.id.imageButtonHilfe);
        ivStartBild = findViewById(R.id.ivStartBild);

        int startBildID = getResources().getIdentifier("tschauseppbild", "drawable", getPackageName());
        ivStartBild.setImageResource(startBildID);
        //int hilfeBildID = getResources().getIdentifier("helpbild", "drawable", getPackageName());
        //imageButtonHilfe.setBackgroundResource(hilfeBildID);

        //Video letzte 11min


        buttonBeenden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            System.exit(0);
            }
        });
        buttonEinzelspieler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEinzelspieler();
            }
        });
        buttonMehrspieler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onClickMehrspieler();
            }
        });
        imageButtonHilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public boolean firstStart(){
        SharedPreferences preferences = getSharedPreferences(preferenceFirststart, MODE_PRIVATE);
        if(preferences.getBoolean(preferenceFirststart, true)){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(preferenceFirststart, false);
            return true;
        }else{
            return false;
        }
    }
    public void onClickMehrspieler(){
        Intent intent = new Intent(this, MultiplayerMenueActivity.class);
        startActivity(intent);
    }
    public void onClickEinzelspieler(){
    }

    public void createDatabase(){
        SQLiteDatabase database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE "+ databaaseTabelTest + "(testInteger INTEGER, testText TEXT)");
        database.execSQL("INSERT INTO " + databaaseTabelTest  + " VALUES('1', 'test1')");
        database.execSQL("INSERT INTO " + databaaseTabelTest  + " VALUES('2', 'test2')");
        database.execSQL("INSERT INTO " + databaaseTabelTest  + " VALUES('3', 'test3')");
        database.close();

        /**
        Cursor cursor = database.rawQuery("SELECT * FROM " + databaaseTabelTest + " WHERE testInteger = '1'", null);
        cursor.moveToFirst();
        String text;
         if(cursor.getCount() == 1){
             text = cursor.getString(1);
             cursor.close();
             database.close();
         }
         4 14:05

         Unten auf dem Bildschirm Nachricht ausgeben als "kurze Anzeige" 5 10:20

         Toast.makeText(getApplicationContext(), "Text", Toast.LENGTH_SHORT).show();

         Animationen letztes 1:00

         LottieFiles
         */


    }
}
