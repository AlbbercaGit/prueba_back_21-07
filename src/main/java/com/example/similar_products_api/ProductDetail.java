package com.example.similar_products_api;

/**
 * Modelo que representa el detalle de un producto.
 * Utilizado como respuesta en el endpoint /product/{productId}/similar.
 */
public class ProductDetail {
    private String id;
    private String name;
    private double price;
    private boolean availability;

    // Constructor vacío necesario para deserialización JSON
    public ProductDetail() {}

    // Constructor completo para facilidad de uso en tests y mocks
    public ProductDetail(String id, String name, double price, boolean availability) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    // Getters y setters estándar
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
