package com.shawnbutton.learningHours.pureFunctions;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PureOrNotTest {

    @Test
    void example1_is_pure_function() {
        var sut = new PureOrNot();

        assertThat(sut.pure_or_not_1(99, 1)).isEqualTo(95);
        assertThat(sut.pure_or_not_1(1, 2)).isEqualTo(2);
        assertThat(sut.pure_or_not_1(0, -1)).isEqualTo(-1);
        assertThat(sut.pure_or_not_1(-1, -2)).isEqualTo(-2);
    }

    @Test
    void example1_does_not_mutate_parameter() {
        var sut = new PureOrNot();

        var firstParam = 1;
        sut.pure_or_not_1(firstParam, 2);
        assertThat(firstParam).isEqualTo(1);
    }

    @Test
    void example2_results_vary_depending_on_state() {
        var sut = new PureOrNot.PureOrNot2();

        assertThat(sut.pure_or_not_2(1)).isEqualTo("1, 1");
        sut.setX(2);
        assertThat(sut.pure_or_not_2(1)).isEqualTo("2, 1");
    }

    @Test
    void example3_is_pure_function() {
        var sut = new PureOrNot();

        assertThat(sut.pure_or_not_3("Bob")).isEqualTo("Hello, Bob");
        assertThat(sut.pure_or_not_3("")).isEqualTo("Hello, World!");
    }

    @Test
    void example4_is_not_pure_as_writes_to_console() throws IOException {
        //Redirect System.out to buffer
        var oldOut = System.out;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        var sut = new PureOrNot();

        sut.pure_or_not_4("Bob");

        bo.flush();
        assertTrue(bo.toString().contains("Bob"));
        System.setOut(oldOut);
    }

    @Test
    void example5_is_not_a_pure_function() {
        var sut = new PureOrNot.PureOrNot5();

        var total = new PureOrNot.Total(1);

        assertThat(sut.pure_or_not_5(total).total).isEqualTo(2);
        sut.setX(2);
        assertThat(sut.pure_or_not_5(total).total).isEqualTo(4);
    }

    @Test
    void example5_mutates_the_total() {
        var sut = new PureOrNot.PureOrNot5();

        var total = new PureOrNot.Total(1);

        sut.pure_or_not_5(total);
        assertThat(total.total).isEqualTo(2);
    };

    @Test
    void example6_is_not_a_pure_function() {
        var sut = new PureOrNot.PureOrNot6();

        var total = new PureOrNot.Total(1);

        assertThat(sut.pure_or_not_6(total, 2).total).isEqualTo(3);
    }

    @Test
    void example6_mutates_the_parameter() {
        var sut = new PureOrNot.PureOrNot6();

        var total = new PureOrNot.Total(1);

        sut.pure_or_not_6(total, 2);
        assertThat(total.total).isEqualTo(3);
    }

    @Test
    void example7_is_a_pure_function() {
        var sut = new PureOrNot.PureOrNot7();

        var total = new PureOrNot.Total(1);

        assertThat(sut.pure_or_not_7(total, 2).total).isEqualTo(3);
    }

    @Test
    void example7_does_not_mutate_the_parameter() {
        var sut = new PureOrNot.PureOrNot7();

        var total = new PureOrNot.Total(1);

        sut.pure_or_not_7(total, 2);
        assertThat(total.total).isEqualTo(1);
    }


    @Test
    void example8_is_a_pure_function() {
        var sut = new PureOrNot();

        assertThat(sut.pure_or_not_8(4)).isEqualTo(2);
    }

    @Test
    void example9_is_not_a_pure_function() {
        var oldIn = System.in;
        System.setIn(new ByteArrayInputStream("hello!".getBytes()));

        var sut = new PureOrNot();

        assertThat(sut.pure_or_not_9()).isEqualTo("They said: hello!");

        System.setIn(oldIn);
    }

}