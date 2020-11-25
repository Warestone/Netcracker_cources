package tricky_tasks_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateWorkerTest {

    @Test
    void countDays() {
        DateWorker dw = new DateWorker();
        int days = dw.countDays(2020,10,13);
        System.out.println(dw.toString(2020,5,9));
    }
}