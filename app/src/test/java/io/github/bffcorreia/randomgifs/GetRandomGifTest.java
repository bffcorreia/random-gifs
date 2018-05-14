package io.github.bffcorreia.randomgifs;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetRandomGifTest {

  @InjectMocks private GetRandomGif getRandomGif;
  @Mock private GifRepository gifRepository;
  private final Faker faker = new Faker();

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test public void givenVariousGifs_whenRun_thenGetRandomGifFromList() {
    List<Gif> gifs = generateGifsList(5);
    when(gifRepository.getAll()).thenReturn(gifs);
    Gif gif = getRandomGif.run();
    assertThat(gifs, hasItem(gif));
  }

  @Test public void givenVariousGifs_whenRun_thenGetAllIsCalled() {
    List<Gif> gifs = generateGifsList(5);
    when(gifRepository.getAll()).thenReturn(gifs);
    getRandomGif.run();
    verify(gifRepository).getAll();
  }

  @Test public void givenTwoGifs_whenRunMultipleTimes_thenAlwaysGetTheOtherGif() {
    List<Gif> gifs = generateGifsList(2);
    when(gifRepository.getAll()).thenReturn(gifs);

    Gif firstGif = getRandomGif.run();
    Gif secondGif = getRandomGif.run();
    Gif thirdGif = getRandomGif.run();
    Gif fourthGif = getRandomGif.run();

    assertThat(firstGif, is(not(secondGif)));
    assertThat(secondGif, is(not(thirdGif)));
    assertThat(thirdGif, is(not(fourthGif)));

    assertThat(firstGif, is(thirdGif));
    assertThat(secondGif, is(fourthGif));
  }

  private List<Gif> generateGifsList(int size) {
    List<Gif> gifs = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      gifs.add(new Gif(faker.rickAndMorty().character(), faker.internet().url()));
    }
    return gifs;
  }
}
