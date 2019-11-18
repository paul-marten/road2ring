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

  public AccessoryCategoryView getSubCategoryByCategory(Integer accessoryCategoryId){
    AccessoryCategory category = accessoryCategoryService.getAccessoryCategoryById(accessoryCategoryId);

    AccessoryCategoryView result = new AccessoryCategoryView();
    result.setCategoryName(category.getTitle());
    result.setSubCategories(accessorySubCategoryRepository.findAllByAccessoryCategoryIdAndStatus(category.getId(),PublishedStatus.PUBLISHED));
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


  public void changeStatus(PublishedStatus statusId, int id) throws Road2RingException {
    AccessorySubCategory save = accessorySubCategoryRepository.findOne(id);
    save.setStatus(statusId);
    accessorySubCategoryRepository.save(save);
  }
}
