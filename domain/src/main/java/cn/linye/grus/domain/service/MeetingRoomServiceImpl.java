package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.MeetingRoomDto;
import cn.linye.grus.domain.entity.generated.MeetingRoomEntity;
import cn.linye.grus.domain.repository.MeetingRoomRepository;
import cn.linye.grus.domain.repository.generated.MeetingRoomMapper;
import cn.linye.grus.facade.model.admin.req.AddMeetingRoomReq;
import cn.linye.grus.facade.model.admin.req.QueryMeetingRoomReq;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2017/8/7.
 */
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    public PagedCollection<MeetingRoomDto> queryMeetingRoomList(QueryMeetingRoomReq queryMeetingRoomReq) {
        List<MeetingRoomEntity> deptEntities = meetingRoomRepository.queryMeetingRoomEntities(queryMeetingRoomReq.getRoomName(), queryMeetingRoomReq.getStart(), queryMeetingRoomReq.getLength());
        int count = meetingRoomRepository.countMeetingRoomEntities(queryMeetingRoomReq.getRoomName());
        PagedCollection<MeetingRoomDto> result = new PagedCollection<>();
        List<MeetingRoomDto> list = new ArrayList<>();
        deptEntities.stream().forEach(x -> {
            MeetingRoomDto dto = DozerUtils.getDozerMapper().map(x,MeetingRoomDto.class);
            list.add(dto);
        });
        result.setDraw(queryMeetingRoomReq.getDraw());
        result.setRecordsFiltered(count);
        result.setData(list);
        result.setRecordsTotal(count);
        return result;
    }

    public int updateMeetingRoomEnabled(int deptId, boolean enabled) {
        return 0;
    }

    public void addMeetingRoom(AddMeetingRoomReq addMeetingRoomReq) {

    }

    public MeetingRoomDto getMeetingRoom(int deptId) {
        return null;
    }

    public void editMeetingRoom(AddMeetingRoomReq addMeetingRoomReq) {

    }
}
