package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.generated.MeetingRoomEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tim on 2017/8/7.
 */
public interface MeetingRoomRepository {
    List<MeetingRoomEntity> queryMeetingRoomEntities(@Param("roomName") String roomName, @Param("start")int start, @Param("limit") int limit);

    int countMeetingRoomEntities(@Param("roomName") String roomName);

    int updateMeetingRoomEnabled(@Param("meetingRoomID") int meetingRoomID, @Param("enabled") boolean enabled);
}
