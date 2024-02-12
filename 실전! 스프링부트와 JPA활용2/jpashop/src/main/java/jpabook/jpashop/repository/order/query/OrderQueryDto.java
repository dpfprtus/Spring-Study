package jpabook.jpashop.repository.order.query;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//v6에서 collect grouping할 때 기준을 만들어주는 것.
@Data
@EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private Address address;
    private OrderStatus orderStatus;
    private String name;
    private List<OrderItemQueryDto> orderItems;
    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus,Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public OrderQueryDto(Long orderId, String name,LocalDateTime orderDate,OrderStatus orderStatus,Address address, List<OrderItemQueryDto> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.address = address;
        this.orderStatus = orderStatus;
        this.name = name;
        this.orderItems = orderItems;
    }
}
