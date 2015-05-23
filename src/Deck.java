package jp.ac.hal.jv93.poker;
import java.util.Arrays;
import java.util.Random;

class Deck {
	private			Random	r		= new Random();
	private			int[]	cards	= new int[C.CARD_NUM];//山札
	private			int[]	cardsCheck;
	private final	int[]	cardsM;

	private	DispSumGraph 	disp= new DispSumGraph();

				 Deck(){
		for(int i=0; i<C.CARD_NUM; i++){
			cards[i] = i;
		}
		cardsM	= cards.clone();
	}
	public	void shuffle(){
		Random	r		= new Random();
		int e;
		int temp;

		cards = cardsM.clone();
		for(int i=0; i<C.CARD_NUM; i++){
			e = r.nextInt(C.CARD_NUM-i)+i;
			temp 	= cards[i];
			cards[i]= cards[e];
			cards[e]= temp;
		}
		disp.sum();
		debugCheckShuffle();
	}
	public	void shuffle2(){
//		Random	r		= new Random();
		int e;
		int temp;
		cards = cardsM.clone();
		for(int i=0; i<C.CARD_NUM; i++){
			e = r.nextInt(C.CARD_NUM);
			temp 	= cards[i];
			cards[i]= cards[e];
			cards[e]= temp;
		}
		disp.sum();
		debugCheckShuffle();
	}
	public	void debugDisp(){
		disp.dispMain();
	}

	private boolean debugCheckShuffle(){
		cardsCheck = cards.clone();
		Arrays.sort(cardsCheck);
		for(int i=0; i<C.CARD_NUM; i++){
			if(cardsCheck[i] != i){
				System.out.println("error");
				return false;
			}
		}
		return true;
	}


/////////////////////////////////////////////////////////////////////////////
	private class DispSumGraph{
		private final static int level = 20; 

		private int[][] cardSums = new int[C.CARD_NUM][C.CARD_NUM];
		private int[][] cardCount= new int[C.CARD_NUM][level];
		private int min;
		private int max;
		private int step;
		
		DispSumGraph(){
			for(int i=0; i<C.CARD_NUM; i++){
				Arrays.fill(cardSums[i], 0);
			}
		}
		
		
		void sum(){
			for(int i=0; i<C.CARD_NUM; i++){
				cardSums[i][cards[i]]++;
			}
		}
		void dispMain(){
			for(int i=0; i<C.CARD_NUM; i++){
				for(int j=0; j<C.CARD_NUM; j++){
					System.out.print(cardSums[i][j] + " " );
				}
				System.out.println();
			}
			minMax();
			count();
			dispSol();
			dispAll();
		}
		
		
		void minMax(){
			min = cardSums[0][0];
			max = cardSums[0][0];
			
			for(int i=0; i<C.CARD_NUM; i++){
				for(int j=0; j<C.CARD_NUM; j++){
					if(cardSums[i][j]>max){max = cardSums[i][j];}
					if(cardSums[i][j]<min){min = cardSums[i][j];}
				}
			}
			step = (max - min) / level +1;
			System.out.println("min = " + min);
			System.out.println("max = " + max);
			System.out.println("step = " + step);
			System.out.println("max -min = " + ( max - min ));
			
		}
		void count(){
			for(int i=0; i<C.CARD_NUM; i++){
				for(int j=0; j<C.CARD_NUM; j++){
					cardCount[i][(max-cardSums[i][j])/step]++;
				}
			}
			for(int i=0; i<C.CARD_NUM; i++){
				for(int j=0; j<level; j++){
					System.out.print(cardCount[i][j] + " ");
				}
				System.out.println();
			}
		}
		void dispSol(){
			for(int i=0; i<C.CARD_NUM; i++){
				System.out.println(i + " ---------------------------------------");
				for(int j=0; j<level; j++){
					System.out.print((max - step*j) + " ");
					for(int k=0; k<cardCount[i][j]; k++){
						System.out.print("*");
					}
					System.out.println();
				}
				
			}
		}
		void dispAll(){
			System.out.println("All ---------------------------------------");
			for(int j=0; j<level; j++){
				System.out.print((max - step*j) + " ");
				for(int i=0; i<C.CARD_NUM; i++){
					for(int k=0; k<cardCount[i][j]; k++){
						System.out.print("*");
					}
				}
				System.out.println();
			}
			
			
		}
	}
}
