package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	// service
	@Autowired
	private MemberService memberService;

	@GetMapping("kakaoLogout")
	public String kakaoLogout() {
		log.info("카카오 로그아웃 Controller");
		return "redirect:../";
	}
	
	@GetMapping("info")
	public void getInfo() throws Exception {
		// DB에서 사용자 정보를 조회 해서 JSP로 보냄
	}

	@GetMapping("update")
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO, Model model) throws Exception {
		// MemberVO memberVO = (MemberVO)principal;


//		  MemberVO memberVO = (MemberVO)session.getAttribute("member"); memberVO =
//		  memberService.getLogin(memberVO);
		   
	   	  MemberInfoVO memberInfoVO = new MemberInfoVO();
		  memberInfoVO.setName(memberVO.getName());
		  memberInfoVO.setBirth(memberVO.getBirth());
		  memberInfoVO.setEmail(memberVO.getEmail());
		 

		model.addAttribute("memberInfoVO", memberInfoVO);
	}

	@PostMapping("update")
	public String setUpdate(@Valid MemberInfoVO memberInfoVO, BindingResult bindingResult) throws Exception {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO memberVO = (MemberVO)obj;
		memberVO.setEmail("UpdateEmail@naver.com");
		return "redirect:/";
	}

	@GetMapping("logout")
	public String getLogout(HttpSession session) throws Exception {
		session.invalidate();

		return "redirect:../";
	}

	@GetMapping("login")
	public String getLogin(@ModelAttribute MemberVO memberVO) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();

		String check = context.getAuthentication().getPrincipal().toString();

		log.info("===============context : {} ==========" + context.getAuthentication().getPrincipal().toString());

		if (!check.equals("anonymousUser")) {
			return "redirect:/";
		}

		return "member/login";
	}

//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception{
//		memberVO = memberService.getLogin(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//			return "redirect:../";
//		}
//		
//		return "redirect:./login";
//	}

	/*
	 * @GetMapping("join") public void setJoin(Model model)throws Exception{
	 * MemberVO memberVO = new MemberVO(); model.addAttribute("memberV0", memberVO);
	 * }
	 */

	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO) throws Exception {

	}

	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile photo) throws Exception {

		boolean check = memberService.getMemberError(memberVO, bindingResult);

		if (bindingResult.hasErrors() || check) {
			return "member/join";
		}

		// 회원가입 진행
		int result = memberService.setJoin(memberVO);

		log.info("Photo : {} --- size : {}", photo.getOriginalFilename(), photo.getSize());
		return "redirect:../";
	}

}
