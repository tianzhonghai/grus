package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.UserDto;
import cn.linye.grus.domain.entity.UserWithProfileEntity;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.facade.model.admin.req.AddUserReq;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;

/**
 * 用户服务
 * Created by Tim on 2017/7/16.
 */
public interface UserService {
    UserDto getUserEntityByAccount(String account);

    UserDto getUserEntityByUserId(int userId);

    PagedCollection<QueryUsersResp> queryUserList(QueryUsersReq queryUserReq);

    void lockUser(int userId,boolean locked);

    void enableUser(int userId,boolean enabled);

    void addUser(AddUserReq req);

    void editUser(AddUserReq req);

    UserWithProfileEntity getUserWithProfileEntity(int userid);
}
