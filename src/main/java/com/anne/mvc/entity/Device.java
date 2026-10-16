package com.anne.mvc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @Column(nullable = false, name = "device_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "varchar(6)", name = "device_code", nullable = false)
    private  String code;
    @Column(columnDefinition = "nvarchar(200)", name = "category_id", nullable = false)
    private String name;
    @ManyToOne
    private Category cate;
    @Column(columnDefinition = "decimal(10)", name = "price", nullable = false)
    private double price;
    @Column(columnDefinition = "int", name = "warranty_months", nullable = false)
    private int warrantyMonths;
    @Column(columnDefinition = "date", name = "installation_date", nullable = false)
    private LocalDate installationDate;
    @Column(columnDefinition = "nvarchar(10)", name = "status", nullable = false)
    private String status;
}
