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
				Criteria.where("addr").is("��õ"));
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
	//mongodb�� json���� ��� �۾��� ó���ϹǷ� key,value�� ������ ����
	//spring-data-mongodb���� �̷��� ������ ó���ϴ� ��ü�� ���� ����
	//Criteria - ����(��Ȯ�ϰ� ��ġ)
	@Override
	public ScoreDTO findById(String key, String value) {
		//1. ������ ���� �� �ִ� ��ü�� ����
		Criteria criteria = new Criteria(key);
		//2. ���ǿ� ���� ���� - value�� �����Ѵ�.(��Ȯ�ϰ� ��ġ�ϴ� �����̱� ������ is���)
		criteria.is(value);
		//3.criteria�� �̿��� ���� ��ü�� ���� - mongodb ���ο��� �ν��� ������ �����ϴ� ��ü
		Query query = new Query(criteria);
		//4. MongoTemplateŬ������ �޼ҵ带 ȣ���ϸ� Query��ü�� �Ű������� ����
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
		//1.������ document�� ������ �����Ѵ�. - RDBMS�� Where���� ���� ����
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		//2.������ �����͸� ���� - RDBMS�� set��
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("dept", document.getDept());
		//3.MongoTemplate�� ������� ȣ��
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
