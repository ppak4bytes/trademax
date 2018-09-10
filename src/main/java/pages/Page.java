package pages;

public interface Page {

    default String url(){
        return "https://www.furniturebox.se";
    }

    default int waitTimeout(){
        return 8000;
    }
}
