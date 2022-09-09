package com.gne.member;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Person implements Serializable{
    private static final long serialVersionUID = -4238320309780264419L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/DD/yyyy");
    public String name;
    public Date birthDate;
    public boolean gender;
    public Person father;
    public Person mother;
    public Person(String name, Date birthdate, boolean gender, Person father, Person mother){
        this.name = name;
        this.birthDate = birthdate;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
    }
    public String getName() {
        return name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public boolean isGender() {
        return gender;
    }
    public String getFormattedDateOfBirth(){
        try{
            return DATE_FORMAT.format(birthDate);
        }catch (Throwable t){
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
    public int age() {
        long now = System.currentTimeMillis();
        return (int) ((now - birthDate.getTime()) / 365 / 24 / 60 / 1000);
    }
    public boolean isAncestorOf(Person person){
        if(person != null) {
            if(equals(person.father))
                return true;
            if(isAncestorOf(person.father)) {
                return true;
            }
            if(equals(person.mother))
                return true;
            if(isAncestorOf(person.mother)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o instanceof Person)
            return ((Person) o).name == name
                    && ((Person) o).birthDate == birthDate;
        return false;
    }
    public String toString() {
        return getName();
    }
}