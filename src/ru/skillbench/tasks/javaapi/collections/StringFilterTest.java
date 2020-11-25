package ru.skillbench.tasks.javaapi.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFilterTest {

    @Test
    void getStringsByPattern() {
        StringFilterImpl sf = new StringFilterImpl();
        sf.add(null);
        sf.add("protection");
        sf.getStringsByPattern("pro*");
        int f = 0;
    }
}