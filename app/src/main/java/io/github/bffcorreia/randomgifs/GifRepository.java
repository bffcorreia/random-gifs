package io.github.bffcorreia.randomgifs;

import java.util.ArrayList;
import java.util.List;

public class GifRepository {

  public List<Gif> getAll() {
    List<Gif> gifs = new ArrayList<>();

    gifs.add(new Gif("It's so fluffy!", "https://media.giphy.com/media/ZXERPeVnfhC2A/giphy.gif"));
    gifs.add(new Gif("Boss Penguin...", "https://media.giphy.com/media/11StaZ9Lj74oCY/giphy.gif"));
    gifs.add(new Gif("I'm a Panda :D", "https://media.giphy.com/media/uSYQsJQWEv6O4/giphy.gif"));
    gifs.add(new Gif("Baby fail xD", "https://media.giphy.com/media/Jk4ZT6R0OEUoM/giphy.gif"));
    gifs.add(new Gif("Watch out!", "https://media.giphy.com/media/13Ev2RtSAxKsTu/giphy.gif"));
    gifs.add(new Gif("Monkey Monkey Monkey!", "https://media.giphy.com/media/xTiTnJ3BooiDs8dL7W/giphy.gif"));
    gifs.add(new Gif("Hello Android", "https://media1.tenor.com/images/e9a2b43613bdde8dea94e81c4ca7e4c2/tenor.gif"));
    gifs.add(new Gif("Exciting news...", "https://m.popkey.co/9b7141/QbAV_f-maxage-0.gif"));
    gifs.add(new Gif("Cat!", "https://media.giphy.com/media/3oriO0OEd9QIDdllqo/giphy.gif"));
    gifs.add(new Gif("Popcorn.", "https://media.giphy.com/media/guufsF0Az3Lpu/giphy.gif"));

    return gifs;
  }
}
