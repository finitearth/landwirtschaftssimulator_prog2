package Utils;
import java.util.ArrayList;
import buildings.Player;
import machinery.Vehicle;

public class CollisionChecker {
	ArrayList<ArrayList<Integer>> boundaries = new ArrayList<>();
	public static void collissionchecker() {
	}
	public int collisioncheckX(Player player, int d_x) { //check for collision: if it collides, bounce back
		int x = (int) player.getX()+d_x;
		int y = (int) player.getY();
		
		for (ArrayList<Integer> boundarie : boundaries) {
			int x1 = boundarie.get(0);
			int y1 = boundarie.get(1);
			int x2 = boundarie.get(2);
			int y2 = boundarie.get(3);
			
			if ((x1<=x)&&(x2>=x)&&(y1<=y)&&(y2>=y)) {
				player.setImageCollided();
				return -1*d_x;
			}
		}
		return d_x;
}
	public int collisioncheckX(Vehicle vehicle, int d_x) { //check for collision: if it collides, bounce back
		int x = (int) vehicle.getX()+d_x;
		int y = (int) vehicle.getY();
		
		for (ArrayList<Integer> boundarie : boundaries) {
			int x1 = boundarie.get(0);
			int y1 = boundarie.get(1);
			int x2 = boundarie.get(2);
			int y2 = boundarie.get(3);
			
			if ((x1<=x)&&(x2>=x)&&(y1<=y)&&(y2>=y)) {
//				vehicle.setImageCollided();
				return -1*d_x;
			}
		}
		return d_x;
}
	
	public int collisioncheckY(Player player, int d_y) {
		int x = (int) player.getX();
		int y = (int) player.getY()+d_y;
		
		for (ArrayList<Integer> boundarie : boundaries) {
			int x1 = boundarie.get(0);
			int y1 = boundarie.get(1);
			int x2 = boundarie.get(2);
			int y2 = boundarie.get(3);
			
			if ((x1<=x)&&(x2>=x)&&(y1<=y)&&(y2>=y)) {
				player.setImageCollided();
				return -1*d_y;
			}
		}
		return d_y;
}
	
	public int collisioncheckY(Vehicle vehicle, int d_y) {
		int x = (int) vehicle.getX();
		int y = (int) vehicle.getY()+d_y;
		
		for (ArrayList<Integer> boundarie : boundaries) {
			int x1 = boundarie.get(0);
			int y1 = boundarie.get(1);
			int x2 = boundarie.get(2);
			int y2 = boundarie.get(3);
			
			if ((x1<=x)&&(x2>=x)&&(y1<=y)&&(y2>=y)) {
//				vehicle.setImageCollided();
				return -1*d_y;
			}
		}
		return d_y;
}
	
	
	public void addboundary(int x1, int y1, int x2, int y2) {
		ArrayList<Integer> temparraylist =  new ArrayList<>();
		temparraylist.add(x1);
		temparraylist.add(y1);
		temparraylist.add(x2);
		temparraylist.add(y2);
		
		boundaries.add(temparraylist);
	}
}
