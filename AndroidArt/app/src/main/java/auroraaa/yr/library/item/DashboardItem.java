package auroraaa.yr.library.item;

import java.io.Serializable;

public class DashboardItem implements Serializable {
    private String id;
    private String title;
    private String url;

    public DashboardItem(String i, String t, String u){
        this.id = i;
        this.title = t;
        this.url = u;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getUrl(){
        return this.url;
    }
}
