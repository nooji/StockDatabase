package StockDatabase.InAndOut;

import cs240.hw3.VectorList;
import cs240.Hw2.Vectorz;
import cs240.Hw2b.SingleLinked;
import java.util.Random;

public class TestStore {
	VectorList<String> menu; 
	Random rand = new Random();
	SingleLinked<Integer> customer; 
	Vectorz<Ingredient> cheese;
	Vectorz<Ingredient> bun;
	Vectorz<Ingredient> patty;
	Vectorz<Ingredient> lettuce;
	Vectorz<Ingredient> tomato;
	Vectorz<Ingredient> onion;
	int lostCustomerDay;
	int date;
	int countItemOne;
	int countItemTwo;
	int countItemThree;
	int countItemFour;
	int countItemFive;
	int countItemSix;
	int wasteCheese;
	int wasteBun;
	int wastePatty;
	int wasteLettuce;
	int wasteTomato;
	int wasteOnion;
	int totalLostCustomerDay;
	int totalCountItemOne;
	int totalCountItemTwo;
	int totalCountItemThree;
	int totalCountItemFour;
	int totalCountItemFive;
	int totalCountItemSix;
	int totalWasteCheese;
	int totalWasteBun;
	int totalWastePatty;
	int totalWasteLettuce;
	int totalWasteTomato;
	int totalWasteOnion;
	
	public TestStore(){
		lostCustomerDay = 0;
		menu = new VectorList<String>();
		customer = new SingleLinked<Integer>();
		cheese = new Vectorz<Ingredient>();
		bun = new Vectorz<Ingredient>();
		patty = new Vectorz<Ingredient>();
		lettuce = new Vectorz<Ingredient>();
		tomato = new Vectorz<Ingredient>();
		onion = new Vectorz<Ingredient>();
		date = 301;
		countItemOne=0;
		countItemTwo=0;
		countItemThree=0;
		countItemFour=0;
		countItemFive=0;
		countItemSix=0;
		wasteCheese=0;
		wasteBun=0;
		wastePatty=0;
		wasteLettuce=0;
		wasteTomato=0;
		wasteOnion=0;
		totalCountItemOne=0;
		totalCountItemTwo=0;
		totalCountItemThree=0;
		totalCountItemFour=0;
		totalCountItemFive=0;
		totalCountItemSix=0;
		totalWasteCheese=0;
		totalWasteBun=0;
		totalWastePatty=0;
		totalWasteLettuce=0;
		totalWasteTomato=0;
		totalWasteOnion=0;
	}
	private VectorList<String> createMenu(){
		VectorList<String> temp = new VectorList<String>();
		temp.addItem("Burger");
		temp.addItem("Cheese Burger");
		temp.addItem("Vegan Burger");
		temp.addItem("Burger No Onion");
		temp.addItem("Cheese Burger No Onion");
		temp.addItem("Burger No Tomato");
		return temp;
	}
	private SingleLinked<Integer> generateCustomers(){
		SingleLinked<Integer> temp = new SingleLinked<Integer>();
		
		int customerNum = rand.nextInt(100)+1;
		if(customerNum <=50){
			for(int i = 1; i<=customerNum;i++){
				temp.enqueue(i);
			}
		}
		if(customerNum > 50){
			for(int i = 1; i <= 50; i++){
				temp.enqueue(i);
			}
			lostCustomerDay += (100-customerNum);
		
		}
		return temp;
	}
	
