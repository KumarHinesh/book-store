package org.acme.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKS")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "AUTHOR")
    private String author;

    @ManyToOne
    @JoinColumn(name = "BOOKTYPE_ID")
    private BookType type;

}
