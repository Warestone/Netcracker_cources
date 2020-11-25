package ru.skillbench.tasks.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactCardImpl implements ContactCard {

    private String full_name, organization;
    private char gender;
    private Calendar b_day;
    private ArrayList<String> phones = new ArrayList<>();
    private ArrayList<String> addresses = new ArrayList<>();



    @Override
    public ContactCard getInstance(Scanner scanner) {
        phones.clear();
        addresses.clear();
        ArrayList<String> data_vcard = new ArrayList<>();
        boolean fn = false, org = false, begin = false, end = false, version = false, n = false, title = false, photo = false, email = false, rev = false, x_qq = false, bdayc = false, gender_c = false;

        scanner.useDelimiter("\\r\\n");
        while (scanner.hasNext())
        {
            data_vcard.add(scanner.next());
        }
        if (!data_vcard.get(0).equals("BEGIN:VCARD") || !data_vcard.get(data_vcard.size()-1).equals("END:VCARD")) throw new NoSuchElementException();
        for (String row: data_vcard) {
            if (row.indexOf(':')==-1) throw new InputMismatchException();
            switch (row.substring(0,row.indexOf(":")))
            {
                case "BEGIN":
                    if (!begin) begin = true;
                    else throw new InputMismatchException();
                    break;

                case "END":
                    if (!end) end = true;
                    else throw new InputMismatchException();
                    break;

                case "FN":
                    if (!fn) fn = true;
                    else throw new InputMismatchException();
                    if (row.length()<4) throw new NoSuchElementException();
                    full_name = row.substring(3);
                    break;

                case "GENDER":
                    if (!gender_c) gender_c = true;
                    else throw new InputMismatchException();
                    if (row.length()>8) throw new InputMismatchException();
                    gender= row.charAt(7);
                    if (gender!='F')
                    {
                        if (gender!='M')throw new InputMismatchException();
                    }
                    break;

                case "ORG":
                    if (!org) org = true;
                    else throw new InputMismatchException();
                    if (row.length()<5) throw new NoSuchElementException();
                    organization = row.substring(4);
                    break;

                case "BDAY":
                    if (!bdayc) bdayc = true;
                    else throw new InputMismatchException();
                    Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
                    Matcher matcher = pattern.matcher(row.substring(5));
                    if (!matcher.matches()) throw new InputMismatchException();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = null;
                    try {
                        date = formatter.parse(row.substring(row.indexOf(':')+1));
                    } catch (ParseException e) {
                        throw new InputMismatchException();
                    }
                    b_day = Calendar.getInstance();
                    b_day.setTime(date);
                    break;

                case "VERSION":
                    if (!version) version = true;
                    else throw new InputMismatchException();
                    break;

                case "N":
                    if (!n) n = true;
                    else throw new InputMismatchException();
                    break;

                case "TITLE":
                    if (!title) title = true;
                    else throw new InputMismatchException();
                    break;

                case "EMAIL":
                    if (!email) email = true;
                    else throw new InputMismatchException();
                    break;

                case "REV":
                    if (!rev) rev = true;
                    else throw new InputMismatchException();
                    break;

                case "x-qq":
                    if (!x_qq) x_qq = true;
                    else throw new InputMismatchException();
                    break;

                default:
                    if (row.substring(0,6).equals("PHOTO;")){       //PHOTO;MEDIATYPE=image/gif:http://www.example.com/dir_photos/my_photo.gif
                        if (!photo) photo = true;
                        else throw new InputMismatchException();
                        break;
                    }
                    if (row.substring(0,4).equals("TEL;"))                 //TEL;TYPE=WORK,VOICE:4951234567
                    {
                        if (row.length()-row.indexOf(':')<10) throw new InputMismatchException();
                        Pattern pattern_2 = Pattern.compile("\\d{10}");
                        Matcher matcher_2 = pattern_2.matcher(row.substring(row.indexOf(":")+1));
                        if (!matcher_2.matches()) throw new InputMismatchException();
                        phones.add(row);
                        break;
                    }
                    if (row.substring(0,4).equals("ADR;"))                 //ADR;TYPE=HOME;LABEL="42 Plantation St.\nBaytown\, LA 30314\nUnited States of America":;;42 Plantation St.;Baytown;LA;30314;United States of America
                    {
                        if (addresses.size()==0){
                            addresses.add(row);
                            break;
                        }
                        else{
                            for (String address:addresses) {
                                if (address.substring(9, address.indexOf(":")).equals(row.substring(9, address.indexOf(":")))) throw new InputMismatchException();
                            }
                            addresses.add(row);
                        }
                        break;
                    }
                    throw new InputMismatchException();
            }
        }
        if (!fn || !org) throw new NoSuchElementException();
        return this;
    }

    @Override
    public ContactCard getInstance(String data) {
        return getInstance(new Scanner(data));
    }

    @Override
    public String getFullName() {
        return full_name;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    @Override
    public boolean isWoman() {
        return gender == 'F';
    }

    @Override
    public Calendar getBirthday(){
        if (b_day==null) throw new NoSuchElementException();
        return b_day;
    }

    @Override
    public Period getAge() {
        Date input = b_day.getTime();
        LocalDate bdayLD= input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date_now = LocalDate.now();
        return Period.between(bdayLD,date_now);
    }

    @Override
    public int getAgeYears() {
        int a = getAge().getYears();
        return getAge().getYears();
    }

    @Override
    public String getPhone(String type) {
        if (phones.isEmpty())throw new NoSuchElementException();
        for (String phone:phones) {
            String phone_type = phone.substring(phone.indexOf("=")+1,phone.indexOf(":"));
            if (phone_type.equals(type))
            {
                String phone_str = phone.substring(phone.indexOf(":")+1);
                return phone_str.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            }
        }
        throw new NoSuchElementException();
    }
}
