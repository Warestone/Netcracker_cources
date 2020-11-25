package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae{
    private String resume_text = "", resume_text_backup = "";
    private boolean check_set = false;

    @Override
    public void setText(String text) {
        resume_text = text;
        resume_text_backup = text;
        check_set = true;
    }

    @Override
    public String getText() {
        if (resume_text==null || !check_set) throw new IllegalStateException();
        else return resume_text;
    }

    @Override
    public List<Phone> getPhones() {
        String resume_text_2 = getText();
        String region_code = "";
        String extension;
        List<Phone> phones = new ArrayList<>();
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Pattern pattern_2;
        Matcher matcher_2;
        Matcher matcher = pattern.matcher(resume_text_2);
        while (matcher.find())
        {
            String phone_str = matcher.group();
            String phone_str_rem = phone_str.replaceAll("[-()\\s]","");
            if (phone_str_rem.length()>7)// region code
            {
                pattern_2 = Pattern.compile("^(\\(?[1-9][0-9]{2}\\)?)");
                matcher_2 = pattern_2.matcher(phone_str);
                while (matcher_2.find())
                {
                    region_code = matcher_2.group();
                    region_code = region_code.replaceAll("[()]","");
                }
            }
            else region_code= "-1";
            if (phone_str.contains("ext"))
            {
                extension = phone_str.substring(phone_str.indexOf("ext")+3);
                extension = extension.replaceAll("[.\\s]","");
            }
            else extension="-1";
            Phone founded = new Phone(phone_str,Integer.parseInt(region_code),Integer.parseInt(extension));
            phones.add(founded);
        }
        return phones;
    }

    @Override
    public String getFullName() {
        String resume_text_2 = getText();
        resume_text_2 = resume_text_2.substring(0, resume_text_2.length()/3); // return 1/3 part of resume
        String full_name = "";
        Pattern pattern = Pattern.compile("\\s[A-Z][[a-z]?[.]?]+\\s[[A-Z][a-z]?[.]?]{2,}\\s([[A-Z][a-z]?[.]?]{2,}\\s)?");
        Matcher matcher = pattern.matcher(resume_text_2);
        while (matcher.find())
        {
            full_name = matcher.group();
            full_name = full_name.substring(1,full_name.length()-1);
            return full_name;
        }
       throw new NoSuchElementException();
    }

    @Override
    public String getFirstName() {
        String first_name = getFullName();
        first_name = first_name.substring(0, first_name.indexOf(" "));
        return first_name;
    }

    @Override
    public String getMiddleName() {
        String full_name = getFullName();
        String [] middle_name = full_name.split(" ");
        return middle_name[1];
    }

    @Override
    public String getLastName() {
        String full_name = getFullName();
        String [] last_name = full_name.split(" ");
        if (last_name.length<3) throw new NoSuchElementException();
        else return last_name[2];
    }

    @Override
    public void updateLastName(String newLastName) {
        if (newLastName.equals("")) return; //null exception for tests?
        String resume_text_2 = getText();
        String current_full_name = getFullName();
        String new_name = getFirstName()+" "+getMiddleName()+" "+newLastName;
        resume_text_2 = resume_text_2.replaceFirst(current_full_name,new_name);
        setText(resume_text_2);
    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) {
        if (oldPhone.getNumber().equals("") || newPhone.getNumber().equals("")) return; //null exception for tests?
        String resume_text_2 = getText();
        String oldPhone_str = oldPhone.getNumber();
        String newPhone_str = newPhone.getNumber();
        if (resume_text_2.contains(oldPhone_str)) resume_text_2 = resume_text_2.replaceFirst(oldPhone_str,newPhone_str);
        else throw new IllegalArgumentException();
        setText(resume_text_2);
    }

    @Override
    public void hide(String piece) {
        if (piece.equals("")) return; //null exception for tests?
        String resume_text_2 = getText();
        if (!resume_text_2.contains(piece)) throw new IllegalArgumentException();
        String crypt_piece = piece;
        crypt_piece = crypt_piece.replaceAll("[A-Za-z0-9\\[\\]'\";,!#$%&*()_+|=-]","X");
        resume_text_2 = resume_text_2.replace(piece,crypt_piece);
        resume_text = resume_text_2;
    }

    @Override
    public void hidePhone(String phone) {
        if (phone.equals("")) return; //null exception for tests?
        String resume_text_2 = getText();
        if (!resume_text_2.contains(phone)) throw new IllegalArgumentException();
        String crypt_phone = phone;
        crypt_phone = crypt_phone.replaceAll("[0-9]","X");
        resume_text_2 = resume_text_2.replace(phone,crypt_phone);
        resume_text = resume_text_2;
    }

    @Override
    public int unhideAll() {
        String resume_text_2 = getText();
        int counter = 0;
        Pattern pattern = Pattern.compile("\\s[ext.]?[(]?[X]+[)]?[-]?[.]?@?");
        Matcher matcher = pattern.matcher(resume_text_2);
        while (matcher.find())
        {
            String S = matcher.group();
            counter++;
        }
        resume_text = resume_text_backup;
        return counter;
    }
}
