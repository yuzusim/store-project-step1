package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.order.Order;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import lombok.Data;

public class CartRequest {

    @Data
    public static class UpdateDTO{
        private Integer cartId;
        private Integer orderQty;
    }

    @Data
    public static class SaveDTO {
        // 주문수량
        private Integer orderQty;

        // DTO에서 엔티티로 변환하는 메서드

        public Cart toEntity(Product product, User user) {
            return Cart.builder()
                    .orderQty(orderQty)
                    .product(product)
                    .user(user)
                    .build();
        }

    }

}
