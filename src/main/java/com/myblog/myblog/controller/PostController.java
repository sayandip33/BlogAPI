package com.myblog.myblog.controller;

import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.payload.PostResponse;
import com.myblog.myblog.service.PostService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
   public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

//        if (result.hasErrors())
//        {
//            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        if (result.hasErrors()){
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
       PostDto dto = postService.createPost(postDto);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
   }

//   @GetMapping
//   public List <PostDto> getAllPost(){
//       return postService.getAllPosts();
//   }

    /// URL SHOULD BE
    //  http://localhost:8080/api/posts?pageNo=0&pageSize=5
    //  http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title    (for sort)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    PostResponse getAllPost1(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
                             @RequestParam(value = "pageSize",defaultValue ="10",required = false)int pageSize,
                             @RequestParam(value ="sortBy",defaultValue = "id",required = false) String sortBy,
                             @RequestParam(value = "sortDir",defaultValue = "ASC",required = false) String sortDir
                                    ){
        return postService.getAllPosts1(pageNo,pageSize,sortBy,sortDir);
    }

   //http://localhost:8080/api/posts/1
   @GetMapping("/{id}")
   public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){
       PostDto postById = postService.getPostById(id);
       return ResponseEntity.ok(postById);
   }

   @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable("id")long id){
       PostDto postDto1 = postService.updatePostById(postDto, id);
       return ResponseEntity.ok(postDto1);
   }
    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Succesfully",HttpStatus.OK);
    }

}
