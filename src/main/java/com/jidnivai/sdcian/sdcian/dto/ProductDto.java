package com.jidnivai.sdcian.sdcian.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String category;

    private String brand;

    private Float price;

    private Float discountPrice;

    private String stockStatus;// Availabe, Low , Out of Stock

    private int quantity;


    private Image mainImage;

    private List<Image> galleryImages;

    private String dimensions;

    private String weight;


    private UserDto seller;

    private Float ratings;

    private int reviews;

    private List<String> tags;

    private String origin;

    private List<String> features;

    private LocalDateTime addedDate;
}
