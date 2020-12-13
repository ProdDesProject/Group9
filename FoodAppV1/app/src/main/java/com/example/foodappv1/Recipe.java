package com.example.foodappv1;

import java.net.CookieHandler;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class Recipe extends AppCompatActivity{

	private double[] veggiesFruits = new double[]{ 400, 520, 575};		//veggiesFruits[0] = quantity in g for a small portion in a day
	private double[] carbs = new double[]{ 200, 300, 400};				//carbs[1] = quantity in g for a medium portion in a day
	private double[] dairy = new double[]{ 300, 345, 345};				//dairy[2] = quantity in g for a large portion in a day
	private double[] meatFishEggs = new double[]{ 300, 420, 550};

	private double totVeggies = 0, totCarbs = 0, totDairy = 0, totMeat = 0, totCheese = 0;

	int nb_carbs = 0, nb_veggies = 0, nb_dairy = 0, nb_meat = 0, nb_cheese = 0;

	String name;
	String date;
	int[] portions;
	String type;
	Food[] list;
	ArrayList<Carbs> carbsList = new ArrayList<Carbs>(4);
	ArrayList<Veggies> veggieList = new ArrayList<Veggies>(6);
	ArrayList<MeatFishEggs> meatList = new ArrayList<MeatFishEggs>(4);
	ArrayList<Dairy> dairyList = new ArrayList<Dairy>(3);
	ArrayList<Cheese> cheeseList = new ArrayList<Cheese>(4);

	String shopping;
	String nomeat = "";
	String noveggies = "";
	String nocarbs = "";

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
				carbsList.add((Carbs) list[j]);
			}
			if(list[j] instanceof Veggies) {
				nb_veggies ++;
				veggieList.add((Veggies) list[j]);
			}
			if(list[j] instanceof MeatFishEggs) {
				nb_meat ++;
				meatList.add((MeatFishEggs) list[j]);
			}
			if(list[j] instanceof Dairy) {
				nb_dairy ++;
				dairyList.add((Dairy) list[j]);
			}
			if(list[j] instanceof Cheese) {
				nb_cheese ++;
				cheeseList.add((Cheese) list[j]);
			}
		}

		if(nb_carbs==0){
			nocarbs = "You don't have any carbs, you should add some to your meal.\n";
		}
		if(nb_meat==0){
			nomeat = "You don't have any meat, fish or egg, you should add some to your meal.\n";
		}
		if(nb_veggies==0){
			noveggies = "You don't have any veggie or fruit, you should add some to your meal.\n";
		}

		for(int k=0; k<list.length; k++){
			if (list[k] instanceof Carbs) {
				if(nb_carbs!=0) {
					for(int m = 0; m<nb_carbs; m++){
						shopping = shopping + (totCarbs / nb_carbs) + " grams of " + carbsList.get(m).name + ".\n";
						nb_carbs = 0;
					}
				}
			}
			if(list[k] instanceof Veggies) {
				if(nb_veggies!=0) {
					for(int l = 0; l<nb_veggies; l++){
						shopping = shopping + (totVeggies/nb_veggies) + " grams of " + veggieList.get(l).name + ".\n";
					}
					nb_veggies = 0;
				}
			}
			if(list[k] instanceof MeatFishEggs) {
				if(nb_meat!=0) {
					for (int n = 0; n < nb_meat; n++) {
						shopping = shopping + (totMeat / nb_meat) + " grams of " + meatList.get(n).name + ".\n";
					}
					nb_meat = 0;
				}
			}
			if(list[k] instanceof Dairy) {
				if(nb_dairy!=0) {
					for(int o = 0; o<nb_dairy; o++){
						shopping = shopping + (totDairy/nb_dairy) + " grams of " + dairyList.get(o).name + ".\n";
					}
					nb_dairy = 0;
				}
			}
			if(list[k] instanceof Cheese) {
				if(nb_cheese!=0) {
					for(int p = 0; p<nb_cheese; p++){
						shopping = shopping + (totCheese/nb_cheese) + " grams of " + cheeseList.get(p).name + ".\n";
					}
					nb_cheese = 0;
				}
			}
		}

		return (shopping + "\n" + nomeat + noveggies + nocarbs);

		}
}
