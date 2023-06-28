package org.example.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookHistoryMapper {
    private LocalDateTime takenDate;
    private LocalDate returnDate;
    private String studentName;
    private String studentSurname;
    private String studentPhone;
    private String note;

    public BookHistoryMapper(LocalDateTime takenDate, LocalDate returnDate, String studentName, String studentSurname, String studentPhone, String note) {
        this.takenDate = takenDate;
        this.returnDate = returnDate;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentPhone = studentPhone;
        this.note = note;
    }
}
