package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GameEntity> games;
    String name, genre, plataform;
    Double price;
    EditText nameText, genreText, plataformText, priceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.editTextName);
        genreText = findViewById(R.id.editTextGenre);
        plataformText = findViewById(R.id.editTextPlataform);
        priceText = findViewById(R.id.editTextPrice);
        games = new ArrayList<>();
    }

    public void addGameList(View view) {
        name = nameText.getText().toString();
        genre = genreText.getText().toString();
        plataform = plataformText.getText().toString();
        price = Double.parseDouble(priceText.getText().toString());
        games.add(new GameEntity(name, price, genre, plataform));
        if(games.size() > 3){
            Toast.makeText(this, "Não é possivel adicionar mais jogos a lista", Toast.LENGTH_SHORT).show();
        }
        System.out.println("QUANTIDADE DE GAMES: "+games.size());
    }
}