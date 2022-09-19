package com.gne.member;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Person implements Serializable
{
    @Serial
    private static final long serialVersionUID = -4238320309780264419L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/DD/yyyy");
    private static final int YEAR = 365 * 24 * 60 * 1000;

    @Getter
    private final String name;
    private final Date birthDate;
    @Getter
    private final boolean gender;
    private final Person father;
    private final Person mother;

    public Person(String name, Date birthDate, boolean gender, Person father, Person mother)
    {
        this.name = name;
        this.birthDate = new Date(birthDate.getTime());
        this.gender = gender;
        this.father = father;
        this.mother = mother;
    }

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
    public int hashCode()
    {
        return 31 * name.hashCode() * birthDate.hashCode();
    }

    @Override
    public String toString()
    {
        return getName();
    }

    public Date getBirthDate()
    {
        return new Date(birthDate.getTime());
    }
}