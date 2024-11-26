package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithWrongFormat() {
        String path = "./data/pair_wrong_format.properties";
        Config config = new Config(path);
        config.load();
        assertThatException();
    }

    @Test
    void whenPairWithWrongFormat2() {
        String path = "./data/pair_wrong_format_2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr=");
    }

    @Test
    void whenPairWithWrongFormat3() {
        String path = "./data/pair_wrong_format_3.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr=1");
    }

}