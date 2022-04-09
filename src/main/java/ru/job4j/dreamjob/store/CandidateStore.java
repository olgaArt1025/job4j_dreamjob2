package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {
    private final AtomicInteger CANDIDATE_ID = new AtomicInteger(3);
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

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
            candidate.setCreated(LocalDate.now());
        }
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(Integer id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidates.replace(candidate.getId(), candidate);
        candidate.setCreated(LocalDate.now());
    }
}
