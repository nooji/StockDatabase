package StockDatabase.InAndOut;

public class Ingredient {
	private int expDate;
	private int dayLeft;

	public Ingredient(int expDate){
		this.expDate = expDate;
		this.dayLeft = this.expDate;
	}
	
	public int getDayLeft(){
		return this.dayLeft;
	}
	public void setDayLeft(int num){
		dayLeft = num;
	}
	public void dayPassed(){
		dayLeft = dayLeft -1;
	}
}
