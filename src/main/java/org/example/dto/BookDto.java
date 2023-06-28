package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDate publishDate;
    private String availableDay;
    private boolean visible;
    private LocalDateTime createdDate;

    public BookDto(Integer id, String title, String author, String categoryName, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.publishDate = publishDate;
    }


    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", publishDate=" + publishDate + '}';
    }
}
