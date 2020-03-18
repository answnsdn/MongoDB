package spring.data.mongodb.dto;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface ScoreRepository extends PagingAndSortingRepository<ScoreDTO, String> {

}
