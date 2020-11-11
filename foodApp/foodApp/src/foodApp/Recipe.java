package foodApp;

public class Recipe {

	private double[] veggiesFruits = new double[]{ 400, 520, 575};		//veggiesFruits[0] = quantity in g for a small portion in a day
	private double[] carbs = new double[]{ 200, 300, 400};				//carbs[1] = quantity in g for a medium portion in a day
	private double[] dairy = new double[]{ 300, 345, 345};				//dairy[2] = quantity in g for a large portion in a day
	private double[] meatFishEggs = new double[]{ 300, 420, 550};

	private double totVeggies = 0, totCarbs = 0, totDairy = 0, totMeat = 0;

	String name;
	
	public Recipe(Food[] arrayFood, String type, String name, int[] portions) {

		this.name = name;
		for (int i = 0; i < 3; i++) {

			if (type == "lunch") {			//if the meal is a lunch

				//for each food type and each portion size, we add the recommended quantity for a
				//lunch multiplied by the number of people eating this portion size to the
				//previous total

				totVeggies = totVeggies + veggiesFruits[i]*0.3*portions[i];
				totCarbs = totCarbs + carbs[i]*0.4*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.35*portions[i];

			}

			if (type == "breakfast") {		//if the meal is a breakfast

				totVeggies = totVeggies + veggiesFruits[i]*0.3*portions[i];
				totCarbs = totCarbs + carbs[i]*0.3*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.3*portions[i];

			}

			if (type == "dinner") {		//if the meal is a dinner

				totVeggies = totVeggies + veggiesFruits[i]*0.4*portions[i];
				totCarbs = totCarbs + carbs[i]*0.3*portions[i];
				totDairy = totDairy + dairy[i]*0.33*portions[i];
				totMeat = totMeat + meatFishEggs[i]*0.35*portions[i];

			}
		}
	}

	public void info(Food[] arrayFood) {		//Will print the amount of food needed
			System.out.println("");
			System.out.println("To make a " + this.name + ", you will need:");
			System.out.println("");
			System.out.println(totVeggies + " grams of " + arrayFood[0]);
			System.out.println(totCarbs + " grams of " + arrayFood[1]);
			System.out.println(totDairy + " grams of " + arrayFood[2]);
			System.out.println(totMeat + " grams of " + arrayFood[3]);
		}

}
