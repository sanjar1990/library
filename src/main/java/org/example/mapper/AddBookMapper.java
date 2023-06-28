package org.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookMapper {
    private String title;
    private String author;
    private String categoryName;
    private String publishedDate;
}
