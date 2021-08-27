package machinery;


class Harvester extends Vehicle {
	public int graintank;
	public int maxgraintank;

	public Harvester(int x, int y, int maxfuel, int maxgraintank_) {
		super(x, y, maxfuel);
		graintank = 0;
		maxgraintank = maxgraintank_;

	}

	//public void mow(Grainfield field) {
	/*
	 * int grainamount = field.getGrainamount(); if (graintank + grainamount <=
	 * maxgraintank) { graintank += grainamount; field.mow(); } else {
	 * System.out.println("Tank full"); }
	 * 
	 * }
	 */

	/*public void empty(Silo silo) {
		int silox = silo.getX();
		int siloy = silo.getY();
		int silolevel = silo.getLevel();

		if (Math.pow((silox - x), 2) + Math.pow((siloy - y), 2) < 1) {
			try {
				silo.setLevel(silolevel + graintank);
				graintank = 0;
			} catch (TankFull e) {
				System.out.println("Tank full :(");
			}
		}*/
	//}

}

