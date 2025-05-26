package com.myblog.myblog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.myblog.payload.CommentDto;
import com.myblog.myblog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // http://localhost:8080/api/posts/1/comments

    //SAVE COMMENT FOR PARTICULAR POST
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment(

            @PathVariable("postId") long postId,
            @Valid
            @RequestBody CommentDto commentDto,
            BindingResult result){

//        if (result.hasErrors()){
//            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        if (result.hasErrors()){
            List<String> errors = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }

    //Get all Comments by postId

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId")
                                                Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //Get  Comment by COMMENT I'd

    //  http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value =
            "postId") Long postId,@PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

// Update comment using PostId and Comment id

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value ="postId") Long postId,
            @PathVariable(value = "id") Long commentId,
            @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(postId,
                commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);


    }





    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }





















}




