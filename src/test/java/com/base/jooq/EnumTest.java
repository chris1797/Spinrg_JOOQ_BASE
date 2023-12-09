package com.base.jooq;

import com.base.jooq.component.role.BoardType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EnumTest {

    @Test
    void enumTest() {

        assertThat(BoardType.AD.getName()).isEqualTo("광고");
        assertThat(BoardType.AD.name()).isEqualTo("AD");
    }

}
