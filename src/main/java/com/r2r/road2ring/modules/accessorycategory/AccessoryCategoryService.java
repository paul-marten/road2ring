package com.r2r.road2ring.modules.accessorycategory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryCategoryService {

  AccessoryCategoryRepository accessoryCategoryRepository;

  @Autowired
  public void setAccessoryCategoryRepository(AccessoryCategoryRepository accessoryCategoryRepository){
    this.accessoryCategoryRepository = accessoryCategoryRepository;
  }

  public List<AccessoryCategory> getAllAccessoryCategory(){
    return accessoryCategoryRepository.findAll();
  }

  public AccessoryCategory saveAccessoryCategory(AccessoryCategory accessoryCategory){
    AccessoryCategory saved = new AccessoryCategory();
    if(accessoryCategory.getId() != 0 && accessoryCategory.getId() != null){
      saved = accessoryCategoryRepository.findOne(accessoryCategory.getId());
    }
    saved.setImage(accessoryCategory.getImage());
    saved.setTitle(accessoryCategory.getTitle());
    return accessoryCategoryRepository.save(saved);
  }

  public AccessoryCategory getAccessoryCategoryById(int id){
    return accessoryCategoryRepository.findOne(id);
  }

  public AccessoryCategory getAccessoryCategoryByTitle(String title){
    return accessoryCategoryRepository.findOneByTitleIgnoreCase(title);
  }

  public List<AccessoryCategory> getAccessoryCategories(){
    return accessoryCategoryRepository.findAllByOrderByIdAsc();
  }

  public List<AccessoryCategory> getAccessoryCategoriesIdNotContaionHelm(){
    List<AccessoryCategory> result = accessoryCategoryRepository.findAllByTitleIgnoreCaseNotLike("helm");
    return result;
  }

}
