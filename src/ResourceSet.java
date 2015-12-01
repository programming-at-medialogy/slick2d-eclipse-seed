/**
 * Class for creating a set of resources for a game of settlers
 *
 */

public class ResourceSet {

	/**
	 * Creating resource array
	 */
	private int[] resources;

	/**
	 * Constructor for creating a full set of resources
	 * 
	 * @param ro = rock
	 * @param br = brick
	 * @param tr = tree
	 * @param sh = sheep
	 * @param co = corn
	 */
	public ResourceSet(int ro, int br, int tr, int sh, int co) {
		resources = new int[5];
		resources[ResourceType.ROCK.toInt()] = ro;
		resources[ResourceType.BRICK.toInt()] = br;
		resources[ResourceType.TREE.toInt()] = tr;
		resources[ResourceType.SHEEP.toInt()] = sh;
		resources[ResourceType.CORN.toInt()] = co;

	}

	/**
	 * clear the array of resources
	 */
	public void clear() {
		for (int i = 0; i < resources.length; i++) {
			resources[i] = 0;
		}
	}

	/**
	 * Returns the amount of a specific resource
	 * 
	 * @param type
	 *            = which type of resource you would like to see
	 * @return
	 */
	public int getAmount(int type) {
		return resources[type];
	}

	/**
	 * Returns the total amount of resources
	 * 
	 * @return
	 */
	public int getTotal() {
		int sum = 0;
		for (int i = 0; i < resources.length; i++) {
			sum += resources[i];
		}
		return sum;
	}

	/**
	 * Set a specific amount of resources in the array
	 * 
	 * @param amount
	 *            = amount added
	 * @param type
	 *            = which type is added
	 */
	public void setAmount(int amount, int type) {
		resources[type] = amount;
	}

	/**
	 * Add a specific type of resources to the array
	 * 
	 * @param amount
	 *            amount added
	 * @param type
	 *            which type is added
	 */
	public void add(int amount, int type) {
		resources[type] += amount;
	}

	/**
	 * Subtracts an amount of resources.
	 * 
	 * @param amount
	 *            subtracted
	 * @param type
	 *            which resource
	 */
	public void subtract(int amount, int type) {
		if (amount > resources[type]) {
			resources[type] = 0;
		} else {
			resources[type] -= amount;
		}
	}
}
