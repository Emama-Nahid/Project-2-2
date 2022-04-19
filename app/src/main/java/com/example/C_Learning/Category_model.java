package com.example.C_Learning;

public class Category_model {

    private String Name;
    private int Sets;
    private String Url;

    public Category_model(){
        //for firebase
    }

    public Category_model(String name, int sets, String url) {
        Name = name;
        Sets = sets;
        Url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSets() {
        return Sets;
    }

    public void setSets(int sets) {
        Sets = sets;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
