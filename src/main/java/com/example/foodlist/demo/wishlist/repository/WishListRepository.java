package com.example.foodlist.demo.wishlist.repository;

import com.example.foodlist.demo.db.MemoryDbRepositoryAbstract;
import com.example.foodlist.demo.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
