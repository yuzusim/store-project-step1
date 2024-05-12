package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.user.User;
import lombok.Data;

public class OrderRequest {

    @Data
    public static class PaymentDTO {
        private String payment;
    }

    @Data
    public static class UpdateDTO {
        private Integer cartId;
        private Integer orderQty;
        private Boolean status;
    }


    //구매하기(주문하기)
    @Data
    public static class SaveDTO {
        //private Integer orderId;

        private User user; //user 정보
        private Cart cart; // 카트 정보

        //private Product product; //product 정보
        //private Integer orderQty;
        private Integer totalQty; // 주문 수량
        private Boolean status; // 주문 상태, Boolean 대신 열거형(enum) 사용을 고려해 볼 수 있음


        // DTO에서 엔티티로 변환하는 메서드
        public Order toEntity( User user, Cart cart) {
            //Integer orderQty = (totalQty != null) ? totalQty : 0;
            return Order.builder()
                    //.product(product)
                    .user(user)
                    .cart(cart)
                    //.orderQty(orderQty)
                    .status(status)
                    .build();
        }

    }


}
