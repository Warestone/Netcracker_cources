package ru.skillbench.tasks.text.regex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurriculumVitaeTest {

    @Test
    void setText() {
        String Text = "afey5e46 dasdw. Jonatan M. Waller 2 AASSd (800)-250-08-90 AASSd e56yjr (800)-250-08-90 67kg 495 926-93-47 ext.1846 hjkt78oyui, 68587tyu 59i 8i56 65i (916)125-4171"; //"(916)125-4171", "495 926-93-47 ext.1846", "800 250 0890"
        CurriculumVitaeImpl impl = new CurriculumVitaeImpl();
        impl.setText(Text); //Jonatan M. Waller
        //impl.getPhones();
        //String a = impl.getFullName();
        //impl.updateLastName("Dmitrievich");
        impl.hide("Jonatan M. Waller");
        impl.hidePhone("495 926-93-47 ext.1846");
        int Unhide = impl.unhideAll();
        return;
    }

    @Test
    void getText() {

    }

    @Test
    void getPhones() {
    }

    @Test
    void getFullName() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void getMiddleName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void updateLastName() {
    }

    @Test
    void updatePhone() {
    }

    @Test
    void hide() {
    }

    @Test
    void hidePhone() {
    }

    @Test
    void unhideAll() {
    }
}