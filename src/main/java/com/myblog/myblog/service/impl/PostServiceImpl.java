package com.myblog.myblog.service.impl;

import com.myblog.myblog.entity.Post;
import com.myblog.myblog.execption.ResourceNotFoundException;
import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.payload.PostResponse;
import com.myblog.myblog.repository.PostRepository;
import com.myblog.myblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto newDto=mapToDto(newPost);
        return newDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        //we will return as PostDto
        //thats why we are using StreamApi
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        postRepository.save(post);
        return mapToDto(post);
//        Post post1 = mapToEntity(postDto);
//        postRepository.save(post1);
//        return mapToDto(post1);
//       ata korle kaj hocche na new record save hoye jacche .....
    }

    @Override
    public void deletePostById(long id) {
        postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","Id",id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse getAllPosts1(int pageNo, int pageSize, String sortBy,String sortDir) {
    // public List<PostDto> getAllPosts1(int pageNo, int pageSize, String sortBy,String sortDir) {
        //Pageable pageable=PageRequest.of(pageNo, pageSize);
       // Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> contents = posts.getContent();
        List<PostDto> postDtos = contents.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setPageSize(posts.getSize());
        postResponse.setLast(posts.isLast());
        postResponse.setTotalElements(posts.getTotalElements());

        return postResponse;
    }

    private PostDto mapToDto(Post newPost) {
        PostDto postDto = mapper.map(newPost, PostDto.class);


//        PostDto postDto=new PostDto();
//        postDto.setId(newPost.getId());
//        postDto.setTitle(newPost.getTitle());
//        postDto.setContent(newPost.getContent());
//        postDto.setDescription(newPost.getDescription());
        return postDto;
    }

    private  Post mapToEntity(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);



//        Post post=new Post();
//        System.out.println(postDto.getTitle());
//        System.out.println(postDto.getDescription());
//        System.out.println(postDto.getContent());
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        return post;
    }
}


