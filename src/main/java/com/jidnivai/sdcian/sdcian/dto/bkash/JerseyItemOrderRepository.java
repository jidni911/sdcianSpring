package com.jidnivai.sdcian.sdcian.dto.bkash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface JerseyItemOrderRepository extends JpaRepository<JerseyItemOrder, Long>{

    List<JerseyItemOrder> findAllByJerseyBuyer(JerseyBuyer jerseyBuyer);

    JerseyItemOrder findByPaymentId(String paymemntId);

    @Query(value ="SELECT  " +  
                "  jersey_item_order.id, " +  
                "  jersey_buyer_number, " +  
                "  paid, " +  
                "  total, " +  
                "  STATUS, " +  
                "  paid_to, " +  
                "  payment_method, " +  
                "  buyer_address, " +  
                "  buyer_name, " +  
                "  buyer_phone_number, " +  
                "  delevery_charge, " +  
                "  free_pickup_point, " +  
                "  payment_id, " +  
                "  trx_id, " +  
                "  delivery_method, " +  
                "  quantity, " +  
                "  rate, " +  
                "  size, " +  
                "  jersey_item_id, " +  
                "  name_on_jersey, " +
                "  nick_name " +  
                "FROM  " +  
                "  jersey_item_order " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_jersey_item_order_items  " +  
                "  ON jersey_item_order.id = jersey_item_order_jersey_item_order_items.jersey_item_order_id " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_item  " +  
                "  ON jersey_item_order_jersey_item_order_items.jersey_item_order_items_id = jersey_item_order_item.id " + 
                "LEFT JOIN  " +  
                "  jersey_item  " +  
                "  ON jersey_item_order_item.jersey_item_id = jersey_item.id " + 
                "", nativeQuery = true)
   List<OrderDTO> findAllOrders();

    @Query(value ="SELECT  " +  
                "  jersey_item_order.id, " +  
                "  jersey_buyer_number, " +  
                "  paid, " +  
                "  total, " +  
                "  STATUS, " +  
                "  paid_to, " +  
                "  payment_method, " +  
                "  buyer_address, " +  
                "  buyer_name, " +  
                "  buyer_phone_number, " +  
                "  delevery_charge, " +  
                "  free_pickup_point, " +  
                "  payment_id, " +  
                "  trx_id, " +  
                "  delivery_method, " +  
                "  quantity, " +  
                "  rate, " +  
                "  size, " +  
                "  jersey_item_id, " +  
                "  name_on_jersey, " +
                "  nick_name " +  
                "FROM  " +  
                "  jersey_item_order " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_jersey_item_order_items  " +  
                "  ON jersey_item_order.id = jersey_item_order_jersey_item_order_items.jersey_item_order_id " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_item  " +  
                "  ON jersey_item_order_jersey_item_order_items.jersey_item_order_items_id = jersey_item_order_item.id " + 
                "LEFT JOIN  " +  
                "  jersey_item  " +  
                "  ON jersey_item_order_item.jersey_item_id = jersey_item.id " + 
                "WHERE  " +  
                " ( paid = true) " +  
                "", nativeQuery = true)
   List<OrderDTO> findAllPaidOrders();

    @Query(value ="SELECT  " +  
                "  jersey_item_order.id, " +  
                "  jersey_buyer_number, " +  
                "  paid, " +  
                "  total, " +  
                "  STATUS, " +  
                "  paid_to, " +  
                "  payment_method, " +  
                "  buyer_address, " +  
                "  buyer_name, " +  
                "  buyer_phone_number, " +  
                "  delevery_charge, " +  
                "  free_pickup_point, " +  
                "  payment_id, " +  
                "  trx_id, " +  
                "  delivery_method, " +  
                "  quantity, " +  
                "  rate, " +  
                "  size, " +  
                "  jersey_item_id, " +  
                "  name_on_jersey, " +
                "  nick_name " +  
                "FROM  " +  
                "  jersey_item_order " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_jersey_item_order_items  " +  
                "  ON jersey_item_order.id = jersey_item_order_jersey_item_order_items.jersey_item_order_id " +  
                "LEFT JOIN  " +  
                "  jersey_item_order_item  " +  
                "  ON jersey_item_order_jersey_item_order_items.jersey_item_order_items_id = jersey_item_order_item.id " + 
                "LEFT JOIN  " +  
                "  jersey_item  " +  
                "  ON jersey_item_order_item.jersey_item_id = jersey_item.id " + 
                "WHERE  " +  
                " ( paid = false) " +  
                "", nativeQuery = true)
   List<OrderDTO> findAllUnpaidOrders();
    
}
