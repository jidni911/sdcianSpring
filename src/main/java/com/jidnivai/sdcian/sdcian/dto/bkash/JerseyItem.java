package com.jidnivai.sdcian.sdcian.dto.bkash;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class JerseyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private List<String> imageUrlList;
    private Float regularPrice;
    private List<String> sizes;
    private Boolean isCustomSizeAvailable;
    private Float customSizePrice;
    private Boolean namedEdition;
    private Boolean active;
    private String nickName;
}
