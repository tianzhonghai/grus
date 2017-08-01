package cn.linye.grus.domain.repository.generated;

import cn.linye.grus.domain.entity.generated.MeetingRoomEntity;
import cn.linye.grus.domain.entity.generated.MeetingRoomEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingRoomMapper {
    long countByExample(MeetingRoomEntityExample example);

    int deleteByExample(MeetingRoomEntityExample example);

    int deleteByPrimaryKey(Integer meetingroomid);

    int insert(MeetingRoomEntity record);

    int insertSelective(MeetingRoomEntity record);

    List<MeetingRoomEntity> selectByExample(MeetingRoomEntityExample example);

    MeetingRoomEntity selectByPrimaryKey(Integer meetingroomid);

    int updateByExampleSelective(@Param("record") MeetingRoomEntity record, @Param("example") MeetingRoomEntityExample example);

    int updateByExample(@Param("record") MeetingRoomEntity record, @Param("example") MeetingRoomEntityExample example);

    int updateByPrimaryKeySelective(MeetingRoomEntity record);

    int updateByPrimaryKey(MeetingRoomEntity record);
}