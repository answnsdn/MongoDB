package spring.data.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoCursorNotFoundException;

import spring.data.mongodb.dto.ScoreDTO;
import spring.data.mongodb.dto.ScoreRepository;
@Repository
public class scoreMongoDAOImpl implements ScoreMongoDAO {
@Autowired
ScoreRepository scoreRepository;
@Autowired
MongoTemplate mongoTemplete;
	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		String[] data = key.split(",");
		Query query = new Query();
		
		/*Criteria criteria = new Criteria(key);*/
		if(key.equals("java")||key.equals("servlet")) {
			int val = Integer.parseInt(value);
			query.addCriteria(Criteria.where(key).is(val));
		}else {
			query.addCriteria(Criteria.where(key).regex(value));
		}
		/*Query query = new Query(criteria);*/
		List<ScoreDTO> mongolist = mongoTemplete.find(query, ScoreDTO.class, "score");
		return mongolist;
	
	}
/*	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		String[] data = key.split(",");
		Criteria criteria = new Criteria();
		criteria.andOperator(
				Criteria.where(data[0]).is(value),
				Criteria.where("addr").is("인천"));
		Query query = new Query(criteria);
		query.addCriteria(Criteria.where(data[0]).regex(value));
		Criteria criteria = new Criteria(key);
		if(key.equals("java")||key.equals("servlet")) {
			int val = Integer.parseInt(value);
			criteria.in(val);
		}else {
			criteria.in(value);
		}
		Query query = new Query(criteria);
		List<ScoreDTO> mongolist = mongoTemplete.find(query, ScoreDTO.class, "score");
		return mongolist;
	
	}*/
	//mongodb가 json으로 모든 작업을 처리하므로 key,value로 조건을 정의
	//spring-data-mongodb에서 이러한 조건을 처리하는 객체를 만들어서 제공
	//Criteria - 조건(정확하게 일치)
	@Override
	public ScoreDTO findById(String key, String value) {
		//1. 조건을 담을 수 있는 객체로 생성
		Criteria criteria = new Criteria(key);
		//2. 조건에 대한 설정 - value를 세팅한다.(정확하게 일치하는 조건이기 때문에 is사용)
		criteria.is(value);
		//3.criteria를 이용한 쿼리 객체를 생성 - mongodb 내부에서 인식할 조건을 정의하는 객체
		Query query = new Query(criteria);
		//4. MongoTemplate클래스의 메소드를 호출하면 Query객체를 매개변수로 전달
		ScoreDTO document = mongoTemplete.findOne(query, ScoreDTO.class, "score");
		return document;
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		mongoTemplete.insert(doc);
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		mongoTemplete.insertAll(docs);
	}

	@Override
	public void update(ScoreDTO document) {
		//1.수정할 document에 조건을 적용한다. - RDBMS의 Where절과 같은 역할
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		//2.수정될 데이터를 세팅 - RDBMS의 set절
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("dept", document.getDept());
		//3.MongoTemplate의 수정기능 호출
		mongoTemplete.updateMulti(query, update, "score");

	}


	@Override
	public List<ScoreDTO> findAll() {
		List<ScoreDTO> mongolist = (List<ScoreDTO>)scoreRepository.findAll();
		return mongolist;
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		Page<ScoreDTO> pageList = scoreRepository.findAll(new PageRequest(pageNo, 10));
		List<ScoreDTO> mongolist = pageList.getContent();
		return mongolist;
	}

}
