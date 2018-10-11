package com.ed2.joseherrera.lab2_ed2;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.nio.charset.*;

import com.ed2.joseherrera.lab2_ed2.codifications.zigzag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_storage = 1;
    private static Charset UTF8=Charset.forName("UTF-8");
    zigzag newZigZag = new zigzag();
    private TextView mTextMessage, mTextMessage2;
    private EditText text, key;
    private Button buttonToCode, buttonToDecode, buttonSearchArchive;
    String entry;
    private int id;
    Uri originalPath;
    String route;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_zigzag:
                    mTextMessage.setText(R.string.title_home);
                    mTextMessage2.setText("Ingrese sus niveles");
                    id = item.getItemId();
                    return true;
                case R.id.navigation_sdes:
                    mTextMessage.setText(R.string.title_dashboard);
                    mTextMessage2.setText("Ingrese su clave");
                    id = item.getItemId();
                    return true;
                case R.id.navigation_rsa:
                    mTextMessage.setText(R.string.title_notifications);
                    mTextMessage2.setText("Ingrese su clave");
                    id = item.getItemId();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSearchArchive = (Button)findViewById(R.id.btnLoadFile);
        buttonSearchArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);

                startActivityForResult(Intent.createChooser(intento, "Seleccione un archivo"), 123);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

        } else {


            explainUsage();
            grandAccessStorage();
        }
        text = (EditText) findViewById(R.id.textoACifrar);
        key = (EditText) findViewById(R.id.nivelesACifrar);
        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage2 = (TextView) findViewById(R.id.message2);
        buttonToCode = (Button) findViewById(R.id.btnCifrar);
        buttonToDecode = (Button) findViewById(R.id.btnDescifrar);
        mTextMessage.setText(R.string.title_home);
        mTextMessage2.setText("Ingrese sus niveles");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        buttonToCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id) {
                    case R.id.navigation_zigzag:
                        try {
                            

                            if (Integer.valueOf(String.valueOf(key.getText())) > 2) {
                                CreateFile(newZigZag.codezigzag(String.valueOf(text.getText()), Integer.valueOf(String.valueOf(key.getText()))));

                            }
                            else{
                                Toast.makeText(context, "Debe ser un numero mayor o igual a 2", Toast.LENGTH_LONG).show();
                            }
                          } catch (IOException e) {
                            e.printStackTrace();
                        }
                    break;
                    case R.id.navigation_sdes:
                        mTextMessage.setText(R.string.title_dashboard);
                        mTextMessage2.setText("Ingrese su clave");
                        break;

                    case R.id.navigation_rsa:
                        mTextMessage.setText(R.string.title_notifications);
                        mTextMessage2.setText("Ingrese su clave");
                        break;

                        default:
                            try {
                                if (Integer.valueOf(String.valueOf(key.getText())) > 2) {
                                    CreateFile(newZigZag.codezigzag(String.valueOf(text.getText()), Integer.valueOf(String.valueOf(key.getText()))));

                                }
                                else{
                                    Toast.makeText(context, "Debe ser un numero  mayor o igual a 2", Toast.LENGTH_LONG).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                }
            }
        });

        buttonToDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id) {
                    case R.id.navigation_zigzag:
                        try {
                            if (Integer.valueOf(String.valueOf(key.getText())) > 2) {
                                CreateFile2(newZigZag.decodezigzag(String.valueOf(text.getText()), Integer.valueOf(String.valueOf(key.getText()))));
                            } else{
                                Toast.makeText(context, "Debe ser un numero  mayor o igual a 2", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case R.id.navigation_sdes:
                        mTextMessage.setText(R.string.title_dashboard);
                        mTextMessage2.setText("Ingrese su clave");
                        break;
                    case R.id.navigation_rsa:
                        mTextMessage.setText(R.string.title_notifications);
                        mTextMessage2.setText("Ingrese su clave");
                        break;
                    default:
                        try {
                            if (Integer.valueOf(String.valueOf(key.getText())) > 2) {
                                CreateFile2(newZigZag.decodezigzag(String.valueOf(text.getText()), Integer.valueOf(String.valueOf(key.getText()))));

                            }
                            else{
                                Toast.makeText(context, "Debe ser un numero mayor a 2", Toast.LENGTH_LONG);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }
        });

    }
    private void explainUsage() {


        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Se necesita del almacenamiento para guardar su archivo comprimido", Toast.LENGTH_SHORT).show();

        }
    }


    private void grandAccessStorage() {


        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        ActivityCompat.requestPermissions(this,
                new String[]{permission.WRITE_EXTERNAL_STORAGE},
                SOLICITUD_PERMISO_storage);

        Toast.makeText(this, "Por favor concender permisos para almacenar en memoria", Toast.LENGTH_SHORT).show();


    }
    Uri archivo;
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK){
            archivo = data.getData();


            Toast.makeText(this, archivo.getPath(), Toast.LENGTH_LONG).show();
            try{
                entry=readTextFromUri(archivo);

                if(archivo.getPath().endsWith("txt")){
                    text.setText(entry);
                    route = archivo.getPath();
                    originalPath = archivo;
                }
                else if (archivo.getPath().endsWith("cif")){
                    text.setText(entry);
                    route = archivo.getPath();
                }
            }catch (IOException e){
                Toast.makeText(this, "Hubo un error al obtener el texto del archivo", Toast.LENGTH_LONG).show();
            }
        }
    }
//hola
    private String readTextFromUri(Uri uri) throws IOException {
        String salida="";

        InputStream inputStream = getContentResolver().openInputStream(uri);
        String cadena="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf8"),8192);
        while((cadena = reader.readLine())!=null) {
            salida=salida+cadena+"\n";
        }
        inputStream.close();
        reader.close();
        return salida;
    }
    private void CreateFile(String encoded_values) throws IOException {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;
        String fname;
        myDir = new File(root + "/misCifrados");
        fname = "code.cif";
        myDir.mkdirs();
        File file = new File(myDir, fname);
        if(file.exists()) {
            file.delete();
        }
        try
        {
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(stream,UTF8);
            writer.write(encoded_values);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Cifrado exitoso su archivo codificado se guardo en"+myDir.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }
    private void CreateFile2(String encoded_values) throws IOException {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;
        String fname;
        myDir = new File(root + "/misCifrados");
        fname = "decode.cif";
        myDir.mkdirs();
        File file = new File(myDir, fname);
        if(file.exists()) {
            file.delete();
        }
        try
        {
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(stream,UTF8);
            writer.write(encoded_values);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Descifrado exitos su archivo decodificado se guardo en"+myDir.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }
}
