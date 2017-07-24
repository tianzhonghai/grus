package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.AddUserReq;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;

/**
 * 用户服务
 * Created by Tim on 2017/7/16.
 */
public interface UserService {
    UserEntity getUserEntityByAccount(String account);

    UserEntity getUserEntityByUserId(int userId);

    PagedCollectionResp<QueryUsersResp> queryUserList(QueryUsersReq queryUserReq);

    void lockUser(int userId,boolean locked);

    void enableUser(int userId,boolean enabled);

    void addUser(AddUserReq req);
}
