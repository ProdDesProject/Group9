package com.example.foodappv1;

import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;

public class Recipe extends AppCompatActivity{

	private double[] veggiesFruits = new double[]{ 400, 520, 575};		//veggiesFruits[0] = quantity in g for a small portion in a day
	private double[] carbs = new double[]{ 200, 300, 400};				//carbs[1] = quantity in g for a medium portion in a day
	private double[] dairy = new double[]{ 300, 345, 345};				//dairy[2] = quantity in g for a large portion in a day
	private double[] meatFishEggs = new double[]{ 300, 420, 550};

	private double totVeggies = 0, totCarbs = 0, totDairy = 0, totMeat = 0, totCheese = 0;

	String name;
	String date;
	int[] portions;
	String type;
	String[] list;

	public Recipe(String type, String name, int[] portions, String date, String[] list) {

		this.name = name;
		this.date = date;
		this.portions = portions;
		this.type = type;
		this.list = list;


		for (int i = 0; i < 3; i++) {

			if (type.equals("lunch")) {			//if the meal is a lunch

				//for each food type and each portion size, we add the recommended quantity for a
				//lunch multiplied by the number of people eating this portion size to the
				//previous total

				this.totVeggies = this.totVeggies + this.veggiesFruits[i]*0.3*portions[i];
				totCarbs = totCarbs + carbs[i]*0.4*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.35*portions[i];

			}

			if (type.equals("breakfast")) {		//if the meal is a breakfast

				totVeggies = totVeggies + veggiesFruits[i]*0.3*portions[i];
				totCarbs = totCarbs + carbs[i]*0.3*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.3*portions[i];

			}

			if (type.equals("dinner")) {		//if the meal is a dinner

				totVeggies = totVeggies + veggiesFruits[i]*0.4*portions[i];
				totCarbs = totCarbs + carbs[i]*0.3*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.35*portions[i];

			}
			totCheese = totCheese + 30*portions[i];
		}
	}

	public String info() {		//Will print the amount of food needed

			//Shopping list creation
			double[] shoppingList = new double[]{totVeggies, totCarbs, totDairy, totMeat, totCheese};

			return ("You want to cook " + this.name + " on the " + this.date + ", you will need:\n\n" + this.totVeggies + " grams of " + list[0] + ".\n" + totCarbs + " grams of " + list[1] + ".\n" + totDairy + " grams of " + list[2] + ".\n" + totMeat + " grams of " + list[3] + ".\n" + totCheese + " grams of " + list[4] + ".");

		}
}
