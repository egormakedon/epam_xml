package entity;

import exception.MethodNotSupportedException;

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
