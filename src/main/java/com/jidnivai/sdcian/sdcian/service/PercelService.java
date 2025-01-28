package com.jidnivai.sdcian.sdcian.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Percel;
import com.jidnivai.sdcian.sdcian.enums.PercelStatus;
import com.jidnivai.sdcian.sdcian.interfaces.PercelServiceInt;
import com.jidnivai.sdcian.sdcian.repository.PercelRepository;

@Service
public class PercelService implements PercelServiceInt {

	@Autowired
	private PercelRepository percelRepository;
	
	@Override
	public Page<Percel> getPercels(int page, int size) {
        return percelRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Percel getPercel(Long id) {
		Optional<Percel> percel = percelRepository.findById(id);
		return percel.orElse(null);
	}
	
	@Override
	public Percel createPercel(Percel percel) {
		return percelRepository.save(percel);
	}
	
	@Override
	public Percel updatePercel(Long id, Percel percel) {
		Percel percelDb = getPercel(id);
		if(percelDb != null) {
			percelDb.setName(percel.getName());
			percelDb.setDescription(percel.getDescription());
			percelDb.setWeight(percel.getWeight());
			percelDb.setCOD(percel.getCOD());
			percelDb.setDC(percel.getDC());
			percelDb.setDistance(percel.getDistance());
			percelDb.setImages(percel.getImages());
			percelDb.setSender(percel.getSender());
			percelDb.setPickupInstructions(percel.getPickupInstructions());
			percelDb.setPickupTime(percel.getPickupTime());
			percelDb.setReceiverName(percel.getReceiverName());
			percelDb.setRecieverPhone(percel.getRecieverPhone());
			percelDb.setRecieverAddress(percel.getRecieverAddress());
			percelDb.setDeliveryInstructions(percel.getDeliveryInstructions());
			percelDb.setDeliveryTime(percel.getDeliveryTime());
			percelDb.setStatus(percel.getStatus());
			percelDb.setRider(percel.getRider());
			return percelRepository.save(percelDb);
		}
		return null;
	}
	
	@Override
	public void deletePercel(Long id) {
		percelRepository.deleteById(id);
	}
	
	
	
	@Override
	public long count() {
		return percelRepository.count();
	}
	
	@Override
	public long countBySenderId(Long id) {
		return percelRepository.countBySenderId(id);
	}
	
	@Override
	public long countByRiderId(Long id) {
		return percelRepository.countByRiderId(id);
	}
	
	@Override
	public long countByNameContaining(String name) {
		return percelRepository.countByNameContaining(name);
	}
	
	@Override
	public long countByStatus(PercelStatus status) {
		return percelRepository.countByStatus(status);
	}
	
	@Override
	public long countBySenderIdAndStatus(Long id, PercelStatus status) {
		return percelRepository.countBySenderIdAndStatus(id, status);
	}
	
	@Override
	public long countByRiderIdAndStatus(Long id, PercelStatus status) {
		return percelRepository.countByRiderIdAndStatus(id, status);
	}

    @Override
    public Page<Percel> search(String name, int page, int size) {
        return percelRepository.findByNameContaining(name, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsBySender(Long id, int page, int size) {
        return percelRepository.findBySenderId(id, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsByRider(Long id, int page, int size) {
        return percelRepository.findByRiderId(id, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsByStatus(PercelStatus status, int page, int size) {
        return percelRepository.findByStatus(status, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsByStatusAndSender(PercelStatus status, Long id, int page, int size) {
        return percelRepository.findByStatusAndSenderId(status, id, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsByStatusAndRider(PercelStatus status, Long id, int page, int size) {
        return percelRepository.findByStatusAndRiderId(status, id, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsByStatusAndSenderAndRider(PercelStatus status, Long senderId, Long riderId, int page,
            int size) {
        return percelRepository.findByStatusAndSenderIdAndRiderId(status, senderId, riderId, PageRequest.of(page, size));
    }

    @Override
    public Page<Percel> getPercelsBySenderAndRider(Long senderId, Long riderId, int page, int size) {
        return percelRepository.findBySenderIdAndRiderId(senderId, riderId, PageRequest.of(page, size));
    }
	
}

