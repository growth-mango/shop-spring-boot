package com.shop.shop.repository;

import com.shop.shop.constant.ItemSellStatus;
import com.shop.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 통합 테스트를 위한 스프링 부트 제공 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행시 application-test 에 설정이 있다면 더 높은 우선순위 부여
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test // 이 메소드 테스트 대상으로 지정하기
    @DisplayName("상품 저장 테스트") // 테스트 코드 실행 시, 여기에 지정한 테스트명 노출
    public void createItemTest(){
        Item item = new Item() ;
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
}