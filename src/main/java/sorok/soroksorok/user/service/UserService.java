package sorok.soroksorok.user.service;

import sorok.soroksorok.user.entity.User;

public interface UserService {

  User getUserEntityByNickname(String nickname);

}
