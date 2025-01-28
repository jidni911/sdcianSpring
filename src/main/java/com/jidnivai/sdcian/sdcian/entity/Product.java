package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Product {

    //{
    //     "id": "prod001",
    //     "name": "Mountain Bike",
    //     "description": "A durable mountain bike perfect for off-road adventures.",
    //     "category": "Bikes",
    //     "brand": "TrailBlazer",
    //     "price": 499.99,
    //     "discountPrice": 449.99,
    //     "currency": "USD",
    //     "stockStatus": "In Stock",
    //     "quantityAvailable": 20,
    //     "mainImage": "https://www.diycarbonbikes.com/cdn/shop/files/F130fullbikegray-Photoroom_300x300.jpg",
    //     "galleryImages": [
    //       "https://via.placeholder.com/600x400",
    //       "https://via.placeholder.com/600x400"
    //     ],
    //     "dimensions": {
    //       "height": "110 cm",
    //       "width": "180 cm",
    //       "depth": "40 cm"
    //     },
    //     "weight": "12 kg",
    //     "sellerId": "seller01",
    //     "rating": 4.7,
    //     "reviews": 120,
    //     "tags": [
    //       "Mountain Bike",
    //       "Durable",
    //       "Off-road"
    //     ],
    //     "origin": "USA",
    //     "features": [
    //       "High-durability frame",
    //       "Shock-absorbing suspension"
    //     ],
    //     "addedDate": "2024-12-29"
    //   }

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String category;

    private String brand;

    private double price;

    private double discountPrice;

    private String stockStatus;//Availabe, Low , Out of Stock

    private int quantity;

    @OneToOne
    private Image mainImage;

    @OneToMany
    private List<Image> galleryImages;

    private String dimensions;
    private String weight;

    @ManyToOne
    private User seller;

    private double ratings;

    private int reviews;

    private List<String> tags;

    private String origin;

    private List<String> features;
    private String addedDate;
}
