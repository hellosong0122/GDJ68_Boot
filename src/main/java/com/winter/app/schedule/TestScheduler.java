package com.winter.app.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestScheduler {

	@Autowired
	private NoticeDAO noticeDAO;
	//특정 주기로 반복
	//@Scheduled(fixedRateString = "2000", initialDelayString = "1000")//fixedRate 2000
	public void userFixRate()throws Exception{
		//메서드의 실행하고 종료일때까지 4초가 걸린다.
		log.info("===========Fixed Rate===========");
	
	}
	
	
	//@Scheduled(fixedDelay = 1000, initialDelay = 2000)
	public void useFixedDelay()throws Exception{
		
		log.info("===========Fixed Schedule===========");
	}
	
	@Scheduled(cron = "*/10 * * * * *")
	public void useCron()throws Exception{
		log.info("===========Fixed Schedule===========");
		Pager pager = new Pager();
		List<BoardVO> ar = noticeDAO.getList(pager);
		log.info("List : {} ", ar);
	}
	
}
