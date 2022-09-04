//package com.cg.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "products")
//@Accessors(chain = true)
//public class Product extends BaseEntity {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//
//    private String title;
//
//    private String slug;
//
//    @Column(precision = 9, scale = 0, updatable = false)
//    private BigDecimal price;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
//    private Category category;
//
//    @OneToOne(mappedBy = "product")
//    private ProductMedia productMedia;
//}
