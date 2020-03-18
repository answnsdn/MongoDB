package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("commentdao")
public class ProductCommentDAOImpl implements ProductCommentDAO{
	@Autowired
	SqlSession sqlSession;
	@Override
	public List<ProductCommentDTO> commentlist(String prd_no) {
		return sqlSession.selectList("kr.multi.bigdataShop.product.comment.listall",prd_no);
	}

	@Override
	public int insert(ProductCommentDTO comment) {
		return sqlSession.insert("kr.multi.bigdataShop.product.comment.newcomment",comment);
	}

	@Override
	public List<CommentResultDTO> ranklist() {
		return sqlSession.selectList("kr.multi.bigdataShop.product.comment.ranklist");
	}
	

}
