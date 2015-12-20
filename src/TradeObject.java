
public class TradeObject {
	int[] has;
	int[] wants;
	int hasType;
	int wantsType;
	int initPlayer;
	int acceptPlayer;
	
	TradeObject(int[] has, int[] wants, int init, int acceptPlayer){
		this.has = has;
		this.wants = wants;
		this.initPlayer = init;
		this.hasType = has[0];
		this.wantsType = wants[0];
		this.acceptPlayer = acceptPlayer;
	}
}
