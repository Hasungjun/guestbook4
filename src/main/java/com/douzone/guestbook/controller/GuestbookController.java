package com.douzone.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {

	private Long no;
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	

	@RequestMapping("")
	public String main(Model model) {
		model.addAttribute("list",guestbookDao.getList());
		return "index";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(GuestbookVo guestbookVo) {
		guestbookDao.insert(guestbookVo);
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") Long no) {
		this.no = no;
		return "deleteform";
	}
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(GuestbookVo guestbookVo) {
		guestbookVo.setNo(no);
		guestbookDao.delete(guestbookVo);
		
		return "redirect:/";
	}
	
}
