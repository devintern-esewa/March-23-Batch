package com.don.solocanteenmanagementsystem.fooditem.repository;

import com.don.solocanteenmanagementsystem.fooditem.model.StoreFoodQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreFoodQuantityRepository extends JpaRepository<StoreFoodQuantity, Long> {

    @Query(value = "select quantity from store_food_quantity where food_id =?1 and customer_id=?2", nativeQuery = true)
    int findQuantityByTwoIds(Long foodId, Long customerId);
}
