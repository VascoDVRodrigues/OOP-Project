package WIP;

/*
 * H - hearts (copas)
 * D - diamonds (oiros)
 * S - spades (espadas)
 * C - clubs (paus)
 * 
 * As --> 1
 * Numbers 2 to 9
 * Number 10 --> T
 * Queen --> 11
 * Jack --> 12
 * King --> 13
 */

public class Card {
	protected char nape;
	protected int number;

	public Card(char n, int num) {
		this.nape = n;
		this.number = num;
	}

	public Card(String s) {
		//Se quisermos dar uma string c a carta em vez de numero e naipe separado
		//Vem na forma sempre Numero Naipe 
		this.number = Character.getNumericValue(s.charAt(0)); 
		this.nape = s.charAt(1); 
	}

	@Override
	public String toString() {
		char[] str = new char[2];

		switch(this.number) {
			case 1:
				str[0] = 'A';
			  break;
			case 10:
				str[0] = 'T';
				break;
			case 11:
				str[0] = 'Q';
			  break;
			case 12:
				str[0] = 'J';
				break;
			case 13:
				str[0] = 'K';
				break;
			default:
				// str[0] = Character.forDigit(this.number, 10);
				str[0] = (char) (this.number + '0');

				break;
		}       

		str[1] = this.nape;

		return String.valueOf(str);
	}
}
