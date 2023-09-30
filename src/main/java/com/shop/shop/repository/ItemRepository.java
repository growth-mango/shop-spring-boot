package com.shop.shop.repository;

import com.shop.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> { // <Entity Type Class, PK Type>
    // 상품의 이름을 이용하여 데이터를 조회하기 위해 findByItemNm 메소드 추가하기
    List<Item> findByItemNm(String itemNm); // itemNm(상품명) 으로 데이터를 조회하기 위해서 By 뒤에 필드명인 ItemNm을 메소드의 이름으로 붙이기

    // 상품명과 상품 상세 설명을 OR 조건을 이용하여 조회하는 쿼리 메소드
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 파라미터로 넘어온 price 변수 보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드
    List<Item> findByPriceLessThan(Integer price);

    // 상품의 가격을 내림차순으로 조회하는 쿼리 메소드
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer Price);

    @Query("Select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);



}
