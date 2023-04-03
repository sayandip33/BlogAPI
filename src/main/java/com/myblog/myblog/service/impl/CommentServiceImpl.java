package com.myblog.myblog.service.impl;

import com.myblog.myblog.entity.Comment;
import com.myblog.myblog.entity.Post;
import com.myblog.myblog.execption.BlogAPIException;
import com.myblog.myblog.execption.ResourceNotFoundException;
import com.myblog.myblog.payload.CommentDto;
import com.myblog.myblog.repository.CommentRepository;
import com.myblog.myblog.repository.PostRepository;
import com.myblog.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment=mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", postId)
        );
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
       CommentDto dto= mapToDto(newComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment ->
                mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () ->
                new ResourceNotFoundException("Comment", "id", commentId));
        if (comment.getPost().getId() != (post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");


        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));
        if(comment.getPost().getId()!=(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        comment.setId(commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {

            // retrieve post entity by id
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new ResourceNotFoundException("Post", "id", postId));
            // retrieve comment by id
            Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                    new ResourceNotFoundException("Comment", "id", commentId));

        if(comment.getPost().getId()!=(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentRepository.delete(comment);
    }


    private CommentDto mapToDto (Comment newComment){
        CommentDto dto = mapper.map(newComment, CommentDto.class);
//            CommentDto dto = new CommentDto();
//            dto.setId(newComment.getId());
//            dto.setName(newComment.getName());
//            dto.setEmail(newComment.getEmail());
//            dto.setBody(newComment.getBody());
           return dto;
        }

        private Comment mapToEntity (CommentDto commentDto){
            Comment comment = mapper.map(commentDto, Comment.class);
//            Comment comment = new Comment();
//            comment.setName(commentDto.getName());
//            comment.setEmail(commentDto.getEmail());
//            comment.setBody(commentDto.getBody());
            return comment;
        }
    }

