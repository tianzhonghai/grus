package cn.linye.grus.admin.controllers;

import cn.linye.grus.admin.shiro.ShiroUser;
import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.domain.dtos.common.MeetingRoomDto;
import cn.linye.grus.domain.repository.DeptRepository;
import cn.linye.grus.domain.service.DeptService;
import cn.linye.grus.domain.service.MeetingRoomService;
import cn.linye.grus.facade.model.admin.req.AddDeptReq;
import cn.linye.grus.facade.model.admin.req.AddMeetingRoomReq;
import cn.linye.grus.facade.model.admin.req.QueryDeptsReq;
import cn.linye.grus.facade.model.admin.req.QueryMeetingRoomReq;
import cn.linye.grus.facade.model.admin.resp.DeptResp;
import cn.linye.grus.facade.model.admin.resp.MeetingRoomResp;
import cn.linye.grus.infrastructure.GeneralResp;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.exception.BizException;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 基础数据
 * Created by Tim on 2017/7/31.
 */
@Controller
@RequestMapping("/basic")
public class BasicController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private MeetingRoomService meetingRoomService;

    @RequestMapping("/deptlist")
    public String deptList(){
        return "basic/deptlist";
    }

    @RequestMapping("/getdeptlist")
    @ResponseBody
    public PagedCollection<DeptResp> getDeptList(QueryDeptsReq queryDeptsReq){
        PagedCollection<DeptResp> pagedCollection = new PagedCollection<>();
        PagedCollection<DeptDto> dtoPagedCollection = deptService.queryDeptList(queryDeptsReq);

        pagedCollection.setDraw(dtoPagedCollection.getDraw());
        pagedCollection.setRecordsFiltered(dtoPagedCollection.getRecordsFiltered());
        pagedCollection.setRecordsTotal(dtoPagedCollection.getRecordsTotal());
        pagedCollection.setData(DozerUtils.mapList(dtoPagedCollection.getData(),DeptResp.class));

        return pagedCollection;
    }

    @RequestMapping("/enabledept")
    @ResponseBody
    public GeneralResp<String> enableDept(@RequestParam("deptid") int deptId, @RequestParam("enabled") boolean enabled){
        GeneralResp<String> resp = new GeneralResp<>();
        deptService.updateDeptEnabled(deptId,enabled);
        return resp.success();
    }

    @RequestMapping("/adddept")
    public String addDept(){
        return "basic/deptadd";
    }

    @RequestMapping(value = "/adddept", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> addDept(@RequestBody  AddDeptReq addDeptReq){
        addDeptReq.doValidate();
        GeneralResp<String> generalResp = new GeneralResp<>();

        ShiroUser shiroUser = getCurrentUser();
        addDeptReq.setCreatedby(shiroUser.getAccount());
        addDeptReq.setLastmodifiedby(shiroUser.getAccount());
        try {
            deptService.addDept(addDeptReq);
        }catch (Exception ex){
            BizException.throwFail(ex.getMessage(), ex);
        }
        return generalResp;
    }

    @RequestMapping("/editdept")
    public String editDept(@RequestParam("deptid") int deptid, Model model){
        DeptDto dto = deptService.getDept(deptid);
        model.addAttribute("dept", dto);
        return "basic/deptedit";
    }

    @RequestMapping(value = "/editdept", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> editDept(@RequestBody AddDeptReq addDeptReq){
        GeneralResp<String> generalResp = new GeneralResp<>();
        if(addDeptReq.getDeptid() == null || addDeptReq.getDeptid() == 0){
            return  generalResp.fail();
        }

        ShiroUser shiroUser = getCurrentUser();
        addDeptReq.setLastmodifiedby(shiroUser.getAccount());
        deptService.editDept(addDeptReq);
        return generalResp.success();
    }

    @RequestMapping("/meetingroomlist")
    public String meetingRoomList(){
        return "basic/meetingroomlist";
    }

    @RequestMapping("/getmeetingroomlist")
    @ResponseBody
    public PagedCollection<MeetingRoomResp> getMeetingRoomList(QueryMeetingRoomReq queryMeetingRoomReq){
        PagedCollection<MeetingRoomResp> pagedCollection = new PagedCollection<>();
        PagedCollection<MeetingRoomDto> dtoPagedCollection = meetingRoomService.queryMeetingRoomList(queryMeetingRoomReq);

        pagedCollection.setDraw(dtoPagedCollection.getDraw());
        pagedCollection.setRecordsFiltered(dtoPagedCollection.getRecordsFiltered());
        pagedCollection.setRecordsTotal(dtoPagedCollection.getRecordsTotal());
        pagedCollection.setData(DozerUtils.mapList(dtoPagedCollection.getData(),MeetingRoomResp.class));

        return pagedCollection;
    }

    @RequestMapping("/addmeetingroom")
    public String addMeetingRoom(){
        return "basic/addmeetingroom";
    }

    @RequestMapping(value = "/addmeetingroom", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> addMeetingRoom(@RequestBody AddMeetingRoomReq addMeetingRoomReq){
        addMeetingRoomReq.doValidate();
        GeneralResp<String> generalResp = new GeneralResp<>();

        ShiroUser shiroUser = getCurrentUser();
        addMeetingRoomReq.setCreatedby(shiroUser.getAccount());
        addMeetingRoomReq.setLastmodifiedby(shiroUser.getAccount());
        try {
            meetingRoomService.addMeetingRoom(addMeetingRoomReq);
        }catch (Exception ex){
            BizException.throwFail(ex.getMessage(), ex);
        }
        return generalResp;
    }

    @RequestMapping("/editmeetingroom")
    public String editMeetingRoom(@RequestParam("meetingroomid") int meetingRoomId,Model model) {
        MeetingRoomDto dto = meetingRoomService.getMeetingRoom(meetingRoomId);
        model.addAttribute("mr", dto);
        return "basic/editmeetingroom";
    }

    @RequestMapping(value = "/editmeetingroom", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> editMeetingRoom(@RequestBody AddMeetingRoomReq addMeetingRoomReq) {
        GeneralResp<String> generalResp = new GeneralResp<>();
        if(addMeetingRoomReq.getMeetingroomid() == null || addMeetingRoomReq.getMeetingroomid() == 0){
            return  generalResp.fail();
        }

        ShiroUser shiroUser = getCurrentUser();
        addMeetingRoomReq.setLastmodifiedby(shiroUser.getAccount());
        meetingRoomService.editMeetingRoom(addMeetingRoomReq);
        return generalResp.success();
    }

    @RequestMapping("/enablemeetingroom")
    @ResponseBody
    public GeneralResp<String> enableMeetRoom(@RequestParam("roomid") int roomId, @RequestParam("enabled") boolean enabled){
        GeneralResp<String> resp = new GeneralResp<>();
        meetingRoomService.updateMeetingRoomEnabled(roomId, enabled);
        return resp.success();
    }

    private ShiroUser getCurrentUser(){
        Subject currentUser = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
        return shiroUser;
    }
}
