package engine;

import java.util.List;

public class RuleFollower extends GridObject {
	
	List<Statistic> myStats;
	List<Item> myItems;
	double mySpeed;
	double myDX;
	double myDY;

	public RuleFollower(double x, double y, double dx, double dy, double speed) {
		super(x,y);
		myStats = null;
		mySpeed = speed;
		myDX = dx;
		myDY = dy;
	}
	
	@Override
	public void move() {
		myX+=myDX;
		myY+=myDY;
	}
	
	public void addStatistic(Statistic stat) {
		myStats.add(stat);
	}
	
	public void addItem(Item it) {
		myItems.add(it);
	}
	
	public void removeItem(Item it) {
		for (Item current : myItems) {
			if (current.getName().equals(it.getName())) {
				myItems.remove(current);
			}
		}
	}
	
	
	

}