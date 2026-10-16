package com.anne.mvc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", columnDefinition = "int", nullable = false)
    private Integer id;
    @Column(name = "category_name", columnDefinition = "nvarchar(100)", nullable = false)
    private String name;
}
