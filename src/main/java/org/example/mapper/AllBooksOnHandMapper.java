package org.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AllBooksOnHandMapper {
    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private LocalDateTime takenDate;
    private LocalDate deadlineDate;
    private String studentName;
    private String studentSurname;
    private String studentPhone;

    public AllBooksOnHandMapper(Integer bookId, String title, String author, String category, LocalDateTime takenDate, LocalDate deadlineDate, String studentName, String studentSurname, String studentPhone) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.takenDate = takenDate;
        this.deadlineDate = deadlineDate;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentPhone = studentPhone;
    }
}
