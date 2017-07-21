package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.facade.model.PagedCollectionResp;

/**
 * 用户服务
 * Created by Tim on 2017/7/16.
 */
public interface UserService {
    UserEntity getUserEntityByAccount(String account);

    PagedCollectionResp<UserEntity> queryUserList(String account);
}
