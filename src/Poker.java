package jp.ac.hal.jv93.poker;



public class Poker {
	public static void main(String[] args) {
		Deck	d	= new Deck();
		int		turn= 1000000;
		int		round=1;

		long t1;
		long t2 = System.currentTimeMillis();
		for(int i=0; i<round; i++){
			t1 = System.currentTimeMillis();
			for(int j=0; j<turn; j++){
				d.shuffle2();
			}
			t1 =System.currentTimeMillis()-t1;
			System.out.println("時間は" + t1 + "m秒" );
		}
		System.out.println("平均は" + (System.currentTimeMillis()-t2)/round + "m秒");
		d.debugDisp();
	}

}
