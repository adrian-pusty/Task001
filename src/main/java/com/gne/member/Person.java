package com.gne.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public final class Person implements Serializable
{
    @Serial
    private static final long serialVersionUID = -4238320309780264419L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/DD/yyyy");

    @Getter
    private final String name;
    @Getter
    private final LocalDate birthDate;
    @Getter
    private final boolean gender;
    private final Person father;
    private final Person mother;
    private String formattedDateOfBirth;

    public String getFormattedDateOfBirth()
    {
        if(formattedDateOfBirth == null)
        {
            formattedDateOfBirth = DATE_FORMAT.format(birthDate);
        }
        return formattedDateOfBirth;
    }

    public int age()
    {
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
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
}