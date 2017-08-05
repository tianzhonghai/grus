package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.generated.DeptEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tim on 2017/8/4.
 */
public interface DeptRepository {
    List<DeptEntity> queryDeptEntities(@Param("deptName") String deptName, @Param("start")int start, @Param("limit") int limit);

    int countDeptEntities(@Param("deptName") String deptName);
}
