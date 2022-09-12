package com.gne.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
public class Person implements Serializable
{
    @Serial
    private static final long serialVersionUID = -4238320309780264419L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/DD/yyyy");
    @Getter
    public String name;
    @Getter
    public Date birthDate;
    @Getter
    public boolean gender;
    public Person father;
    public Person mother;

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
            return equals(person.father) || isAncestorOf(person.father) || equals(person.mother) || isAncestorOf(person.mother);
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o instanceof Person)
            return Objects.equals(name, ((Person) o).name)
                    && ((Person) o).birthDate == birthDate;
        return false;
    }
    public String toString() {
        return getName();
    }
}