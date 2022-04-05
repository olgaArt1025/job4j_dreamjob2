package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job",
                "Понимание базовых алгоритмов работы с массивами, коллекциями, поиском",
                LocalDate.of(2022, 4, 1)));
        posts.put(2, new Post(2, "Middle Java Job",
                "Опыт разработки не менее 1 года. Хорошее знание ООП. Реальный опыт test-driven development.",
                LocalDate.of(2022,3,28)));
        posts.put(3, new Post(3, "Senior Java Job","Опыт разработки от 3-х лет. Java, Spring Framework. REST. SQL. PostgreSQL.",
                LocalDate.of(2022, 4,4)));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
