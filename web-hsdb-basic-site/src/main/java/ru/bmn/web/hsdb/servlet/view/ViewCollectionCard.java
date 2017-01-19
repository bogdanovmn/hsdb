package ru.bmn.web.hsdb.servlet.view;

import ru.bmn.web.hsdb.domain.Card;
import ru.bmn.web.hsdb.domain.CollectionItem;

/**
 *
 */
public class ViewCollectionCard implements Comparable<ViewCollectionCard> {
    private final Card card;
    private final CollectionItem collectionItem;


    public ViewCollectionCard(Card card, CollectionItem ci) {
        this.card = card;
        this.collectionItem = ci;
    }

    @Override
    public int compareTo(ViewCollectionCard o) {
        int result = this.card.getManaCost() - o.card.getManaCost();

        if (result == 0) {
            result = this.card.getId() - o.card.getId();
        }

        return result;
    }
}
