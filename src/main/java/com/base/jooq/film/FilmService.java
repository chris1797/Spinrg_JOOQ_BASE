package com.base.jooq.film;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public List<FilmWithActor> getFilmActorPageResponse(Long page, Long size) {
        return filmRepository.getFilmActorPageResponse(page, size);
    }
}
