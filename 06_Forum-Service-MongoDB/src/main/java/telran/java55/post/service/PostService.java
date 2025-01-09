package telran.java55.post.service;

import java.util.List;

import telran.java55.post.dto.CommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PeriodDto;
import telran.java55.post.dto.PostDto;

public interface PostService {

	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	List<PostDto> findPostsByAuthor(String user);

	List<PostDto> findPostsByTags(List<String> tags);

	List<PostDto> findPostsByPeriod(PeriodDto period);

	PostDto addComment(String id, CommentDto newCommentDto);

	Integer addLike(String id);

	

	

}
