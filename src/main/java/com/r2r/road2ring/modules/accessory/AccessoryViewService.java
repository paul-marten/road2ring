package com.r2r.road2ring.modules.accessory;

import com.r2r.road2ring.modules.accessorycategory.AccessoryCategory;
import com.r2r.road2ring.modules.accessorycategory.AccessoryCategoryService;
import com.r2r.road2ring.modules.accessorycategory.AccessoryCategoryView;
import com.r2r.road2ring.modules.accessorycategory.AccessorySubCategory;
import com.r2r.road2ring.modules.accessorycategory.AccessorySubCategoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryViewService {

  AccessoryService accessoryService;

  AccessoryCategoryService accessoryCategoryService;

  @Autowired
  AccessorySubCategoryService accessorySubCategoryService;

  @Autowired
  public void setAccessoryService(AccessoryService accessoryService){
    this.accessoryService = accessoryService;
  }

  @Autowired
  public void setAccessoryCategoryService(AccessoryCategoryService accessoryCategoryService){
    this.accessoryCategoryService = accessoryCategoryService;
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

  public List<AccessoryView> getListAccessoriesByCategoryName(String title){
    Integer accessoryCategoryId = accessoryCategoryService.getAccessoryCategoryByTitle(title).getId();
    List<Accessory> accessories = accessoryService.getAllAccessoryByCategory(accessoryCategoryId);
    List<AccessoryView> result = this.bindListAccessoryView(accessories);
    return result;
  }

  public AccessoryCategoryView getListAccessoriesBySubCategory(Integer accessorySubCategoryId){
    AccessorySubCategory subCategory = accessorySubCategoryService.getAccessorySubCategroryById(accessorySubCategoryId);
    List<Accessory> accessories = accessoryService.getAllAccessoryBySubCategory(accessorySubCategoryId);
    AccessoryCategoryView result = new AccessoryCategoryView();

    result.setCategoryName(subCategory.getTitle());

    result.setAccessories(this.bindListAccessoryView(accessories));
    return result;
  }


  public AccessoryCategoryView getDummy(String subCategoryName){
    AccessoryCategoryView dummyResult = new AccessoryCategoryView();
    dummyResult.setCategoryName("Helmet");
    AccessoryView item = new AccessoryView();
    List<AccessoryView> result = new ArrayList<AccessoryView>();

    item = new AccessoryView();
    item.setId(1);
    item.setTitle("MDS");
    item.setDescription("helmet");
    item.setPrice(140000);
    item.setPicture("https://res.cloudinary.com/marfycarlo/image/upload/dummy/img/helmet-r2r-4.png");
    result.add(item);

    item = new AccessoryView();
    item.setId(2);
    item.setTitle("Arai");
    item.setDescription("helmet");
    item.setPrice(250000);
    item.setPicture("https://res.cloudinary.com/marfycarlo/image/upload/dummy/img/helmet-r2r-3.png");
    result.add(item);

    item = new AccessoryView();
    item.setId(3);
    item.setTitle("Ink");
    item.setDescription("helmet");
    item.setPrice(375000);
    item.setPicture("https://res.cloudinary.com/marfycarlo/image/upload/dummy/img/helmet-r2r-2.png");
    result.add(item);

    item = new AccessoryView();
    item.setId(4);
    item.setTitle("KYT");
    item.setDescription("helmet");
    item.setPrice(400000);
    item.setPicture("https://res.cloudinary.com/marfycarlo/image/upload/dummy/img/helmet-r2r-1.png");
    result.add(item);

    item = new AccessoryView();
    item.setId(5);
    item.setTitle("Arai");
    item.setDescription("helmet");
    item.setPrice(500000);
    item.setPicture("https://res.cloudinary.com/marfycarlo/image/upload/dummy/img/helmet-r2r-0.png");
    result.add(item);

    dummyResult.setAccessories(result);
    return dummyResult;
  }

}
