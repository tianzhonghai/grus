package cn.linye.grus.domain.repository.generated;

import cn.linye.grus.domain.entity.generated.UserProfileEntity;
import cn.linye.grus.domain.entity.generated.UserProfileEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserProfileMapper {
    long countByExample(UserProfileEntityExample example);

    int deleteByExample(UserProfileEntityExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(UserProfileEntity record);

    int insertSelective(UserProfileEntity record);

    List<UserProfileEntity> selectByExample(UserProfileEntityExample example);

    UserProfileEntity selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") UserProfileEntity record, @Param("example") UserProfileEntityExample example);

    int updateByExample(@Param("record") UserProfileEntity record, @Param("example") UserProfileEntityExample example);

    int updateByPrimaryKeySelective(UserProfileEntity record);

    int updateByPrimaryKey(UserProfileEntity record);
}