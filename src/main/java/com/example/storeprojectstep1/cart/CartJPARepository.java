package com.example.storeprojectstep1.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartJPARepository extends JpaRepository<Cart, Integer> {

    // 주문서 확인 폼
//    @Query("select c from Cart c JOIN FETCH c.product p JOIN FETCH c.user u WHERE u.id =:userId and c.isChecked =:isChecked")
//    List<CartResponse.ListDTO> findByUserIdAndChecked(@Param("userId") int userId, @Param("isChecked") boolean isChecked);
//
//    @Modifying
//    @Query("update Cart c set c.isChecked = :isChecked where c.id = :id")
//    int updateCheckedById();

    //cart-save 용

    //장바구니 체크박스 업데이트
//    @Modifying
//    @Query("update Cart c set c.status = :status where c.id = :id")
//    void updateStatusById(Boolean status, Integer id);
//
//    //롤백
//    @Modifying
//    @Query("update Cart c set c.status = :newStatus where c.status = :oldStatus")
//    int updateStatus(boolean newStatus, boolean oldStatus);
}

