package com.gdu.dev_springboot_demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems  {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Products products;

  @ManyToOne
  @JoinColumn(name ="order_id", referencedColumnName = "id", nullable = false)
  private Orders orders;

  @Column(name = "quantity", nullable = false)
  private int quantity;

}
