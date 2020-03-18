package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ProductCommentServiceImpl implements ProductCommentService{
	@Autowired
	@Qualifier("commentdao")
	ProductCommentDAO dao;
	@Override
	public List<ProductCommentDTO> commentlist(String prd_no) {
		return dao.commentlist(prd_no);
	}
	
	@Override
	public int insert(ProductCommentDTO comment) {
		return dao.insert(comment);
	}

	@Override
	public List<CommentResultDTO> ranklist() {
		return dao.ranklist();
	}

	
	
}
