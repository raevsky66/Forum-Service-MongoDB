package telran.java55.post.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java55.post.dao.PostRepository;
import telran.java55.post.dto.CommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PeriodDto;
import telran.java55.post.dto.PostDto;
import telran.java55.post.dto.exceptions.PostNotFoundException;
import telran.java55.post.model.Comment;
import telran.java55.post.model.Post;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addNewPost(String author, NewPostDto newPostDto) {
		Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author, newPostDto.getTags());
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		String content = newPostDto.getContent();
		if (content != null) {
			post.setContent(content);
		}
		String title = newPostDto.getTitle();
		if (title != null) {
			post.setTitle(title);
		}
		Set<String> tags = newPostDto.getTags();
		if (tags != null) {
			tags.forEach(post::addTag);
		}
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String user) {
		return postRepository.findByAuthorIgnoreCase(user).map(s -> modelMapper.map(s, PostDto.class)).toList();
	
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findPostsByTags(tags).map(s -> modelMapper.map(s, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto period) {
		return postRepository.findPostsByPeriod(period).map(s -> modelMapper.map(s, PostDto.class)).toList();
	}

	@Override
	public PostDto addComment(String id, CommentDto newCommentDto) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		Comment comment = new Comment(newCommentDto.getUser(), newCommentDto.getMessage());
		post.addComment(comment);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public Integer addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.addLike();
		post = postRepository.save(post);
		return post.getLikes();
		
	}

}
