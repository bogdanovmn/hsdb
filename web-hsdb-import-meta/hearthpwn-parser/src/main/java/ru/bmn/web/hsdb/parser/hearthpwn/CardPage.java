package ru.bmn.web.hsdb.parser.hearthpwn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.bmn.web.hsdb.model.entity.hs.*;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class CardPage {
    private static final UrlContentDiscCache CACHE = new UrlContentDiscCache(
        CardPage.class.toString()
    );

    private boolean isParsed = false;
    private Document htmlDocument;
    private final URL url;
    private String aboutText;
    private Artist artist;
    private boolean collectible;
    private String imageUrl;
    private String goldImageUrl;
    private Set<Mechanic> mechanics;
    private Rarity rarity;

    public CardPage(URL url) {
        this.url = url;
    }

    public void parse()
        throws IOException
    {
        if (!this.isParsed) {
            String html = CACHE.getText(this.url);
            this.htmlDocument = Jsoup.parse(html);
        }
    }

    public String getAboutText() {
        return aboutText;
    }

    public Artist getArtist() {
        return artist;
    }

    public boolean isCollectible() {
        return collectible;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGoldImageUrl() {
        return goldImageUrl;
    }

    public Set<Mechanic> getMechanics() {
        return mechanics;
    }

    public Race getRace() {
        return null;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Series getSeries() {
        return null;
    }

    public Set<Sound> getSounds() {
        return null;
    }

    public String getText() {
        return null;
    }
}
