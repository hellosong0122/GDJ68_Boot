package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
//	model.addAttribute("board","notice") 모든메서드에 포함되어있음	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO,Model model)throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);		
		return "fileDownView";
	}
	
	//ModelAndView, void, String
	
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		//ERROR, WARN경고, INFO, DEBUG, TRACE추적(정보량 제일 많음)
		log.error("getList 실행");
		return "board/list";
	}
	
	@GetMapping("add")
	public String add()throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String addTest(NoticeVO noticeVO, MultipartFile[] files)throws Exception{
		//log.info("NoticeVO : {}", noticeVO);
		log.info("files:{}", files[0].getOriginalFilename());
		int result = noticeService.add(noticeVO, files);
		
		return "redirect:./list";
	}
	
	/*@GetMapping("detail")
	public ModelAndView getDetail(BoardVO boardVO, ModelAndView mv)throws Exception{
		
		boardVO = noticeService.getDetail(boardVO);
		mv.addObject("dto",boardVO);
		mv.setViewName("board/detail");
		
		return mv;
	}*/
	
	@GetMapping("detail")
	public String getDetail(NoticeVO noticeVO,Model model)throws Exception{
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		model.addAttribute("boardVO",boardVO);
		return "board/detail";
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv) throws Exception{
		boardVO = noticeService.getDetail(boardVO);
		mv.addObject("dto",boardVO);
		mv.setViewName("board/update");
		
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO) throws Exception{
		noticeService.setUpdate(boardVO);
				
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO)throws Exception{
			
		return "redirect:./list";
	}
	
	
}
