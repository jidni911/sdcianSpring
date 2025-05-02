package com.jidnivai.sdcian.sdcian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.merchandise.Jersey;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrder;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrderItem;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseySize;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.interfaces.JerseyServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.JerseyOrderRepository;
import com.jidnivai.sdcian.sdcian.repository.JerseyRepository;

@Service
public class JerseyService implements JerseyServiceInt {

    @Autowired
    JerseyRepository jerseyRepository;
    @Autowired
    JerseyOrderRepository jerseyOrderRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Jersey addJersey(Jersey jersey, User user) {
        if (user != null && user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {

            jersey.setCreatedBy(user);
            jersey.setUpdatedBy(user);
            List<Image> images = imageRepository.findAllById(jersey.getImageIds());
            jersey.setImages(images);
            return jerseyRepository.save(jersey);
        } else {
            return null;

        }

    }

    @Override
    public List<Jersey> getJerseys() {
        return jerseyRepository.findAll();
    }

    @Override
    public Jersey getJersey(Long id) {
        return jerseyRepository.findById(id).orElse(null);
    }

    @Override
    public JerseyOrder placeOrder(JerseyOrder jerseyOrder, User user) {
        
        if (user == null)
        return null;
        jerseyOrder.setUser(user);
        for (JerseyOrderItem item : jerseyOrder.getItems()) {
            double price = 0d;
            Jersey jersey = this.getJersey(item.getJersey().getId());
            JerseySize size = item.getSize();
            String sleeve = item.getSleeve().trim();
            if (size.getName().toLowerCase().startsWith("baby")) {
                if (sleeve.toLowerCase().startsWith("half")) {
                    price = jersey.getBabyHalfSleevePrice();
                } else {
                    price = jersey.getBabyFullSleevePrice();
                }
            } else {
                if (size.getName().toLowerCase().startsWith("custom")) {
                    if (sleeve.toLowerCase().startsWith("half")) {
                        price = jersey.getCustomHalfSleevePrice();
                    } else {
                        price = jersey.getCustomFullSleevePrice();
                    }
                } else {
                    if (sleeve.toLowerCase().startsWith("half")) {
                        price = jersey.getHalfSleevePrice();
                    } else {
                        price = jersey.getFullSleevePrice();
                    }
                }
            }
            if (item.getPrice() != price ) {
                return null;
            }
        }
        jerseyOrder.setPending(true);
        switch (jerseyOrder.getDeliveryOption()) {
        case "Home Delivery (Inside Dhaka)":
            if (jerseyOrder.getDeliveryCharge() != 70d) {
                return null;
            }
            break;
        case "Courier (Outside Dhaka)":
            if (jerseyOrder.getDeliveryCharge() != 110d) {
                return null;
            }
            break;
        case "Free Pick Up Point":
            if (jerseyOrder.getDeliveryCharge() != 0d) {
                return null;
            }
            break;

        default:
            return null;
        }
        return jerseyOrderRepository.save(jerseyOrder);
    }

    @Override
    public List<JerseyOrder> getJerseyOrders(User user) {
        if (user == null)
            return null;
        return jerseyOrderRepository.findAllByUserAndPendingOrderByCreatedAtDesc(user, true);
    }

    @Override
    public JerseyOrder makePayment(JerseyOrder entity, User user) {
        if (user == null)
            return null;
        JerseyOrder jerseyOrder = jerseyOrderRepository.findById(entity.getId()).orElse(null);
        if(jerseyOrder.getUser().equals(user)) {
            return null;
        };
        // jerseyOrder.setPending(false);
        jerseyOrder.setPaid(true);
        jerseyOrder.setPaymentMethod(entity.getPaymentMethod());
        jerseyOrder.setAccountNumber(entity.getAccountNumber());
        jerseyOrder.setTrxId(entity.getTrxId());
        return jerseyOrderRepository.save(jerseyOrder);
    }

}
