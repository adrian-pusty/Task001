package com.gne.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class Person implements Serializable
{
    @Serial
    private static final long serialVersionUID = -4238320309780264419L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/DD/yyyy");
    private static final int YEAR = 365 * 24 * 60 * 1000;

    @Getter
    public String name;
    @Getter
    public Date birthDate;
    @Getter
    public boolean gender;
    public Person father;
    public Person mother;

    public String getFormattedDateOfBirth()
    {
        return DATE_FORMAT.format(birthDate);
    }

    public int age()
    {
        long now = System.currentTimeMillis();
        return (int) ((now - birthDate.getTime()) / YEAR);
    }

    public boolean isAncestorOf(Person person)
    {
        return person != null && (isParent(person) || isAncestorOf(person.father) || isAncestorOf(person.mother));
    }

    private boolean isParent(Person person)
    {
        return equals(person.father) || equals(person.mother);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (o instanceof Person other)
        {
            return name.equals(other.name) && birthDate.equals(other.birthDate);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}