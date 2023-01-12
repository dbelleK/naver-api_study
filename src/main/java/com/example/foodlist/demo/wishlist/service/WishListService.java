package com.example.foodlist.demo.wishlist.service;

import com.example.foodlist.demo.naver.NaverClient;
import com.example.foodlist.demo.naver.dto.SearchImageReq;
import com.example.foodlist.demo.naver.dto.SearchLocalReq;
import com.example.foodlist.demo.wishlist.dto.WishListDto;
import com.example.foodlist.demo.wishlist.entity.WishListEntity;
import com.example.foodlist.demo.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    // 1. 검색 조회
    public WishListDto search(String query) {

        //// 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query); //매개변수로 받아온 query 지정
        var searchLocalRes = naverClient.searchLocal(searchLocalReq); //결과

        //지역검색결과가 있으면
        if (searchLocalRes.getTotal() > 0) {

            var localItem = searchLocalRes.getItems().stream().findFirst().get(); //첫번째 아이템 꺼냄

            //위 localItem 가지고 imageQuery만듬
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", ""); //가로쳐져 있는거 지움 ex) 갈비집(한우) -> 갈비집
            //이미지 검색
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            ////이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if (searchImageRes.getTotal() > 0) {

                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                ////결과를 리턴 //최종화면에 보이는 글
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;

            }

        }
        return new WishListDto();
    }

    // --------------------------------------------------

    // 2. 추가
    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }


    //dto를 가지고 entity를 만듬
    private WishListEntity dtoToEntity(WishListDto wishListDto) {
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    //entity를 가지고 dto로 바꿈
    private WishListDto entityToDto(WishListEntity wishListEntity) {
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    // --------------------------------------------------

    // 3. 전체 리스트 가져옴
    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    // 4. 삭제
    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    // --------------------------------------------------

    // 5. 방문 추가
    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){ //있으면 업데이트
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
