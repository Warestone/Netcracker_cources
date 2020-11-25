package ru.skillbench.tasks.javaapi.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void addChild() {
        TreeNodeImpl tree = new TreeNodeImpl();
        TreeNodeImpl child = new TreeNodeImpl();
        tree.addChild(child);
        int v = 0;
    }
}