	private void restock(){
		int temp = rand.nextInt(301)+700;
		System.out.println("***Restock " + temp + " per item***");
		for(int i =0; i < temp;i++ ){
			cheese.push(new Ingredient(2));
			bun.push(new Ingredient(5));
			patty.push(new Ingredient(4));
			lettuce.push(new Ingredient(3));
			tomato.push(new Ingredient(3));
			onion.push(new Ingredient(5));
		}
	}
	public void testDay(){
		lostCustomerDay = 0;
		countItemOne=0;
		countItemTwo=0;
		countItemThree=0;
		countItemFour=0;
		countItemFive=0;
		countItemSix=0;
		wasteCheese=0;
		wasteBun=0;
		wastePatty=0;
		wasteLettuce=0;
		wasteTomato=0;
		wasteOnion=0;
		if(date == 301){
			menu =createMenu();
		}
		if(date % 3 == 1){
			restock();
			reOrderRun();
		}
		for(int i = 0; i <10;i++){
			customer = generateCustomers();
			while(!customer.isEmpty()){
				orderFood();
				customer.dequeue();
			}
			customer.clear();
		}
		reOrderRun();
		passDay();
		tossExpire();
		totalLostCustomerDay += lostCustomerDay;
		totalCountItemOne+= countItemOne;
		totalCountItemTwo+= countItemTwo;
		totalCountItemThree+= countItemThree;
		totalCountItemFour+= countItemFour;
		totalCountItemFive+= countItemFive;
		totalCountItemSix+= countItemSix; 
		totalWasteCheese+= wasteCheese;
		totalWasteBun+= wasteBun;
		totalWastePatty+= wastePatty;
		totalWasteLettuce+= wasteLettuce;
		totalWasteTomato+= wasteTomato;
		totalWasteOnion+= wasteOnion;
		printDay();
		date+=1;

	}
	private void passDay(){
		for(int i = 0; i < cheese.getBag().size(); i++){
			cheese.getBag().elementAt(i).dayPassed();
		}
		for(int i = 0; i < bun.getBag().size(); i++){
			bun.getBag().elementAt(i).dayPassed();
		}
		for(int i = 0; i < patty.getBag().size(); i++){
			patty.getBag().elementAt(i).dayPassed();
		}
		for(int i = 0; i < lettuce.getBag().size(); i++){
			lettuce.getBag().elementAt(i).dayPassed();
		}
		for(int i = 0; i < tomato.getBag().size(); i++){
			tomato.getBag().elementAt(i).dayPassed();
		}
		for(int i = 0; i < onion.getBag().size(); i++){
			onion.getBag().elementAt(i).dayPassed();
		}
	}
	private void printDay(){
		System.out.println("Customers Lost on " + date + "				: " + lostCustomerDay);
		System.out.println("Cheese wasted					: " + wasteCheese);
		System.out.println("Bun wasted						: " + wasteBun);
		System.out.println("Patty wasted					: " + wastePatty);
		System.out.println("Lettuce wasted					: " + wasteLettuce);
		System.out.println("Tomato wasted					: " + wasteTomato);
		System.out.println("Onion wasted					: " + wasteOnion);
		System.out.println(menu.peekItem(0) + " ordered					: " + countItemOne);
		System.out.println(menu.peekItem(1) + " ordered				: " + countItemTwo);
		System.out.println(menu.peekItem(2) + " ordered				: " + countItemThree);
		System.out.println(menu.peekItem(3) + " ordered				: " + countItemFour);
		System.out.println(menu.peekItem(4) + " ordered		: " + countItemFive);
		System.out.println(menu.peekItem(5) + " ordered			: " + countItemSix);
		System.out.println();
	}
	public void printMonth(){
		System.out.println("Customers lost total			: " + totalLostCustomerDay);
		System.out.println("Cheese wasted total			: " + totalWasteCheese);
		System.out.println("Bun wasted total			: " + totalWasteBun);
		System.out.println("Patty wasted total			: " + totalWastePatty);
		System.out.println("Lettuce wasted total			: " + totalWasteLettuce);
		System.out.println("Tomato wasted total			: " + totalWasteTomato);
		System.out.println("Onion wasted total			: " + totalWasteOnion);
		System.out.println(menu.peekItem(0) + " ordered total			: " + totalCountItemOne);
		System.out.println(menu.peekItem(1) + " ordered total		: " + totalCountItemTwo);
		System.out.println(menu.peekItem(2) + " ordered total		: " + totalCountItemThree);
		System.out.println(menu.peekItem(3) + " ordered total		: " + totalCountItemFour);
		System.out.println(menu.peekItem(4) + " ordered total	: " + totalCountItemFive);
		System.out.println(menu.peekItem(5) + " ordered total		: " + totalCountItemSix);
		
	}
	
	
	private void orderFood(){
		switch(menu.peekItem(rand.nextInt(6))){
			case "Burger":
				if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty() && !onion.isEmpty()){
					bun.pop();
					patty.pop();
					lettuce.pop();
					tomato.pop();
					onion.pop();
					countItemOne+=1;
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			case "Cheese Burger":
				if(!bun.isEmpty() && !cheese.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty() && !onion.isEmpty()){
					bun.pop();
					cheese.pop();
					patty.pop();
					lettuce.pop();
					tomato.pop();
					onion.pop();
					countItemTwo+=1;
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			case "Vegan Burger":
				if(!lettuce.isEmpty()&& !tomato.isEmpty() && !onion.isEmpty()){
					int temp;
					temp = lettuce.peek().getDayLeft();
					lettuce.pop();
					if(!lettuce.isEmpty()){
						lettuce.pop();
						tomato.pop();
						onion.pop();
						countItemThree+=1;
					}
					else{
						lettuce.push(new Ingredient(temp));
						lostCustomerDay+=1;
					}
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			case "Burger No Onion":
				if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty()){
					bun.pop();
					patty.pop();
					lettuce.pop();
					tomato.pop();
					countItemFour+=1;
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			case "Cheese Burger No Onion":
				if(!bun.isEmpty() && !cheese.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !tomato.isEmpty()){
					bun.pop();
					cheese.pop();
					patty.pop();
					lettuce.pop();
					tomato.pop();
					countItemFive+=1;
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			case "Burger No Tomato":
				if(!bun.isEmpty() && !patty.isEmpty() && !lettuce.isEmpty() && !onion.isEmpty()){
					bun.pop();
					patty.pop();
					lettuce.pop();
					onion.pop();
					countItemSix+=1;
				}
				else{
					lostCustomerDay+=1;
				}
				break;
			default:
				break;
		}
	}
	private Vectorz<Ingredient> reOrder(Vectorz<Ingredient> temp){
		for(int i = 1; i < temp.getBag().size();i++){
			int j=i-1;
			while(j>=0 &&(temp.getBag().elementAt(j+1).getDayLeft() > temp.getBag().elementAt(j).getDayLeft())){
				
				int tempNum = temp.getBag().elementAt(j+1).getDayLeft();
				temp.getBag().elementAt(j+1).setDayLeft(temp.getBag().elementAt(j).getDayLeft());
				temp.getBag().elementAt(j).setDayLeft(tempNum);
				j-=1;
			}
		}
		return temp;
	}
	
	private void reOrderRun(){
		cheese = reOrder(cheese);
		bun = reOrder(bun);
		patty = reOrder(patty);
		lettuce = reOrder(lettuce);
		tomato = reOrder(tomato);
		onion = reOrder(onion);
	}
	
	private void tossExpire(){
		while(!cheese.isEmpty()&&cheese.peek().getDayLeft() <= 0){
			cheese.pop();
			wasteCheese+=1;
		}
		while(!bun.isEmpty()&&bun.peek().getDayLeft() <= 0){
			bun.pop();
			wasteBun+=1;
		}
		while(!patty.isEmpty()&&patty.peek().getDayLeft() <= 0){
			patty.pop();
			wastePatty+=1;
		}
		while(!lettuce.isEmpty() &&lettuce.peek().getDayLeft() <= 0){
			lettuce.pop();
			wasteLettuce+=1;
		}
		while(!tomato.isEmpty()&&tomato.peek().getDayLeft() <= 0){
			tomato.pop();
			wasteTomato+=1;
		}
		while(!onion.isEmpty()&&onion.peek().getDayLeft() <= 0){
			onion.pop();
			wasteOnion+=1;
		}
	}
	public int getDate(){
		return date;
	}
	
}
