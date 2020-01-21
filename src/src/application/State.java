package src.application;
public class State {
	
	public static final int State_INITAL = 1;
	public static final int State_NAME = 2;
	public static final int State_ANSWER = 3;
	public static final int State_PLAYER_ORDER = 4;
	public static final int State_CHOOSE_CATEGORY = 5;
	public static final int State_SHOW_CROSSWORD = 6;



	
	private int state = State_INITAL;
	
	
	public int getState() {   
		return state;
	} 
	
	public void setState(int state) {
		this.state = state;
	}
	

}
