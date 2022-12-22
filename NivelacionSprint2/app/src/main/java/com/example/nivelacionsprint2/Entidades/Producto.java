package com.example.nivelacionsprint2.Entidades;

public class Producto {
    private int id;
    private String name;
    private String description;
    private int price;
    private byte[] image;

    public Producto(String name, String description, int price, byte[] image) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Producto(int id, String name, String description, int price, byte[] image) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
