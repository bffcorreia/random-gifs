package io.github.bffcorreia.randomgifs;

import java.util.List;
import java.util.Random;

public class GetRandomGif {

  private final GifRepository gifRepository;
  private int lastRandomNumber = -1;

  public GetRandomGif(GifRepository gifRepository) {
    this.gifRepository = gifRepository;
  }

  public Gif run() {
    List<Gif> gifs = gifRepository.getAll();
    int randomGif = getRandomNumberNotRepeated(gifs.size());
    return gifs.get(randomGif);
  }

  private int getRandomNumberNotRepeated(int max) {
    Random random = new Random();
    int randomNumber = random.nextInt(max);
    while (randomNumber == lastRandomNumber) randomNumber = random.nextInt(max);
    lastRandomNumber = randomNumber;
    return randomNumber;
  }
}
