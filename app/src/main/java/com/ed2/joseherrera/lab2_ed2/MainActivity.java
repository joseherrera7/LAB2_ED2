package com.ed2.joseherrera.lab2_ed2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ed2.joseherrera.lab2_ed2.codifications.zigzag;

public class MainActivity extends AppCompatActivity {

    zigzag newZigZag = new zigzag();
    private TextView mTextMessage;
    private TextView mTextMessage2;
    private Button mButton;
    private int id;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_zigzag:
                    mTextMessage.setText(R.string.title_home);
                    mTextMessage2.setText("Ingrese sus niveles");
                    return true;
                case R.id.navigation_sdes:
                    mTextMessage.setText(R.string.title_dashboard);
                    mTextMessage2.setText("Ingrese su clave");
                    return true;
                case R.id.navigation_rsa:
                    mTextMessage.setText(R.string.title_notifications);
                    mTextMessage2.setText("Ingrese su clave");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage2 = (TextView) findViewById(R.id.message2);
        mButton = (Button) findViewById(R.id.btnCifrar);
        mTextMessage.setText(R.string.title_home);
        mTextMessage2.setText("Ingrese sus niveles");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id) {
                    case R.id.navigation_zigzag:
                        newZigZag.codezigzag(String.valueOf(mTextMessage.getText()), Integer.valueOf(String.valueOf(mTextMessage2.getText())));
                    case R.id.navigation_sdes:
                        mTextMessage.setText(R.string.title_dashboard);
                        mTextMessage2.setText("Ingrese su clave");

                    case R.id.navigation_rsa:
                        mTextMessage.setText(R.string.title_notifications);
                        mTextMessage2.setText("Ingrese su clave");

                }
            }
        });

    }

}
