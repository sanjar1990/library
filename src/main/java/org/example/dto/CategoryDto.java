package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class CategoryDto {

    private Integer  id;
    private String categoryName;
    private LocalDateTime createdDate;
    private boolean visible;

    public CategoryDto(Integer id, String categoryName, LocalDateTime createdDate, boolean visible) {
        this.id = id;
        this.categoryName = categoryName;
        this.createdDate = createdDate;
        this.visible = visible;
    }
}
