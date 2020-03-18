package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.multi.bigdataShop.product.ProductDTO;

@Controller
public class ProductCommentController {
	@Autowired
	ProductCommentService service;
	
	//댓글 입력
	@RequestMapping(value="/comment/write.do", method=RequestMethod.POST)
	public String insert(ProductCommentDTO comment) {
		int result = service.insert(comment);
		String url = "redirect:/product/read.do?prd_no="+comment.getPrd_no();
		return url;
	}
	
	@RequestMapping("/comment/result.do")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView();
		List<CommentResultDTO> ranklist = service.ranklist();
		mav.addObject("ranklist", ranklist);
		mav.setViewName("comment/result");
		return mav;
	}

	
}
