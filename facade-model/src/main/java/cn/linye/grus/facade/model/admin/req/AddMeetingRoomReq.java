package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by Tim on 2017/8/7.
 */
public class AddMeetingRoomReq extends BaseReq {
    private Integer meetingroomid;
    @NotBlank(message = "会议室名称不能为空")
    private String roomname;
    private Date lastmodifiedtime;
    private String lastmodifiedby;
    private Date createdtime;
    private String createdby;

    public Integer getMeetingroomid() {
        return meetingroomid;
    }

    public void setMeetingroomid(Integer meetingroomid) {
        this.meetingroomid = meetingroomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public String getLastmodifiedby() {
        return lastmodifiedby;
    }

    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
}
