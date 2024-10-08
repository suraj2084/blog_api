package com.suraj.blog_api.surajblogapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PageResponse;
import com.suraj.blog_api.surajblogapi.Payloads.PostDto;
import com.suraj.blog_api.surajblogapi.Services.FileService;
import com.suraj.blog_api.surajblogapi.Services.PostService;
import com.suraj.blog_api.surajblogapi.Utils.AppConstants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/")
public class PostConroller {

    @Autowired
    private FileService fileService;

    @Autowired
    PostService postService;
    @Value("${Project.image}")
    private String path;

    @PostMapping("user/{user_id}/category/{category_id}/posts")
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer user_id,
            @PathVariable Integer category_id) {
        ApiResponse CreatedPost = postService.createPost(postDto, user_id, category_id);
        return new ResponseEntity<ApiResponse>(CreatedPost, HttpStatus.CREATED);
    }

    @GetMapping("posts/")
    public ResponseEntity<?> getPosts(
            @RequestParam(defaultValue = AppConstants.PAGE_NO) Integer pageNo,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR) String sortDir) {
        PageResponse<PostDto> postdtos = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @GetMapping("category/{category_id}/posts")
    public ResponseEntity<?> getPostByCategory(@PathVariable Integer category_id) {
        List<PostDto> postdtos = postService.getPostByCategory(category_id);
        System.out.println("Category ID: " + category_id); // Debugging line
        if (postdtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @GetMapping("user/{user_id}/posts")
    public ResponseEntity<?> getPostByUser(@PathVariable Integer user_id) {
        List<PostDto> postdtos = postService.getPostByUser(user_id);
        if (postdtos.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Integer id, @RequestBody PostDto entity) {
        ApiResponse updatedPost = postService.updatePost(entity, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);

    }

    @DeleteMapping("posts/")
    public ResponseEntity<ApiResponse> deleteAllPost() {
        ApiResponse deleteAll = postService.deleteAll();
        return new ResponseEntity<>(deleteAll, HttpStatus.OK);

    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<ApiResponse> deletePostPostById(@PathVariable Integer id) {
        ApiResponse deleteById = postService.deleteById(id);
        return new ResponseEntity<>(deleteById, HttpStatus.OK);

    }

    @GetMapping("posts/search")
    public ResponseEntity<?> getPostSearch(@RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResponse<PostDto> postdtos = postService.searchPostsByContent(keyword, pageNo, pageSize);
        if (postdtos == null) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postdtos, HttpStatus.OK);
    }

    // Post Image upload
    @PostMapping("/post/image/upload/{postid}")
    public ResponseEntity<ApiResponse> uploadImage(@PathVariable Integer postid,
            @RequestParam("image") MultipartFile image) throws IOException {
        PostDto post = postService.getPostById(postid);
        String uploadImage = fileService.uploadImage(path, image);
        post.setP_imageUrl(uploadImage);
        postService.updatePost(post, postid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Image Inserted Successfully.", false),
                HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {

        InputStream is = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
    }

}
