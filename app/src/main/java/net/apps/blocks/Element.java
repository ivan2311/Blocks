package net.apps.blocks;

public class Element {

    private int resource;
    private boolean empty;

    public Element() {
        this.empty = true;
    }

    public Element(int resource) {
        this.resource = resource;
        this.empty = false;
    }

    public int getResource() {
        return resource;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;

        Element element = (Element) o;

        return getResource() == element.getResource();

    }

    @Override
    public int hashCode() {
        return getResource();
    }

}
