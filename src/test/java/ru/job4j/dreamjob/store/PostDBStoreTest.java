package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(4, "Java Job");
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdateIdPost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job");
        store.add(post);
        Post post1 = new Post(post.getId(), "Java Job2");
        store.update(post1);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), equalTo(post1.getName()));
    }


    @Test
    public void whenFindAll() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job");
        Post post1 = new Post(0, "Java Job2");
        List<Post> first = store.findAll();
        store.add(post);
        store.add(post1);
        List<Post> second = store.findAll();
        assertThat((second.size() - first.size()), is(2));
    }
}
