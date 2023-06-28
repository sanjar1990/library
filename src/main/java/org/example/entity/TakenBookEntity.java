package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.TakenBookStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taken_book")
public class TakenBookEntity {
   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
   private String id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "student_id")
   private ProfileEntity studentId;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "book_id")
   private BookEntity bookId;
   @Column(name = "created_date")
   private LocalDateTime createdDate;
   @Column
   @Enumerated(EnumType.STRING)
   private TakenBookStatus status;
   @Column(columnDefinition = "text")
   private String note;
   @Column(name = "return_date")
   private LocalDate returnDate;
}
