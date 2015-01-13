package com.tedneward.example;

import java.beans.*;
import java.util.*;

//Akash Puri
//Android Dev 498 D
//Ted Neward
public class Person 
  implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s; 
    count++;
    ssn = "";
  }

  //Compares 2 different Person objects by each Person's age
  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person x, Person y) {
      return (x.getAge() - y.getAge());
    }
  }

  //Returns the count of the total number of Person instances that 
  //have been created
  public int count() {
    return count;
  }

  //Returns the Person objects age
  public int getAge() {
    return age;
  }

  //Sets the age of the person to a new int
  //If the int passed is less than zero, throws IllegalArgumentException
  public void setAge(int x) {
    if (x < 0) {
      throw new IllegalArgumentException("Can't set age to be less than 0");
    }
    int old = age;
    age = x;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "x", old, x));
  }
  
  //Returns Person's name
  public String getName() {
    return name;
  }

  //Sets the name of the person to a new string
  //If the string passed is null, throws IllealArumentException
  public void setName(String x) {
    if (x == null) {
      throw new IllegalArgumentException("Can't set name to null string");
    }
    String old = name;
    name = x;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "x", old, x));
  }
  
  //Returns the Person's salary
  public double getSalary() {
    return salary;
  }

  //Sets the Person's salary to a new double
  public void setSalary(double x) {
    double old = salary;
    salary = x;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "x", old, x));
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }


  public static ArrayList<Person> getNewardFamily() {
    Person Ted = new Person("Ted", 41, 250000.0);
    Person Charlotte = new Person("Charlotte", 43, 150000.0);
    Person Michael = new Person("Michael", 22, 10000.0);
    Person Matthew = new Person("Matthew", 15, 0.0);
    ArrayList<Person> x = new ArrayList<Person>();
    x.add(Ted);
    x.add(Charlotte);
    x.add(Michael);
    x.add(Matthew);
    return x;
  }
  
  //@Override
  //public boolean equals(Person other) {
  // return (this.name.equals(p.name) && this.age == p.age);
  //}

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person x = (Person) other;
       return (this.name == x.getName() && this.age == x.getAge());
    } else {
      return false;
    }
  }

  //Persons are comparable by salary where larger salaries are sorted to
  //be less than salaries that are smaller (150000 < 10000)
  @Override
  public int compareTo(Person other) {
    if (this.salary > other.salary) {
      return -1;
    } else if (this.salary < other.salary) {
      return 1;
    } else {
      return 0;
    }
  }

  //Returns a string representation of the Person object
  //Formatted as such: [Person name:Fird Birfle age:20 salary:195750.22]
  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
