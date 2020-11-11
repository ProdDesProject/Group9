package foodApp;

public class main {

	public static void main(String[] args) {

		int[] portions = new int[]{2, 8, 5};			//portions[0] is the number of small portions, 1 => medium and 2 => large
		
		User Jorge = new User("Jorge", 21, "male");
		User Ouma = new User("Ouma", 21, "female");
		Jorge.info();
		Ouma.info();
		
		Dairy cheese = new Dairy();
		Carbs bread = new Carbs();
		MeatFishEggs ham = new MeatFishEggs();
		Veggies salad = new Veggies();
		Food[] list = new Food[4];
		
		list[0] = cheese;
		list[1] = bread;
		list[2] = ham;
		list[3] = salad;
		
		Recipe sandwich = new Recipe(list, "lunch", "sandwich", portions);
		
		sandwich.info(list);

	}

}
