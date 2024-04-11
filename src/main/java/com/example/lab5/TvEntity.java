package com.example.lab5;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс телевизора
 */
@Entity
@Table(name = "tv")
@NoArgsConstructor
@Data
public class TvEntity {
    /**
     * Идентификатор предмета
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    /**
     * Название телевизора
     */
    @Column(name = "product_name")
    private String name;
    /**
     * Тип телевизора
     */
    @Column(name = "type")
    private String type;
    /**
     * Название бренда
     */
    @Column(name = "brand")
    private String brand;
    /**
     * Диагональ телевизора
     */
    @Column(name = "screen_diagonal")
    private int size;
    /**
     * Цена товара
     */
    @Column(name = "price")
    private double price;
    /**
     * Количество телевизоров
     */
    @Column(name = "quantity")
    private int quantity;


    /**
     * Получает индекс товара
     * @return индекс товара
     */
    public int getId() {
        return id;
    }
    public TvEntity(int id, String name, String type, String brand, int size, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Устанавливает индекс товара
     * @param id индекс товара
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает название предмета
     * @return название предмета
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название телевизора
     * @param name название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает тип телевизора
     * @return тип телевизора
     */
    public String getType() {
        return type;
    }

    /**
     * Устанавливает тип телевизора
     * @param type тип телевизора
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Получает бренд телевизора
     * @return название бренда
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд  телевизора
     * @param brand название бренда
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Получает диагональ телевизора
     * @return диагональ телевизора
     */
    public int getSize() {
        return size;
    }

    /**
     * Устанавливает диагональ телевизора
     * @param size диагональ телевизора
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Получает текущую цену
     * @return цена товара
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает цену товара
     * @param price цена товара
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Получает количество товара
     * @return количество
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Устанавливает количество товара
     * @param quantity количество
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Создаёт строковое представление объекта
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d, Brand: %s, Type: %s, Product Name: %s, Screen diagonal: %d, Price: %.2f ₽, Quantity: %d",
                id, name, type, brand, size, price, quantity
        );
    }
}