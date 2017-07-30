package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.RoleDto;
import cn.linye.grus.domain.entity.generated.RoleEntity;
import cn.linye.grus.domain.entity.generated.RoleEntityExample;
import cn.linye.grus.domain.entity.generated.RolePermissionEntity;
import cn.linye.grus.domain.entity.generated.RolePermissionEntityExample;
import cn.linye.grus.domain.repository.RoleRepository;
import cn.linye.grus.domain.repository.generated.RoleMapper;
import cn.linye.grus.domain.repository.generated.RolePermissionMapper;
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

import java.util.Date;
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
    private RolePermissionMapper rolePermissionMapper;

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

        RoleEntity roleEntity = DozerUtils.getDozerMapper().map(addRoleReq, RoleEntity.class);
        roleEntity.setCreatedtime(new Date());
        roleEntity.setIssystem(false);
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int rows = roleMapper.insert(roleEntity);
            for (int permissionId :addRoleReq.getPermissionIds()) {
                RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                rolePermissionEntity.setPermissionid(permissionId);
                rolePermissionEntity.setRoleid(roleEntity.getRoleid());
                rolePermissionMapper.insert(rolePermissionEntity);
            }
            sqlSession.commit();
        } catch (Exception ex) {
            sqlSession.rollback();
            BizException.throwFail(ex.getMessage(),ex);
        }finally {
            sqlSession.close();
        }
    }

    private SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = SpringUtils.getBean(SqlSessionFactoryBean.class);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception ex) {
            BizException.throwFail(ex.getMessage(),ex);
        }
        return sqlSessionFactory;
    }

    public RoleDto getRoleById(int roleId) {
        RoleEntity roleEntity = roleMapper.selectByPrimaryKey(roleId);
        return DozerUtils.getDozerMapper().map(roleEntity,RoleDto.class);
    }

    public void editRole(AddRoleReq addRoleReq) {
        if(addRoleReq.getRoleid() == null || addRoleReq.getRoleid() == 0) BizException.throwIllegalArgument("角色id不能为空");
        RoleEntity originalRoleEntity = roleMapper.selectByPrimaryKey(addRoleReq.getRoleid());
        if(originalRoleEntity == null) BizException.throwFail("角色不存在");
        if(originalRoleEntity.getIssystem()) BizException.throwFail("系统角色不能修改");

        RoleEntity roleEntity = DozerUtils.getDozerMapper().map(addRoleReq,RoleEntity.class);
        roleEntity.setIssystem(originalRoleEntity.getIssystem());
        roleEntity.setCreatedtime(originalRoleEntity.getCreatedtime());

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            RolePermissionEntityExample rolePermissionEntityExample = new RolePermissionEntityExample();
            rolePermissionEntityExample.createCriteria().andRoleidEqualTo(addRoleReq.getRoleid());
            rolePermissionMapper.deleteByExample(rolePermissionEntityExample);

            roleMapper.updateByPrimaryKey(roleEntity);
            for (int permissionId :addRoleReq.getPermissionIds()) {
                RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                rolePermissionEntity.setPermissionid(permissionId);
                rolePermissionEntity.setRoleid(roleEntity.getRoleid());
                rolePermissionMapper.insert(rolePermissionEntity);
            }
            sqlSession.commit();
        }catch (Exception ex){
            sqlSession.rollback();
            BizException.throwFail(ex.getMessage(),ex);
        }finally {
            sqlSession.close();
        }
    }

    public void deleteRole(int roleId) {
        if(roleId == 0)BizException.throwIllegalArgument("roleid不能为空");
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            RolePermissionEntityExample rolePermissionEntityExample = new RolePermissionEntityExample();
            rolePermissionEntityExample.createCriteria().andRoleidEqualTo(roleId);
            rolePermissionMapper.deleteByExample(rolePermissionEntityExample);

            RoleEntityExample roleEntityExample = new RoleEntityExample();
            roleEntityExample.createCriteria().andRoleidEqualTo(roleId);
            roleMapper.deleteByExample(roleEntityExample);

            sqlSession.commit();
        }catch (Exception ex){
            sqlSession.rollback();
            BizException.throwFail(ex.getMessage(),ex);
        }finally {
            sqlSession.close();
        }
    }

    public List<RoleDto> getAllRole() {
        List<RoleEntity> entities = roleMapper.selectByExample(null);
        return DozerUtils.mapList(entities,RoleDto.class);
    }
}
