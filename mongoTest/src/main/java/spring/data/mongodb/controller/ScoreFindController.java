package spring.data.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.data.mongodb.dto.ScoreDTO;
import spring.data.mongodb.service.ScoreMongoService;

@Controller
public class ScoreFindController {
@Autowired
ScoreMongoService service;

	@RequestMapping(value="/score/search",method=RequestMethod.GET)
	public String searchPage() {
		return "search";
	}
	@RequestMapping(value="/score/search",method=RequestMethod.POST)
	public ModelAndView search(String key,String opr,String value) {
		List<ScoreDTO> list = service.findCriteria(key+","+opr,value);
		
		return new ModelAndView("list", "mongolist", list);
	}
	//update�� read�� ���� �ʿ��� ��Ʈ�ѷ�
	@RequestMapping(value="/score/detail")
	public ModelAndView findById(String key, String value, String action) {
		// MongoDB�� �����Ͱ� key�� value�� ���ֱ� ������ �Ű������� key,value�� �������ش�
		String view="";
		if(action.equals("READ")) {
			view="mongo_detail";
		}else {
			view="mongo_update";
		}
		ScoreDTO doc = service.findById(key, value);
		System.out.println(doc.toString());
		return new ModelAndView(view, "document", doc);
	}
}
