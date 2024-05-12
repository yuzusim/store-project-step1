package com.example.storeprojectstep1.order;

import com.example.storeprojectstep1.cart.Cart;
import com.example.storeprojectstep1.cart.CartRepository;
import com.example.storeprojectstep1.cart.CartRequest;
import com.example.storeprojectstep1.cart.CartResponse;
import com.example.storeprojectstep1.orderItem.OrderItemRepository;
import com.example.storeprojectstep1.product.Product;
import com.example.storeprojectstep1.product.ProductRepository;
import com.example.storeprojectstep1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductRepository productRepo;
    private final CartRepository cartRepo;
    private final HttpSession session;
    private final EntityManager em;


    //상세보기
//    public OrderResponse.DetailDTO findById(int id) {
//        Order order = orderRepo.findById(id);
//        return new OrderResponse.DetailDTO(order);
//    }



    //주문서 (카트에 있는거 들고 와서 뿌림 .. 카트를 DI)
    @Transactional
    public List<CartResponse.CartDTO> findByCartIdAndUserIdAndStatus(Integer userId) {
        // 저장된 리스트를 사용자 ID와 카트 ID를 기준으로 조회
        List<CartResponse.CartDTO> cartList = orderRepo.findByCartIdAndUserIdAndStatus(userId);
        cartRepo.updateStatus();
        // 조회된 주문 리스트를 DTO로 변환
        return cartList;
    }


    //구매하기(주문하기)
//    @Transactional
//    public OrderResponse.SaveDTO save(OrderRequest.SaveDTO reqDTO, Product product, Cart cart, User user) {
//        System.out.println("들어올꺼니?" + reqDTO);
//        //Product product = productRepo.findById(id);
//        Order order = orderRepo.save(reqDTO.toEntity(product, cart, user));
//
//
//        return new OrderResponse.SaveDTO(order);
//    }

    //

    @Transactional
    public OrderResponse.OrderSaveDTO save(OrderRequest.SaveDTO saveDTO, User user) {
        Cart cart = cartRepo.findByCartUserId(user.getId());

        // 주문을 저장하기 전에 카트가 존재하는지 확인합니다.
//        if (cart.isEmpty()) {
//            throw new IllegalStateException("해당 사용자의 카트를 찾을 수 없습니다.");
//        }
//
//        // 여러 개의 카트 중 첫 번째 카트를 선택합니다.
//        Cart cart = cart.get(0);

        // 주문을 저장합니다.
        Order order = orderRepo.save(saveDTO.toEntity(user, cart));

//        // 주문에 연관된 상품이 있는지 확인합니다.
//        if (order.getProduct() == null) {
//            throw new IllegalStateException("주문에 연관된 상품을 찾을 수 없습니다.");
//        }


        // 저장된 주문에 대한 응답을 생성하여 반환합니다.
        return new OrderResponse.OrderSaveDTO(order);
    }

//    @Transactional
//    public OrderResponse.OrderSaveDTO save(OrderRequest.SaveDTO saveDTO, User user) {
//        Cart cart = cartRepo.findByCartUserId(user.getId());
//        Order order = orderRepo.save(saveDTO.toEntity(user, cart));
//        return new OrderResponse.OrderSaveDTO(order);
//    }

//    @Transactional
//    public OrderResponse.OrderSaveDTO save(OrderRequest.SaveDTO reqDTO, int id, Cart cart, User user) {
//        Product product = productRepo.findById(id);
//        Order order = orderRepo.save(reqDTO.toEntity(product, cart, user));
//        System.out.println("!!!드루와?드루와?드루와?"+reqDTO);
//        return new OrderResponse.OrderSaveDTO(order);
//    }

////order 저장
    //Integer orderId = orderRepo.save(reqDTO);

    //orderitem에 저장
    //orderItemRepo.save(reqDTO, orderId);



    //수량업데이트(구매한 것만)
    //orderRepo.updateQty(requestDTO);

    //체크한 장바구니는 딜리트 시킨다아!!!
    //orderItemRepo.save(requestDTO, orderId);


    //주문 폼 order-form
//    public List<OrderResponse.OrderDTO> findByCartAndUserId() {
//        List<Order> saveList = orderRepo.findByCartAndUserId();
//        return saveList.stream().map(OrderResponse.OrderDTO::new).toList();
//    }

    //목록 메서드 order/list
    public List<OrderResponse.ListDTO> findAll() {
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream().map(OrderResponse.ListDTO::new).toList();
    }

}
