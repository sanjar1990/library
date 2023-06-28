package org.example.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
public class BestBookMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private Integer takenCount;

    @Override
    public String toString() {
        return "BestBookMapper{" +
                "book id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", takenCount=" + takenCount +
                '}';
    }
}
