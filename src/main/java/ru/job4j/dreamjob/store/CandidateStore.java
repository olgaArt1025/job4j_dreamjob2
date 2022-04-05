package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Junior Java",
                "Знание SQL • Java • Java Spring Framework • Spring Boot • Git",
                LocalDate.of(2022, 3, 25)));
        candidates.put(2, new Candidate(2, "Middle Java",
                "Опыт разработки на Java (2 года). Опыт работы с базами данных SQL, Spring, Spring Boot, Maven, Hibernate",
                LocalDate.of(2022,4,1)));
        candidates.put(3, new Candidate(3, "Senior Java","Опыт разработки и проектирования приложений на Java 5+ лет."
                + "Знание Spring Core/Boot/MVC",
                LocalDate.of(2022, 4,2)));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
