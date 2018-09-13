package pages;

public interface Page {

    default String url(){
        return "https://www.chilli.se";
    }

    default int waitTimeout(){
        return 8000;
    }
}
