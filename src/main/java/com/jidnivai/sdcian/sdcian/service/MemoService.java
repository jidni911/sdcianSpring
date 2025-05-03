package com.jidnivai.sdcian.sdcian.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.dto.MemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Memo;
import com.jidnivai.sdcian.sdcian.entity.shop.Product;
import com.jidnivai.sdcian.sdcian.interfaces.MemoServiceInt;
import com.jidnivai.sdcian.sdcian.repository.MemoRepository;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;

@Service
public class MemoService implements MemoServiceInt {

    @Autowired
    MemoRepository memoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JasperService jasperService;
    @Override
    public Page<Memo> getAllMemos(int page, int size, Long id) {
        // developer gets all memos, seller gets memos of their own, others are same
        User user = userRepository.findById(id).orElseThrow();
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            return memoRepository.findAll(PageRequest.of(page, size));
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            return memoRepository.findBySeller(user, PageRequest.of(page, size));
        } else {
            return memoRepository.findByBuyer(user, PageRequest.of(page, size));
        }

    }

    @Override
    public MemoDto getMemo(Long id, Long userId) {
        boolean permission = false;
        User user = userRepository.findById(userId).orElseThrow();
        Memo memo = memoRepository.findById(id).orElseThrow();
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            permission = true;
        }
        if(memo.getSeller().getId().equals(user.getId())) {
            permission = true;
        }
        if(memo.getBuyer().getId().equals(user.getId())) {
            permission = true;
        }
        if(permission) {
            return new MemoDto(memo);
        } else {
            return null;
        }
    }

    @Override
    public MemoDto addMemo(NewMemoDto newMemoDto, Long id) {
        // Only Developer and Seller can add memos
        User user = userRepository.findById(id).orElseThrow();
        User buyer = null ;
        if(newMemoDto.getBuyerId() != null) {
            userRepository.findById(newMemoDto.getBuyerId()).orElse(null);
        }
        User seller = user;
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            List<Product> products = newMemoDto.getMemoItems().stream().map(memoItem -> productRepository.findById(memoItem.getProductId()).orElse(null)).toList();
            return new MemoDto(memoRepository.save(new Memo(newMemoDto,buyer,seller,products)));
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            List<Product> products = newMemoDto.getMemoItems().stream().map(memoItem -> productRepository.findById(memoItem.getProductId()).orElse(null)).toList();
            return new MemoDto(memoRepository.save(new Memo(newMemoDto,buyer,seller,products)));
        } else {
            return null;
        }
    }

    @Override
    public MemoDto updateMemo(Long id, MemoDto memoDto, Long userId) {
        //Only Developer and Seller can update memos
        User user = userRepository.findById(userId).orElseThrow();
        User buyer = userRepository.findById(memoDto.getBuyerId()).orElse(null);
        User seller = userRepository.findById(memoDto.getSellerId()).orElse(user);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            return new MemoDto(memoRepository.save(new Memo(memoDto,buyer,seller)));
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            return new MemoDto(memoRepository.save(new Memo(memoDto,buyer,seller)));
        } else {
            return null;
        }
    }

    @Override
    public void deleteMemo(Long id, Long id2) {
        // Only Developer and Seller can delete memos
        User user = userRepository.findById(id2).orElseThrow();
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            memoRepository.deleteById(id);
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            memoRepository.deleteById(id);
        }
    }

    @Override
    public Page<Memo> searchMemos(String name, int page, int size, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            return memoRepository.findByMemoNumberContainingOrBuyerNameContainingOrBuyerAddressContainingOrBuyerPhoneNumberContainingOrBuyerEmailContaining(name, name, name, name, name, PageRequest.of(page, size));
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            return memoRepository.findBySellerIdAndMemoNumberContainingOrBuyerNameContainingOrBuyerAddressContainingOrBuyerPhoneNumberContainingOrBuyerEmailContaining(user,name, name, name, name, name, PageRequest.of(page, size));
        } else {
            return null;
        }
    }

    @Override
    public Integer nextMemoNumber(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_DEV"))) {
            return memoRepository.findMaxMemoNumberBySeller(user).orElse(0) + 1;
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SELLER"))) {
            return memoRepository.findMaxMemoNumberBySeller(user).orElse(0) + 1;        
        } else {
            return null;
        }
    }

    @Override
    public MemoDto execute(NewMemoDto newMemoDto, Long id) {
        Boolean permission = true;
        User user = userRepository.findById(id).orElseThrow();
        User buyer = null ;
        if(newMemoDto.getBuyerId() != null) {
            userRepository.findById(newMemoDto.getBuyerId()).orElse(null);
        }
        User seller = user;
        List<Product> products = newMemoDto.getMemoItems().stream().map(memoItem -> productRepository.findById(memoItem.getProductId()).orElse(null)).toList();
        for (Product product : products) {
            if(product.getSeller().getId() != user.getId()) {
                permission = false;
                break;
            }
            if(product.getQuantity() < newMemoDto.getMemoItems().stream().filter(memoItem -> memoItem.getProductId().equals(product.getId())).findFirst().get().getQuantity()) {
                permission = false;
                break;
            }
        }
        if(permission) {
            
        } else {
            return null;
        }
        for (Product product : products) {
            int newQuantity = product.getQuantity() - newMemoDto.getMemoItems().stream().filter(memoItem -> memoItem.getProductId().equals(product.getId())).findFirst().get().getQuantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }
        return new MemoDto(memoRepository.save(new Memo(newMemoDto,buyer,seller,products)));
        
    }

    @Override
    public ResponseEntity<byte[]> print(NewMemoDto newMemoDto, Long id) throws JRException, IOException {
        User user = userRepository.findById(id).orElseThrow();
        User buyer = null ;
        if(newMemoDto.getBuyerId() != null) {
            userRepository.findById(newMemoDto.getBuyerId()).orElse(null);
        }
        User seller = user;
        List<Product> products = newMemoDto.getMemoItems().stream().map(memoItem -> productRepository.findById(memoItem.getProductId()).orElse(null)).toList();
        
        return jasperService.makeMemo(new Memo(newMemoDto,buyer,seller,products));
    }
    
}
