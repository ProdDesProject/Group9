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

	private int nb_carbs = 0, nb_veggies = 0, nb_dairy = 0, nb_meat = 0, nb_cheese = 0;

	String name;
	String date;
	int[] portions;
	String type;
	Food[] list;

	String shopping;

	public Recipe(String type, String name, int[] portions, String date, Food[] list) {

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

				totVeggies = totVeggies + veggiesFruits[i]*0.3*portions[i];
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

		shopping = "You want to cook " + this.name + " on the " + this.date + ", you will need:\n\n";

		for(int j=0; j<list.length; j++) {
			if (list[j] instanceof Carbs) {
				nb_carbs ++;
			}
			if(list[j] instanceof Veggies) {
				nb_veggies ++;
			}
			if(list[j] instanceof MeatFishEggs) {
				nb_meat ++;
			}
			if(list[j] instanceof Dairy) {
				nb_dairy ++;
			}
			if(list[j] instanceof Cheese) {
				nb_cheese ++;
			}
		}

		for(int k=0; k<list.length; k++){
			if (list[k] instanceof Carbs) {
				if(nb_carbs!=0) {
					shopping = shopping + (totCarbs / nb_carbs) + " grams of " + list[k].name + ".\n";
				}
				else{
					shopping = shopping + "You don't have any carbs, you should add some to your meal.\n";
				}
			}
			if(list[k] instanceof Veggies) {
				if(nb_veggies!=0) {
					shopping = shopping + (totVeggies/nb_veggies) + " grams of " + list[k].name + ".\n";
				}
				else{
					shopping = shopping + "You don't have any fruit or vegetable, you should add some to your meal.\n";
				}
			}
			if(list[k] instanceof MeatFishEggs) {
				if(nb_meat!=0) {
					shopping = shopping + (totMeat/nb_meat) + " grams of " + list[k].toString() + ".\n";
				}
				else{
					shopping = shopping + "You don't have any meat, fish or egg, you should add some to your meal.\n";
				}
			}
			if(list[k] instanceof Dairy) {
				if(nb_dairy!=0) {
					shopping = shopping + (totDairy/nb_dairy) + " grams of " + list[k].toString() + ".\n";
				}
			}
			if(list[k] instanceof Cheese) {
				if(nb_cheese!=0) {
					shopping = shopping + (totCheese/nb_cheese) + " grams of " + list[k].toString() + ".\n";
				}
			}
		}

		return (shopping);

		}
}
