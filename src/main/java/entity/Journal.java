package entity;

import java.util.Objects;

public class Journal extends Paper {
    private int subscriptionIndex;
    private String tittle;
    private boolean gloss;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        Journal journal = (Journal) o;

        return getSubscriptionIndex() == journal.getSubscriptionIndex() &&
                isGloss() == journal.isGloss() &&
                Objects.equals(getTittle(), journal.getTittle()) &&
                isMothly() == journal.isMothly() &&
                isColor() == journal.isColor() &&
                getVolume() == journal.getVolume() &&
                Objects.equals(getId(), journal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriptionIndex(), getTittle(), isGloss(), isMothly(), isColor(), getVolume(), getId());
    }

    @Override
    public String toString() {
        return "Journal" +
                " subscriptionIndex=" + subscriptionIndex +
                " tittle=" + tittle +
                " gloss=" + gloss +
                " mothly=" + isMothly() +
                " color=" + isColor() +
                " volume=" + getVolume() +
                " id=" + getId();
    }

    public int getSubscriptionIndex() {
        return subscriptionIndex;
    }

    public void setSubscriptionIndex(int subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public boolean isGloss() {
        return gloss;
    }

    public void setGloss(boolean gloss) {
        this.gloss = gloss;
    }
}
