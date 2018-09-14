package pages;

public abstract class Page {

    protected String currentUrl;

    protected String currentItem;

    protected int waitTimeout(){
        return 8000;
    }

    public String url(){
        return currentUrl;
    }

    public String getCurrentItem() {
        return currentItem;
    }
}
