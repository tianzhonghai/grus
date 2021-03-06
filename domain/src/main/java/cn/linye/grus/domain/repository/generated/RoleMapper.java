package cn.linye.grus.domain.repository.generated;

import cn.linye.grus.domain.entity.generated.RoleEntity;
import cn.linye.grus.domain.entity.generated.RoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleEntityExample example);

    int deleteByExample(RoleEntityExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    List<RoleEntity> selectByExample(RoleEntityExample example);

    RoleEntity selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") RoleEntity record, @Param("example") RoleEntityExample example);

    int updateByExample(@Param("record") RoleEntity record, @Param("example") RoleEntityExample example);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}