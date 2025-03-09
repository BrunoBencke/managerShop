package com.techsoft.managerShop.application.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private String description;
    private Long categoryDefaultId;
}
