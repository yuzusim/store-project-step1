package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<OrderItem, Integer> {

}