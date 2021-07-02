package auroraaa.yr.library.data;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.library.item.DashboardItem;

public class GlobalData extends Application {

    private boolean isLogged = false;
    private ArrayList<DashboardItem> likes = new ArrayList<>();
    private String username = "";

    public  void setLogged(boolean logged){
        this.isLogged = logged;
    }

    public void setLikes(ArrayList<DashboardItem> likes){
        this.likes = likes;
    }

    public  void setUsername(String user){
        this.username = user;
    }

    public  void addLike(DashboardItem item){
        this.likes.add(item);
    }

    public  void removeLike(String path){
        this.likes.remove(path);
    }

    public ArrayList<DashboardItem> getLikes(){
        return this.likes;
    }

}
