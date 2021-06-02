package com.jubble.app.utils;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
public class NumberNamesTest {
    @Test
    void shouldNotPrintDecimals() {
        assertThat(NumberNames.createString(10000.433d)).isEqualTo("10 Thousand");
    }

    @Test
    void shouldFormatBigDoubles() {
        assertThat(NumberNames.createString(45e34)).isEqualTo("450 Decillion");
    }

}
