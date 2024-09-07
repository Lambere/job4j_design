package ru.job4j;

import org.junit.jupiter.api.Test;
import ru.job4j.assertj.Box;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisBodyHas0Vertices() {
        Box box = new Box(0, 10);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(0);
    }

    @Test
    void isThisBodyHas4Vertices() {
        Box box = new Box(4, 10);
        int numbOfVertices = box.getNumberOfVertices();
        assertThat(numbOfVertices).isEqualTo(4);
    }

    @Test
    void isNotExist() {
        Box box = new Box(3, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void isExist() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void getAreaOfTetrahedron() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(173.20508075688772);
    }

    @Test
    void getAreaOfCube() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(600);
    }
}