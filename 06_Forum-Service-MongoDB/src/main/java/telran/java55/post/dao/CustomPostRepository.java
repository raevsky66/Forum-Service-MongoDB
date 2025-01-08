package telran.java55.post.dao;

import java.util.List;
import java.util.stream.Stream;

import telran.java55.post.dto.PeriodDto;
import telran.java55.post.dto.PostDto;
import telran.java55.post.model.Post;


public interface CustomPostRepository {
	Stream<Post> findPostsByTags(List<String> tags);
	Stream<Post> findPostsByPeriod(PeriodDto period);
}
