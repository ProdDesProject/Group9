package foodApp;

public class main {

	public static void main(String[] args) {

		//portions[0] is the number of small portions, 1 => medium and 2 => large
		int[] portions = new int[]{2, 8, 5};

		//Creation of two new users
		User Jorge = new User("Jorge", 21, "male");
		User Ouma = new User("Ouma", 21, "female");
		//Print users infos
		Jorge.info();
		Ouma.info();

		//Creating new ingredients
		Dairy cheese = new Dairy();
		Carbs bread = new Carbs();
		MeatFishEggs ham = new MeatFishEggs();
		Veggies salad = new Veggies();

		//Adding the ingredients to a list
		Food[] list = new Food[4];
		list[0] = cheese;
		list[1] = bread;
		list[2] = ham;
		list[3] = salad;

		//Creating a new recipe with these ingredients and the portions entered by the user
		Recipe sandwich = new Recipe(list, "lunch", "sandwich", portions);

		sandwich.info(list);

	}

}
