package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BasePagedReq;

/**
 * Created by Tim on 2017/8/7.
 */
public class QueryMeetingRoomReq extends BasePagedReq {
    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
