package jpabook.jpashop.repository.order.query;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private Address address;
    private OrderStatus orderStatus;
    private String name;

    private String itemName;

    public OrderFlatDto(Long orderId, LocalDateTime orderDate, Address address, OrderStatus orderStatus, String name, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.address = address;
        this.orderStatus = orderStatus;
        this.name = name;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    private int orderPrice;
    private int count;
}
