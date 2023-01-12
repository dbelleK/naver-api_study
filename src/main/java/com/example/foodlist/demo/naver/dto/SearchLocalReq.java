package com.example.foodlist.demo.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq {

    //요청변수

        private String query = "";

        private int display = 1;

        private int start = 1;

        private String sort = "random";


        //메서드 만들어서 용이하게 사용하게
        public MultiValueMap<String, String> toMultiValueMap(){

            var map = new LinkedMultiValueMap<String, String>();

            map.add("query",query);
            map.add("display",String.valueOf(display));
            map.add("start", String.valueOf(start));
            map.add("sort",sort);

            return map;
        }
    }
