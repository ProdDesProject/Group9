package com.example.foodappv1;

public class User {
	
	String name;
	int age;
	String gender;
	
	public User(String n, int a, String g) {
		
		name = n;
		age = a;
		gender = g;
		
	}
	
	public void info() {
		
		System.out.println( this.getName() + " is a " + this.getAge() + " year-old " + this.getGender() + ".");
		
		
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public int getAge() {
			
			return this.age;
		}
	
	public String getGender() {
		
		return this.gender;
	}

}
