package ru.bmn.web.hsdb.servlet.view.ui;

import ru.bmn.web.hsdb.domain.BoosterCards;
import ru.bmn.web.hsdb.domain.Card;
import ru.bmn.web.hsdb.domain.CollectionItem;
import ru.bmn.web.hsdb.domain.UserCollection;
import ru.bmn.web.hsdb.servlet.view.ViewCollectionCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ViewCollectionCards {
    private final UserCollection collection;
    private final BoosterCards boosterCards;

    public ViewCollectionCards(UserCollection collection, BoosterCards boosterCards) {
        this.collection = collection;
        this.boosterCards = boosterCards;
    }

    public List<ViewCollectionCard> getInItems(String characterFilter, String rarityFilter, String setFilter) {
        List<ViewCollectionCard> result = new ArrayList<>();

        for (CollectionItem ci : this.collection.getItems()) {
            Card card = this.boosterCards.cardById(ci.getCardId());

            if (
                (characterFilter.isEmpty() || card.getCharacterId() == Integer.valueOf(characterFilter))
                &&
                (rarityFilter.isEmpty() || card.getRarityId() == Integer.valueOf(rarityFilter))
                &&
                (setFilter.isEmpty() || card.getSetId() == Integer.valueOf(setFilter))
            ) {
                result.add(
                    new ViewCollectionCard(card, ci)
                );
            }
        }

        Collections.sort(result);
        return result;
    }
}
