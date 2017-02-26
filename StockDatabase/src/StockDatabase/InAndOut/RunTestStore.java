package StockDatabase.InAndOut;

public class RunTestStore {
	public static void main(String[] args){
		TestStore inAndOut = new TestStore();
		while(inAndOut.getDate()<=331){
			inAndOut.testDay();
		}
		inAndOut.printMonth();
	}
}
