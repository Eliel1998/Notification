package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GameEntity> games;
    ArrayList<Double> mediaPreco;
    String name, genre, plataform;
    Double price;
    EditText nameText, genreText, plataformText, priceText;
    private static final String CANAL_ID = "1";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.editTextName);
        genreText = findViewById(R.id.editTextGenre);
        plataformText = findViewById(R.id.editTextPlataform);
        priceText = findViewById(R.id.editTextPrice);
        games = new ArrayList<>();
        criarCanal();
    }

    public void addGameList(View view) {
        name = nameText.getText().toString();
        genre = genreText.getText().toString();
        plataform = plataformText.getText().toString();
        price = Double.parseDouble(priceText.getText().toString());
        if (games.size() < 3) {
            games.add(new GameEntity(name, price, genre, plataform));
            Toast.makeText(this, "Jogo adicionado com sucesso", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Não é possivel adicionar mais jogos a lista", Toast.LENGTH_SHORT).show();
            criarNotificação();
            calculaMedia();
        }
        System.out.println("QUANTIDADE DE GAMES: " + games.size());
    }

    void calculaMedia() {
        Double soma = 0.0;
        for (GameEntity game : games) {
            soma += game.getPrice();
        }
        mediaPreco.add(soma / games.size());
        Toast.makeText(this, "Média de preço dos jogos: " + mediaPreco.get(mediaPreco.size() - 1), Toast.LENGTH_SHORT).show();
    }

    private void criarCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Informação";
            String description = "Voce não pode adicionar mais de 3 jogos";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CANAL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void criarNotificação() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Informação")
                .setContentText("Voce não pode adicionar mais de 3 jogos")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}