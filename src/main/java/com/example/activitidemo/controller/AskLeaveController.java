package com.example.activitidemo.controller;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.model.Record;
import com.example.activitidemo.model.RecordDTO;
import com.example.activitidemo.model.TypeEnum;
import com.example.activitidemo.service.AskLeaveService;
import com.example.activitidemo.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/askLeave")
@RequiredArgsConstructor(onConstructor_=  @Autowired)
public class AskLeaveController extends BaseController {

    private final AskLeaveService askLeaveService;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final HistoryService historyService;

    private final RecordService recordService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<AskLeave> askLeaves = askLeaveService.findByUser(getUser())
                .stream().sorted(Comparator.comparing(AskLeave::getInTime).reversed())
                .collect(Collectors.toList());
        for (AskLeave askLeave : askLeaves) {
            Map<String, Object> map = new HashMap<>();
            map.put("askLeave", askLeave);
            // 查询历史实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceBusinessKey(String.valueOf(askLeave.getId())).singleResult();
            if (historicProcessInstance != null) {
                List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                        .processInstanceBusinessKey(String.valueOf(askLeave.getId())).list();
                if (!historicTaskInstances.isEmpty()) {
                    List<Comment> comments = new ArrayList<>();
                    for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
                        comments.addAll(taskService.getTaskComments(historicTaskInstance.getId()));
                    }
                    map.put("comments", comments);
                }
            }
            list.add(map);
        }
        model.addAttribute("askLeaves", list);
        model.addAttribute("user", getUser().getUsername());
        model.addAttribute("_type", TypeEnum.values());
        return "myAskLeaves";
    }

    @PostMapping("/add")
    @ResponseBody
    public void add(RecordDTO recordDTO) {
        Record record = new Record();
        BeanUtils.copyProperties(recordDTO, record);
        Record save = recordService.save(record);
//        askLeave.setUser(getUser());
//        askLeave.setInTime(new Date());
        AskLeave askLeave = new AskLeave();
        Record recordTemp = recordService.save(record);
        askLeave.setStatus(recordDTO.getStatus());
        askLeave.setDescription(recordDTO.getReason());
        askLeave.setInTime(new Date());
        askLeave.setRecordId(recordTemp.getId());
        askLeave.setTitle("申请"+TypeEnum.valueOf(recordDTO.getType()).getValue());
        askLeave.setType(recordDTO.getType());
        askLeave.setUser(getUser());
        askLeaveService.save(askLeave);
    }

    @PostMapping("/commit")
    @ResponseBody
    public Object commit(Integer id) {
        AskLeave askLeave = askLeaveService.findById(id);
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", getUser().getUsername());
        variables.put("pass", "1");
        // 启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("Test", String.valueOf(id), variables);
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("AskLeave", String.valueOf(id), variables);
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        // 增加批注
        Authentication.setAuthenticatedUserId(getUser().getUsername());
        taskService.addComment(task.getId(), instance.getId(), "[提交申请]");
        // 自己任务自动完成
        variables.put("username", askLeave.getUser().getLeader().getUsername());
        taskService.complete(task.getId(), variables);
        // 更改申请状态
        askLeave.setStatus("提交");
        askLeave.setTaskId(task.getId());
        askLeaveService.save(askLeave);
        return true;
    }

}
