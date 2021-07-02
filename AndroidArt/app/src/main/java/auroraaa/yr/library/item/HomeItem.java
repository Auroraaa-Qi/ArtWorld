package auroraaa.yr.library.item;


import java.io.Serializable;
import java.util.List;

public class HomeItem implements Serializable {
    private String id;
    private List<String> images;
    private String title;
    private String username;
    private int likes;
    private String details;
    private String vedioUrl;

    public HomeItem(String id, List<String> images, String t, String u, int l, String d, String v){
        this.id = id;
        this.images = images;
        this.title = t;
        this.username = u;
        this.likes = l;
        this.details = d;
        this.vedioUrl = v;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getImgId(){
        return this.images.get(0);
    }

    public List<String> getImages(){return this.images;}

    public String getUsername(){
        return this.username;
    }

    public int getLikes(){
        return this.likes;
    }

    public String getDetails(){
        return this.details;
    }

    public String getVedioUrl(){
        return this.vedioUrl;
    }
}
