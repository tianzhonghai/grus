package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.MeetingRoomDto;
import cn.linye.grus.facade.model.admin.req.AddMeetingRoomReq;
import cn.linye.grus.facade.model.admin.req.QueryMeetingRoomReq;
import cn.linye.grus.infrastructure.PagedCollection;

/**
 * Created by Tim on 2017/8/7.
 */
public interface MeetingRoomService {
    PagedCollection<MeetingRoomDto> queryMeetingRoomList(QueryMeetingRoomReq queryMeetingRoomReq);

    int updateMeetingRoomEnabled(int meetingRoomId, boolean enabled);

    void addMeetingRoom(AddMeetingRoomReq addMeetingRoomReq);

    MeetingRoomDto getMeetingRoom(int meetingRoomId);

    void editMeetingRoom(AddMeetingRoomReq addMeetingRoomReq);
}
