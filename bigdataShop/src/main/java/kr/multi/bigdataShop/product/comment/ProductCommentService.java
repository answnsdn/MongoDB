package kr.multi.bigdataShop.product.comment;

import java.util.List;

public interface ProductCommentService {
	List<ProductCommentDTO> commentlist(String prd_no);
	List<CommentResultDTO> ranklist();
	int insert(ProductCommentDTO comment);
}
