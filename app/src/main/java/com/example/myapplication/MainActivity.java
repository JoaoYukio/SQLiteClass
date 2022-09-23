package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( nome VARCHAR, idade INT(3))");

            //inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Joao', 24)");
            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Luisa', 21)");

            //recuperar os dados
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            // estilo lista linkada
            cursor.moveToFirst();
            while(cursor != null)
            {
                Log.i("Resultado - nome: ", cursor.getString(indiceNome));
                Log.i("Resultado - idade: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}