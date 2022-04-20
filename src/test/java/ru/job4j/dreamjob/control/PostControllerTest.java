package ru.job4j.dreamjob.control;

import org.junit.jupiter.api.Test;


import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpSession;

class PostControllerTest {

    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        HttpSession session = new MockHttpSession();
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "New post");
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(1, "New post");
        Post input = new Post(1, "New post2");
        PostService postService = mock(PostService.class);
        when(postService.findById(1)).thenReturn(input);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );

        String page = postController.updatePost(input);
        verify(postService).update(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    void whenFormUpdatePost() {
        Post post = new Post(1, "New post");
        Post input = new Post(1, "New post2");

        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findById(1)).thenReturn(input);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );

        String page = postController.formUpdatePost(model, 1);
        verify(model).addAttribute("post", post);
        assertThat(page, is("updatePost"));
    }
}