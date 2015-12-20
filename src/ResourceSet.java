import java.util.ArrayList;
import java.util.Random;

/**
 * Class for creating a set of resources for a game of settlers
 *
 */

public class ResourceSet {

	/**
	 * Creating resource array
	 */
	//private int[] resources;
	
	public static ArrayList<ResourceType> resources;

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
		resources = new ArrayList();
		for (int i = 0; i < ro; i++) {
			resources.add(ResourceType.ROCK);
		}
		for (int i = ro; i < ro + br; i++) {
			resources.add(ResourceType.BRICK);
		}
		for (int i = ro + br; i < ro + br + tr; i++) {
			resources.add(ResourceType.TREE);
		}
		for (int i = ro + br + tr; i < ro + br + tr + sh; i++) {
			resources.add(ResourceType.SHEEP);
		}
		for(int i = ro + br + tr + sh; i < ro + br + tr + sh + co; i++){
			resources.add(ResourceType.CORN);
		}
		shuffle(resources, resources.size() - 1);
	}
	
	private static void shuffle(ArrayList<ResourceType> array, int length) {
		Random r = new Random();
		for (int i = length - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
			ResourceType temp = array.get(index);
			array.set(index, array.get(i));
			array.set(i, temp);
		}
	}


	/**
	 * Returns the amount of a specific resource
	 * 
	 * @param type
	 *            = which type of resource you would like to see
	 * @return
	 */
	public int getTotal() {
		return resources.size();
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
		resources.add(amount, ResourceType.fromInteger(type));
	}
	
}


	

