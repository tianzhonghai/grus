package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.RoleDto;
import cn.linye.grus.domain.entity.generated.RoleEntity;
import cn.linye.grus.domain.entity.generated.RoleEntityExample;
import cn.linye.grus.domain.repository.RoleRepository;
import cn.linye.grus.domain.repository.generated.PermissionMapper;
import cn.linye.grus.domain.repository.generated.RoleMapper;
import cn.linye.grus.facade.model.admin.req.AddRoleReq;
import cn.linye.grus.facade.model.admin.req.QueryRolesReq;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.exception.BizException;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import cn.linye.grus.infrastructure.utils.SpringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public PagedCollection<RoleDto> queryRoleList(QueryRolesReq queryRolesReq) {
        RoleEntityExample roleEntityExample = new RoleEntityExample();
        roleEntityExample.createCriteria().andRolenameLike(queryRolesReq.getRolename());

        List<RoleEntity> roleEntities = roleRepository.queryRoleEntities(queryRolesReq.getRolename(), queryRolesReq.getStart(),queryRolesReq.getLength());
        long count = roleRepository.countRoleEntities(queryRolesReq.getRolename());

        PagedCollection<RoleDto> pagedCollection = new PagedCollection<>();
        pagedCollection.setData(DozerUtils.mapList(roleEntities,RoleDto.class));
        pagedCollection.setRecordsTotal((int)count);
        pagedCollection.setDraw(queryRolesReq.getDraw());
        pagedCollection.setRecordsFiltered((int)count);

        return pagedCollection;
    }

    public void addRole(AddRoleReq addRoleReq) {
        SqlSessionFactoryBean sqlSessionFactoryBean = SpringUtils.getBean(SqlSessionFactoryBean.class);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception ex) {
            BizException.throwFail(ex.getMessage(),ex);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int userId = roleMapper.insert(null);
            for (int roleId :addRoleReq.getPermissionIds()) {
                permissionMapper.insert(null);
            }

            sqlSession.commit();
        } catch (Exception ex) {
            sqlSession.rollback();
            BizException.throwFail(ex.getMessage(),ex);
        }
    }
}
