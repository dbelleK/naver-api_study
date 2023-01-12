package com.example.foodlist.demo.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    //특정 리턴
    Optional<T> findById(int index);

    //저장
    T save(T entity);

    //삭제
    void deleteById(int index);

    //전체 리턴
    List<T> findAll();

}
