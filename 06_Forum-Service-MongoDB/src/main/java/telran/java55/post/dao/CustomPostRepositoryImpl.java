package telran.java55.post.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java55.post.dto.PeriodDto;
import telran.java55.post.model.Post;

@Service
@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository{

	final MongoTemplate mongoTemplate;
	
	@Override
	public Stream<Post> findPostsByTags(List<String> tags) {
		Query query = new Query(); 
		query.addCriteria(Criteria.where("tags").in(tags)); 
		return mongoTemplate.find(query, Post.class).stream();
	}

	@Override
	public Stream<Post> findPostsByPeriod(PeriodDto period) {
		Query query = new Query(); 
		query.addCriteria(Criteria.where("dateCreated") 
				.gte(period.getDateFrom().atStartOfDay()) 
				.lte(period.getDateTo().atStartOfDay().plusDays(1).minusSeconds(1))); 
		return mongoTemplate.find(query, Post.class).stream();
	}

}
