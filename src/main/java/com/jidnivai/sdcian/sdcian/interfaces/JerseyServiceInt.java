package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.merchandise.Jersey;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrder;

public interface JerseyServiceInt {

    Jersey addJersey(Jersey jersey, User user);

    List<Jersey> getJerseys();

    Jersey getJersey(Long id);

    JerseyOrder placeOrder(JerseyOrder jerseyOrder, User user);

    List<JerseyOrder> getJerseyOrders(User user);

    JerseyOrder makePayment(JerseyOrder entity, User user);
    
}
