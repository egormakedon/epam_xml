package entity;

import java.util.Objects;

public class Newspaper extends Paper {
    private int subscriptionIndex;
    private String tittle;
    private boolean gloss;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Newspaper)) return false;
        Newspaper newspaper = (Newspaper) o;
        return getSubscriptionIndex() == newspaper.getSubscriptionIndex() &&
                isGloss() == newspaper.isGloss() &&
                Objects.equals(getTittle(), newspaper.getTittle()) &&
                isMothly() == newspaper.isMothly() &&
                isColor() == newspaper.isColor() &&
                getVolume() == newspaper.getVolume() &&
                Objects.equals(getId(), newspaper.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriptionIndex(), getTittle(), isGloss(), isMothly(), isColor(), getVolume(), getId());
    }

    @Override
    public String toString() {
        return  "Newspaper" +
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
