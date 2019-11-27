package com.r2r.road2ring.modules.user;

import com.r2r.road2ring.modules.common.BaseView;
import java.util.Date;
import lombok.Data;

@Data
public class UserRequestRoleView extends BaseView {
  String name;
  String email;
  Boolean status;
  Date created;
  Date updated;
}
