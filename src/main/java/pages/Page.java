package pages;


import java.util.List;

public abstract class Page {

    final String SCREENSHOT_FILEPATH = "target/pdf_screenshots/";

    protected String currentUrl;

    protected String currentItem;

    protected int waitTimeout() {
        return 12000;
    }

    public String url() {
        return currentUrl;
    }

    public String getCurrentItem() {
        return currentItem;
    }


}
