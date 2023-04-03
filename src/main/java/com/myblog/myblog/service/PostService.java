package com.myblog.myblog.service;

import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {

    public PostDto getPostById(long id) ;


    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    public PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

    PostResponse getAllPosts1(int pageNo, int pageSize, String sortBy, String sortDir);
}
