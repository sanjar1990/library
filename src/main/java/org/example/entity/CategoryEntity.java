package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column(name = "category_name",length =50, unique = true, nullable = false )
    private String categoryName;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column
    private boolean visible;
}
