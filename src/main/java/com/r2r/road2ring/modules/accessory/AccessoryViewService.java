package com.r2r.road2ring.modules.accessory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryViewService {

  AccessoryService accessoryService;

  @Autowired
  public void setAccessoryService(AccessoryService accessoryService){
    this.accessoryService = accessoryService;
  }

  public AccessoryView bindAccessoryView(Accessory accessory){
    AccessoryView accessoryView = new AccessoryView();
    accessoryView.setId(accessory.getId());
    accessoryView.setTitle(accessory.getTitle());
    accessoryView.setDescription(accessory.getDescription());
    accessoryView.setDiscount(accessory.getDiscount());
    accessoryView.setPicture(accessory.getPicture());
    accessoryView.setPrice(accessory.getPrice());
    return accessoryView;
  }

  public List<AccessoryView> bindListAccessoryView(List<Accessory> accessories){
    List<AccessoryView> accessoryViews = new ArrayList<>();
    for(Accessory accessory : accessories){
      accessoryViews.add(this.bindAccessoryView(accessory));
    }
    return accessoryViews;
  }

  public List<AccessoryView> getListAccessories(Integer id){
    List<Accessory> accessories = accessoryService.getAllAccessoryByCategory(id);
    List<AccessoryView> result = this.bindListAccessoryView(accessories);
    return result;
  }

}
