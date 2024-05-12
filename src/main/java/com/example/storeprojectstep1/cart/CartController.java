package com.example.storeprojectstep1.cart;

import com.example.storeprojectstep1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartService cartService;
    private final CartJPARepository cartJPARepo;
    private final HttpSession session;


    //장바구니 췍
    @PostMapping("/cart/update")
    public ResponseEntity<?> update(@RequestBody List<CartRequest.UpdateDTO> reqDTO) {
        System.out.println("장바구니 값 받니? : " + reqDTO);

        // 서비스 계층의 메서드를 호출하여 장바구니 상태 업데이트 처리
        cartService.updateByCarts(reqDTO);

        // 성공적으로 처리되었음을 의미하는 HTTP 응답 반환
        return ResponseEntity.ok().body("선택한 상품의 구매를 진행 하시겠습니까?");
    }

//    @PostMapping("/cart/update")
//    public ResponseEntity<?> update(@RequestBody List<CartRequest.UpdateDTO> reqDTO) {
//        System.out.println("장바구니 값 받니? : " + reqDTO);
//        cartService.updateByCarts(reqDTO);
//
//        return ResponseEntity.ok().body("선택한 상품의 구매를 진행 하시겠습니까?");
//    }

    //장바구니 폼
    @GetMapping("/cart/{id}/cart-form")
    public String cartForm(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<CartResponse.CartDTO> cartSaveList =
                cartService.findByUserId(sessionUser.getId());
        request.setAttribute("cartSaveList", cartSaveList);
        session.setAttribute("user", sessionUser);
        return "cart/cart-form";
    }

    //장바구니 담기
    @PostMapping("/cart/{id}/save")
    public String cartSave(@PathVariable Integer id, CartRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        cartService.save(reqDTO, id, sessionUser);
        return "redirect:/cart/" + id + "/cart-form";
    }

}
