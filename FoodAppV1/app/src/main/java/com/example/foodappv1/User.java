package com.example.foodappv1;

public class User {
	
	String name;
	
	public User(String n) {
		
		name = n;
		
	}
	
	public void info() {
		
		System.out.println( this.getName());
		
		
	}
	
	public String getName() {
		
		return this.name;
	}


}
