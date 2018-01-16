package entity;

import exception.MethodNotSupportedException;

public abstract class Paper {
    private boolean mothly;
    private boolean color;
    private int volume;
    private String id;

    public boolean isMothly() {
        return mothly;
    }

    public void setMothly(boolean mothly) {
        this.mothly = mothly;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract int getSubscriptionIndex() throws MethodNotSupportedException;
    public abstract void setSubscriptionIndex(int subscriptionIndex) throws MethodNotSupportedException;
    public abstract String getTittle();
    public abstract void setTittle(String tittle);
    public abstract boolean isGloss() throws MethodNotSupportedException;
    public abstract void setGloss(boolean gloss) throws MethodNotSupportedException;
}
