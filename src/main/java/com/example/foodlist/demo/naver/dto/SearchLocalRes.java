package com.example.foodlist.demo.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {

    //출력결과

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchLocalItem> items; //items에 item이 들어있다


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem{

        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private String category;
        private int mapx;
        private int mapy;

    }
}
