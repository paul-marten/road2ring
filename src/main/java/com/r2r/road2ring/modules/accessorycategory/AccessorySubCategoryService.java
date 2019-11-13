package com.r2r.road2ring.modules.accessorycategory;

import com.r2r.road2ring.modules.common.PublishedStatus;
import com.r2r.road2ring.modules.common.Road2RingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessorySubCategoryService {

  @Autowired
  AccessorySubCategoryRepository accessorySubCategoryRepository;

  @Autowired
  AccessoryCategoryService accessoryCategoryService;

  public List<AccessorySubCategory> findAllSubCategoryByAccessoryCategoryId(Integer accessoryCategoryId){
    List<AccessorySubCategory> result = accessorySubCategoryRepository
        .findAllByAccessoryCategoryIdAndStatus(accessoryCategoryId,PublishedStatus.PUBLISHED);

    return result;
  }


  public List<AccessorySubCategory> findAllSubCategory(){
    return accessorySubCategoryRepository.findAll();
  }
  public AccessorySubCategory getOneByName(String name){
    return accessorySubCategoryRepository.findAllByTitleIgnoreCaseNotLikeAndStatus(name,PublishedStatus.PUBLISHED);
  }

  public AccessoryCategoryView findAllDummySubCategory(Integer accessoryCategoryId){

    AccessoryCategoryView result = new AccessoryCategoryView();
    result.setCategoryName("Safety");
    result.setSubCategories(this.generateDataDummy());
    return result;
  }

  public AccessorySubCategory save(AccessorySubCategory accessorySubCategory){
    AccessoryCategory category = accessoryCategoryService.getAccessoryCategoryById(accessorySubCategory.getAccessoryCategory().getId());
    accessorySubCategory.setAccessoryCategory(category);
    accessorySubCategory.setStatus(PublishedStatus.UNPUBLISHED);
    return accessorySubCategoryRepository.save(accessorySubCategory);
  }

  public AccessorySubCategory getAccessorySubCategroryById(Integer accessorySubCategoryId){
    return accessorySubCategoryRepository.findOne(accessorySubCategoryId);

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

  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    AccessorySubCategory save = accessorySubCategoryRepository.findOne(id);
    save.setStatus(statusId);
    accessorySubCategoryRepository.save(save);
  }
}
