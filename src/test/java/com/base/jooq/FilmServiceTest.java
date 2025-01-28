package com.base.jooq;

import com.base.jooq.film.*;
import org.jooq.generated.tables.pojos.Film;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FilmServiceTest {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmRepositoryIsA filmRepositoryIsA;

    @Autowired
    FilmRepositoryHasA filmRepositoryHasA;

    @Autowired
    FilmRepository filmRepository;

    @DisplayName("Join으로 연관된 테이블의 데이터를 가져오는 테스트")
    @Test
    public void getFilmActorPageResponse() {
        List<FilmWithActor> filmActorPageResponse = filmRepository.getFilmActorPageResponse(1L, 10L);

        System.out.println(filmActorPageResponse);
    }

    @DisplayName("Jooq Dao를 상속받아 사용하는 테스트 - FilmRepositoryIsA")
    @Test
    public void getFilmActorPageResponseIsA() {
        List<Film> filmActorPageResponse = filmRepositoryIsA.fetchByJFilmId(1L);

        System.out.println(filmActorPageResponse);
    }

    @DisplayName("Jooq Dao를 생성자로 주입받아 사용하는 테스트 - FilmRepositoryHasA")
    @Test
    public void getFilmActorPageResponseHasA() {
        Film film = filmRepositoryHasA.findById(1L);

        System.out.println(film);
    }
}
