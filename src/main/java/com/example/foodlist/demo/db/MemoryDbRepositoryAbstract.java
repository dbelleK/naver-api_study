package com.example.foodlist.demo.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it->it.getIndex() == index).findFirst(); //MemoryDbEntity에서 상속받은 getIndex
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            //db에 이미 데이터가 있는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;

        }else{
            //db에 데이터가 없는 경우
            var preIndex  = optionalEntity.get().getIndex(); //이전 인덱스 뽑음
            entity.setIndex(preIndex);

            deleteById(preIndex); //기존데이터 지우고
            db.add(entity); //새로받은 엔티티 추가
            return entity;

        }

    }

    @Override
    public void deleteById(int index) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();

        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
