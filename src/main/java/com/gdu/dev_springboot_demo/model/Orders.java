package com.gdu.dev_springboot_demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders   {

    public static final String PENDING = "PENDING";
    public static final String CANCELLED = "CANCELLED";
    public static final String COMPLETED = "COMPLETED";
    public static final String DELIVERED = "DELIVERED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users; // Mối quan hệ Many-to-One với User

//    @Column(name = "status", nullable = false, columnDefinition = "default 'PENDING'")
//    private String status;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private String status;


    // Mối quan hệ OneToMany từ Orders đến OrderItems
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> listOrderItems;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", users=" + users +
                ", status='" + status + '\'' +
                ", listOrderItems=" + listOrderItems +
                '}';
    }
}
