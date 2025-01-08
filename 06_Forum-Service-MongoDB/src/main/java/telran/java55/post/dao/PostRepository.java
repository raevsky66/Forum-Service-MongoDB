package telran.java55.post.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java55.post.dto.PeriodDto;
import telran.java55.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String>, CustomPostRepository{

	Stream<Post> findByAuthorIgnoreCase(String user);

	


}
