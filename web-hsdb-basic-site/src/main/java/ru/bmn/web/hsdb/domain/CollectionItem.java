package ru.bmn.web.hsdb.domain;

/**
 *
 */
public class CollectionItem {
    private final int cardId;
    private final int normCnt;
    private final int goldCnt;

    public CollectionItem(final int cardId, final int normCnt, final int goldCnt) {
        this.cardId  = cardId;
        this.goldCnt = goldCnt;
        this.normCnt = normCnt;
    }

    public int getCardId() {
        return cardId;
    }
}
