
public class ResourceSet {
	
	//public static final ResourceSet EMPTY = new ResourceSet();
	
	private int[] resources;
	
	public ResourceSet(int ro, int br, int tr, int sh, int co, int de ){
		resources = new int[6];
		resources[ResourceType.ROCK.toInt()] = ro;
		resources[ResourceType.BRICK.toInt()] = br;
		resources[ResourceType.TREE.toInt()] = tr;
		resources[ResourceType.SHEEP.toInt()] = sh;
		resources[ResourceType.CORN.toInt()] = co;
		resources[ResourceType.DESERT.toInt()] = de;
	}
	
	//public ResourceSet(int[] rset){
		 //this(rset[0], rset[1], rset[2], rset[3], rset[4], (rset.length >= 6) ? rset[5] : 0);
	//}
	
	public void clear(){
		for(int i = 0; i < resources.length; i++ ){
			resources[i] = 0;
		}
	}
	
	public int getAmount(int type){
		return resources[type];
	}
	
	public int getTotal(){
		int sum = 0;
		for(int i = 0; i < resources.length; i++){
			sum+=resources[i];
		}
		return sum;
	}
	
	
	public void setAmount(int amount, int type){
		resources[type] = amount;		
	}
	
	public void add(int amount, int type){
		resources[type] += amount;
	}
	
	public void subtract(int amount, int type){
		if(amount > resources[type]){
			resources[type] = 0;
		} else{
			resources[type] -= amount;
		}
	}
	public static void main(String[] args) {
		ResourceSet res = new ResourceSet(23,32,12,32,5,2);
		System.out.println("lol"+res.getAmount(0));
	}
}
