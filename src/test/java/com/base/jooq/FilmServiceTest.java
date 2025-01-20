package com.base.jooq;

import com.base.jooq.film.FilmRepository;
import com.base.jooq.film.FilmService;
import com.base.jooq.film.FilmWithActor;
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
    FilmRepository filmRepository;

    @Test
    @DisplayName("Join으로 연관된 테이블의 데이터를 가져오는 테스트")
    public void getFilmActorPageResponse() {
        List<FilmWithActor> filmActorPageResponse = filmRepository.getFilmActorPageResponse(1L, 10L);

        System.out.println(filmActorPageResponse);
    }
}
