package org.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksOnHand {
    private String id;
    private String title;
    private String author;
    private String category;
    private LocalDate takenDate;
    private LocalDate returnDate;

    public BooksOnHand(String id, String title, String author, String category, LocalDateTime takenDate, LocalDate returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.takenDate =LocalDate.of(takenDate.getYear(),takenDate.getMonth(),takenDate.getDayOfMonth());
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BooksOnHand{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", return DeadLine=" + takenDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public String takenBookHistoryShow() {
        return "BooksHasTaken{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", taken date=" + takenDate +
                ", returned date=" + returnDate +
                '}';
    }
}
