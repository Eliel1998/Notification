package com.example.notification;

class GameEntity{
    String name;
    Double price;
    String genre;
    String Plataform;

    public GameEntity(String name, Double price, String genre, String Plataform){
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.Plataform = Plataform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlataform() {
        return Plataform;
    }

    public void setPlataform(String plataform) {
        Plataform = plataform;
    }
}