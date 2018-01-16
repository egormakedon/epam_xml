package entity;

import exception.MethodNotSupportedException;

import java.util.Objects;

public class Booklet extends Paper {
    private String tittle;
    private boolean gloss;

    @Override
    public int getSubscriptionIndex() throws MethodNotSupportedException {
        throw new MethodNotSupportedException("NotSupported");
    }

    @Override
    public void setSubscriptionIndex(int subscriptionIndex) throws MethodNotSupportedException {
        throw new MethodNotSupportedException("NotSupported");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booklet)) return false;
        Booklet booklet = (Booklet) o;

        return  isGloss() == booklet.isGloss() &&
                Objects.equals(getTittle(), booklet.getTittle()) &&
                isMothly() == booklet.isMothly() &&
                isColor() == booklet.isColor() &&
                getVolume() == booklet.getVolume() &&
                Objects.equals(getId(), booklet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTittle(), isGloss(), isMothly(), isColor(), getVolume(), getId());
    }

    @Override
    public String toString() {
        return "Booklet" +
                " tittle=" + tittle +
                " gloss=" + gloss +
                " mothly=" + isMothly() +
                " color=" + isColor() +
                " volume=" + getVolume() +
                " id=" + getId();
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
