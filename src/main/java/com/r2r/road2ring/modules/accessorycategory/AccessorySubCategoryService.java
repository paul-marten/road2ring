package com.r2r.road2ring.modules.accessorycategory;

import com.r2r.road2ring.modules.common.PublishedStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessorySubCategoryService {

  @Autowired
  AccessorySubCategoryRepository accessorySubCategoryRepository;

  public List<AccessorySubCategory> findAllSubCategoryByAccessoryCategoryId(Integer accessoryCategoryId){
    List<AccessorySubCategory> result = accessorySubCategoryRepository
        .findAllByAccessoryCategoryIdAndStatus(accessoryCategoryId,PublishedStatus.PUBLISHED);

    return result;
  }

  public List<AccessorySubCategory> findAllDummySubCategory(Integer accessoryCategoryId){

    return this.generateDataDummy();
  }

  public AccessorySubCategory save(AccessorySubCategory accessorySubCategory){
    accessorySubCategory.setStatus(PublishedStatus.UNPUBLISHED);
    return accessorySubCategoryRepository.save(accessorySubCategory);
  }

  public AccessorySubCategory setPublishedStatus(AccessorySubCategory accessorySubCategory){
    AccessorySubCategory result = accessorySubCategoryRepository.findOne(accessorySubCategory.getId());
    if(result.getStatus().equals(PublishedStatus.PUBLISHED)){
      result.setStatus(PublishedStatus.UNPUBLISHED);
    }else{
      result.setStatus(PublishedStatus.PUBLISHED);
    }
    return accessorySubCategoryRepository.save(result);
  }

  private List<AccessorySubCategory> generateDataDummy(){
    List<AccessorySubCategory> result = new ArrayList<AccessorySubCategory>();
    AccessorySubCategory item;

    item = new AccessorySubCategory(1,"Helmet","http://lorempixel.com/768/432/technics/6/",PublishedStatus.PUBLISHED);
    result.add(item);
    item = new AccessorySubCategory(1,"Jacket","http://lorempixel.com/768/432/technics/7/",PublishedStatus.PUBLISHED);
    result.add(item);
    item = new AccessorySubCategory(1,"Goggles","http://lorempixel.com/768/432/technics/2/",PublishedStatus.PUBLISHED);
    result.add(item);
    item = new AccessorySubCategory(1,"T-Shirt","http://lorempixel.com/768/432/technics/4/",PublishedStatus.PUBLISHED);
    result.add(item);

    return result;

  }

}
