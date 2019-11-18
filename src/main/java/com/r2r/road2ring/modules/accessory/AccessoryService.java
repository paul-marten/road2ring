package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService {
  AccessoryRepository accessoryRepository;

  @Autowired
  public void setAccessoryRepository(
      AccessoryRepository accessoryRepository) {
    this.accessoryRepository = accessoryRepository;
  }

  public Accessory getAccessoryById(int id){
    return accessoryRepository.findOne(id);
  }

  public Accessory saveAccessory(Accessory accessory){
    Accessory saved = new Accessory();

    if(accessory.getId() != null && accessory.getId() != 0){
      saved = getAccessoryById(accessory.getId());
    }else{
      saved.setStatus(PublishedStatus.UNPUBLISHED);
    }
    saved.setDescription(accessory.getDescription());
    saved.setDiscount(accessory.getDiscount());
    saved.setPicture(accessory.getPicture());
    saved.setPrice(accessory.getPrice());
    saved.setTitle(accessory.getTitle());
    saved.setAccessoryCategory(accessory.getAccessoryCategory());
    return accessoryRepository.save(saved);
  }

  public List<Accessory> getAllAccessory(){
    return accessoryRepository.findAll();
  }

  public List<Accessory> getAllAccessoryByCategory(Integer accessoryCategory){
    return accessoryRepository.findAllByAccessoryCategoryIdAndStatusOrderByIdAsc(accessoryCategory,PublishedStatus.PUBLISHED);
  }

  public List<Accessory> getAllAccessoryBySubCategory(Integer accessorySubCategory){
    return accessoryRepository.findAllByAccessorySubCategoryIdAndStatusOrderByIdAsc(accessorySubCategory,PublishedStatus.PUBLISHED);
  }

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    Accessory save = accessoryRepository.findOne(id);
    save.setStatus(statusId);
    accessoryRepository.save(save);
  }
}
