package org.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.TakenBookStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakenBookMapper {
    private String takenBookId;
    private LocalDateTime create_date;
    private TakenBookStatus takenBookStatus;
    private Integer bookId;
    private Integer studentId;




}
