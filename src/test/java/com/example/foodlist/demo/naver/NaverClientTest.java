package com.example.foodlist.demo.naver;

import com.example.foodlist.demo.naver.dto.SearchImageReq;
import com.example.foodlist.demo.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    //장소검색
    @Test
    public void searchLocalTest(){

        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }

    //이미지 검색
    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }
}
