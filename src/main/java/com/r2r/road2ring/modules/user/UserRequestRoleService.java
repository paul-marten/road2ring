package com.r2r.road2ring.modules.user;

import com.r2r.road2ring.modules.common.Road2RingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRequestRoleService {

  private static final int ROLE_ROAD_CAPTAIN = 3;

  @Autowired
  UserRequestRoleRepository userRequestRoleRepository;

  @Autowired
  UserService userService;

  public UserRequestRole save(User user) throws Exception{

    UserRequestRole saved = userRequestRoleRepository.findOneByUserIdAndStatus(user.getId(),true);
    if(saved == null) {
      saved.setUser(user);
      saved.setStatus(true);
      return userRequestRoleRepository.save(saved);
    }else{
      throw  new Road2RingException("You've already submit a request",200);
    }
  }

  public UserRequestRole update(UserRequestRole userRequestRole){
    userRequestRole = userRequestRoleRepository.findOne(userRequestRole.getId());
    User user = userService.changeRole(userRequestRole.getUser(), ROLE_ROAD_CAPTAIN);
    if(user != null){
      userRequestRole.setStatus(false);
    }
    return userRequestRoleRepository.save(userRequestRole);
  }
}
