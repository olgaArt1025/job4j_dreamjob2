package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


class CandidateDBStoreTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Middle Java", "Spring Boot", LocalDate.now(), null);
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateIdCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Job", "Spring Boot", LocalDate.now(), null);
        store.add(candidate);
        Candidate candidate1 = new Candidate(candidate.getId(), "Java Job2", "Spring Boot", LocalDate.now(), null);
        store.update(candidate1);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), equalTo(candidate1.getName()));
    }

    @Test
    public void whenFindAll() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Junior Java", "Spring Boot", LocalDate.now(), null);
        Candidate candidate1 = new Candidate(0, "Middle Java", "Spring Boot", LocalDate.now(), null);
        List<Candidate> first = store.findAll();
        store.add(candidate);
        store.add(candidate1);
        List<Candidate> second = store.findAll();
        assertThat((second.size() - first.size()), is(2));
    }
}