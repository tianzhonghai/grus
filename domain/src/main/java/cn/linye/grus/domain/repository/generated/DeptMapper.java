package cn.linye.grus.domain.repository.generated;

import cn.linye.grus.domain.entity.generated.DeptEntity;
import cn.linye.grus.domain.entity.generated.DeptEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    long countByExample(DeptEntityExample example);

    int deleteByExample(DeptEntityExample example);

    int deleteByPrimaryKey(Integer deptid);

    int insert(DeptEntity record);

    int insertSelective(DeptEntity record);

    List<DeptEntity> selectByExample(DeptEntityExample example);

    DeptEntity selectByPrimaryKey(Integer deptid);

    int updateByExampleSelective(@Param("record") DeptEntity record, @Param("example") DeptEntityExample example);

    int updateByExample(@Param("record") DeptEntity record, @Param("example") DeptEntityExample example);

    int updateByPrimaryKeySelective(DeptEntity record);

    int updateByPrimaryKey(DeptEntity record);
}