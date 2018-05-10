package io.github.bffcorreia.randomgifs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private ImageView gifView;
  private Button surpriseMeView;
  private static int lastRandomNumber = -1;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    gifView = findViewById(R.id.gif);
    surpriseMeView = findViewById(R.id.surprise_me);

    surpriseMeView.setOnClickListener(__ -> setRandomGif());

    setRandomGif();
  }

  private Gif getRandomGif() {
    GifRepository gifRepository = new GifRepository();
    List<Gif> gifs = gifRepository.getAll();
    int randomGif = getRandomNumberNotRepeated(gifs.size());
    return gifs.get(randomGif);
  }

  private void setRandomGif() {
    Glide.with(this).asGif().load(getRandomGif().getUrl()).into(gifView);
  }

  private int getRandomNumberNotRepeated(int max) {
    Random random = new Random();
    int randomNumber = random.nextInt(max);
    while (randomNumber == lastRandomNumber) randomNumber = random.nextInt(max);
    lastRandomNumber = randomNumber;
    return randomNumber;
  }
}
