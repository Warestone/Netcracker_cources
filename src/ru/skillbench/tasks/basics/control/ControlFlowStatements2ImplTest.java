package ru.skillbench.tasks.basics.control;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlFlowStatements2ImplTest {

    @Test
    void calculateSportsman() {
        ControlFlowStatements2 controlFlowStatements2 = new ControlFlowStatements2Impl();
        float number = (float) 12.5;
        controlFlowStatements2.calculateSportsman(number);
    }
}