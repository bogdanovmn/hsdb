package ru.bmn.web.hsdb.parser.hearthpwn;

import com.github.bogdanovmn.downloadwlc.UrlContentDiscCache;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.bmn.web.hsdb.model.entity.hs.*;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

/* default */ class CardPage {
    private static final UrlContentDiscCache CACHE = new UrlContentDiscCache(
        CardPage.class
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
    private String text;
    private Series series;
    private Set<Sound> sounds;
    private Rarity rarity;
    private Race race;


    public CardPage(URL url) {
        this.url = url;
    }

    public void parse()
        throws IOException
    {
        if (!this.isParsed) {
            String html = CACHE.getText(this.url);
            this.htmlDocument = Jsoup.parse(html);

            this.imageUrl = this.htmlDocument.select("img[class=hscard-static]").first().attr("src");

            for (Element qInfo : this.htmlDocument.select("aside[class^=infobox] ul li")) {
                if (qInfo.text().startsWith("Rarity:")) {
                    this.rarity = (Rarity) new Rarity()
                        .setName(
                            qInfo.select("a").first().text()
                        );
                }
                else if (qInfo.text().startsWith("Set:")) {
                    this.series = (Series) new Series()
                        .setName(
                            qInfo.select("a").first().text()
                        );
                }
                else if (qInfo.text().startsWith("Race:")) {
                    this.race = (Race) new Race()
                        .setName(
                            qInfo.select("a").first().text()
                        );
                }
                else if (qInfo.text().startsWith("Artist:")) {
                    this.artist = (Artist) new Artist()
                        .setName(
                            qInfo.text().replaceFirst("Artist: ", "")
                        );
                }
                else if (qInfo.text().equals("Collectible")) {
                    this.collectible = true;
                }
            }

            for (Element infoDiv : this.htmlDocument.select("div[class^=card-info]")) {
                String h3Title = infoDiv.select("h3").first().text();

                switch (h3Title) {
                    case "Card Text":
                        this.text = infoDiv.select("p").first().text();
//                        this.mechanics = infoDiv.select("span[class*=mechanics]").stream()
                        this.mechanics = infoDiv.select("strong").stream()
                            .map(
                                x -> {
                                    Mechanic mechanic = new Mechanic();
                                    Elements descrElements = x.select("span[class*=mechanics]");
                                    if (!descrElements.isEmpty()) {
                                        mechanic.setDescription(descrElements.first().attr("title"));
                                    }
                                    mechanic.setName(x.text());
                                    return mechanic;
                                }
                            )
                            .collect(
                                Collectors.toSet()
                            );
                        break;
                    case "Flavor Text":
                        this.aboutText = infoDiv.select("p").first().text();
                        break;
                    case "Card Sounds":
                        this.sounds = infoDiv.select("audio").stream()
                            .map(
                                x -> new Sound()
                                    .setName(x.attr("id").replaceFirst("sound", ""))
                                    .setUrl(x.attr("src"))
                            )
                            .collect(Collectors.toSet());
                        break;
                }
            }
        }
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

    public Race getRace() {
        return this.race;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Series getSeries() {
        return this.series;
    }

    public Set<Mechanic> getMechanics() {
        return mechanics;
    }

    public String getAboutText() {
        return this.aboutText;
    }

    public Set<Sound> getSounds() {
        return this.sounds;
    }

    public String getText() {
        return this.text;
    }
}
