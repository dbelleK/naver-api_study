package com.example.foodlist.demo.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    //출력결과

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImagelItem> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImagelItem{

        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
