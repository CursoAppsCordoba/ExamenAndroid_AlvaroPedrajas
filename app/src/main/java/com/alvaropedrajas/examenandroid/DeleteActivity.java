package com.alvaropedrajas.examenandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_name, et_mail, et_phone;
    String nom, mail;
    Integer tel;

    boolean delFlag;

    private Contacto contacto = new Contacto();
    private ArrayList<Contacto> listaContactos = new ArrayList<>();
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnDel = (Button) findViewById(R.id.btnDel);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btnBack.setOnClickListener(this);
        btnDel.setOnClickListener(this);
    }

    public void getDatos(View v){

        if (!TextUtils.isEmpty(et_name.getText().toString())){
            nom = et_name.getText().toString();
        }else{
            et_name.setError("Debes introducir un nombre!");
            nom = null;
        }

        if (!TextUtils.isEmpty(et_mail.getText().toString())){
            mail = et_mail.getText().toString();
        }else{
            et_mail.setError("Debes introducir un email!");
            mail = null;
        }

        if (!TextUtils.isEmpty(et_phone.getText().toString())){
            tel = Integer.parseInt(et_phone.getText().toString());
        }else{
            et_phone.setError("Debes introducir un teléfono!");
            tel = null;
        }

        if (!Objects.equals(nom, null) && !Objects.equals(mail, null) && !Objects.equals(tel, null)){
            delFlag = true;
        }else{
            delFlag = false;
            Toast.makeText(this, "¡Debes rellenar todos los campos!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public Contacto regDatos(String nom, String mail, Integer tel){
        contacto.setNombre(nom);
        contacto.setMail(mail);
        contacto.setTelefono(tel);

        return contacto;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnDel:
                getDatos(v);
                regDatos(nom, mail, tel);
                if (delFlag){
                    listaContactos = Utils.readFile(activity);
                    listaContactos.remove(contacto);
                    Utils.escribirFichero(activity, listaContactos);
                    Toast.makeText(this, contacto.getNombre() + " ha sido borrado", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
