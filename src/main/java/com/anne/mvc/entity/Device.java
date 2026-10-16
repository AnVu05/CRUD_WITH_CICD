package com.anne.mvc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Device {
    @Id
    @Column(nullable = false, name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(6)", name = "device_code", nullable = false)
    private  String code;
    @Column(columnDefinition = "nvarchar(200)", name = "device_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
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
