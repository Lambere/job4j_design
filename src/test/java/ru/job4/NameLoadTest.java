package ru.job4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNameEmpty() {
        NameLoad nameload = new NameLoad();
        assertThatThrownBy(nameload::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkDoesNotContainSymbolEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] strArray = {"f"};
        assertThatThrownBy(() -> nameLoad.parse(strArray))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain the symbol '='", "f");

    }

    @Test
    void checkStartsWithSymbolEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] strArray = {"=a"};
        assertThatThrownBy(() -> nameLoad.parse(strArray))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain a key", "=a");

    }

    @Test
    void checkEndsWithSymbolEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] strArray = {"s="};
        assertThatThrownBy(() -> nameLoad.parse(strArray))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain a value", "s=");

    }

}