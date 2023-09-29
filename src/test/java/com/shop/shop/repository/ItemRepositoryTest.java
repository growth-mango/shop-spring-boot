package com.shop.shop.repository;

import com.shop.shop.constant.ItemSellStatus;
import com.shop.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 통합 테스트를 위한 스프링 부트 제공 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행시 application-test 에 설정이 있다면 더 높은 우선순위 부여
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test // 이 메소드 테스트 대상으로 지정하기
    @DisplayName("상품 저장 테스트") // 테스트 코드 실행 시, 여기에 지정한 테스트명 노출
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    // 테스트 코드 실행 시 데이터베이스에 상품 데이터가 없으므로 테스트 데이터 생성을 위해서 10개의 상품을 저장하는 메소드
    public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1"); // 리파지토리 인터페이스에서 작성했던 findByItemNm 메소드 호출
        for (Item item : itemList) {
            System.out.println(item.toString()); // 조회 결과 얻은 item 객체들을 출력하기
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 OR 테스트")
    void findByItemNmOrItemDetail() {
        this.createItemList();
        // 상품명이 "테스트 상품1" 또는 상세 설명이 "테스트 상품 상세 설명5"이면 해당 상품을 itemList에 할당
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    void findByPriceLessThan() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
}
