
public class TradeObject {
	int[] has;
	int[] wants;
	int hasType;
	int wantsType;
	int initPlayer;
	int acceptPlayer;
	
	TradeObject(int[] has, int[] wants, int init){
		this.has = has;
		this.wants = wants;
		this.initPlayer = init;
	}
}
