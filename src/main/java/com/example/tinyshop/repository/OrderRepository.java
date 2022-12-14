package com.example.tinyshop.repository;

import com.example.tinyshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    void deleteAllByBuyerId(long id);
}
