package io.github.bffcorreia.randomgifs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

  private ImageView gifView;
  private Button surpriseMeView;
  private Gif currentGif;
  private GetRandomGif getRandomGif = new GetRandomGif(new GifRepository());

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    gifView = findViewById(R.id.gif);
    surpriseMeView = findViewById(R.id.surprise_me);

    gifView.setOnClickListener(__ -> navigateToShareActivity());
    surpriseMeView.setOnClickListener(__ -> setRandomGif());

    setRandomGif();
  }

  private Gif getRandomGif() {
    currentGif = getRandomGif.run();
    return currentGif;
  }

  private void setRandomGif() {
    Glide.with(this).asGif().load(getRandomGif().getUrl()).into(gifView);
  }

  private void navigateToShareActivity() {
    Intent intent = new Intent(this, ShareActivity.class);
    intent.putExtra("gif", currentGif);
    startActivity(intent);
  }
}
