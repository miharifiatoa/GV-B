package org.sales_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "article_types")
public class ArticleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String color;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "article")
    private ArticleEntity article;
}
