package net.apps.blocks.model;

public class Element {

    public static final int STATUS_EMPTY = 0;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_FULL = 2;

    private int resource;
    private int type;

    private int status;

    public Element() {
        this.status = STATUS_EMPTY;
    }

    public Element(int resource) {
        this.resource = resource;
        this.type = resource;
        this.status = STATUS_FULL;
    }

    public int getResource() {
        return resource;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
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
