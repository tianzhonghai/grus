package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.QueryUserReq;

/**
 * 用户服务
 * Created by Tim on 2017/7/16.
 */
public interface UserService {
    UserEntity getUserEntityByAccount(String account);

    PagedCollectionResp<UserEntity> queryUserList(QueryUserReq queryUserReq);
}
