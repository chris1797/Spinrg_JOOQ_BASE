package com.base.jooq.film;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.pojos.Film;
import org.jooq.generated.tables.pojos.FilmActor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FilmWithActor {

    private final Film film;
    private final FilmActor filmActor;
    private final Actor actor;

}
