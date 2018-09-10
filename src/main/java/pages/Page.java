package pages;

public interface Page {

    default String url(){
        return "https://www.trademax.se";
    }

    default int waitTimeout(){
        return 8000;
    }
}
