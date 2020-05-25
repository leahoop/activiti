package com.example.activitidemo;

import com.example.activitidemo.service.RecordService;
import com.example.activitidemo.utils.WordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ActivitiDemoApplication.class})// 指定启动类
public class ActivitiDemoApplicationTests {
	@Autowired
	private WordUtil wordUtil;

	@Autowired
	private RecordService recordService;

	@Test
	public void contextLoads() throws Exception {

//		Map<String, Object> dataMap;
//		Optional<Record> byId = recordService.findById(1);
//		if (byId.isPresent()) {
//			dataMap = wordUtl.objectToMap(byId);
//			wordUtl.ExportWord(dataMap);
//		}
	}

}
