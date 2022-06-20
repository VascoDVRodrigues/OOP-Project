package videopoker.deck;

/**
 * 
 * Class that implements a card object, it assumes that:
 * 
 * H - hearts (copas)
 * D - diamonds (oiros)
 * S - spades (espadas)
 * C - clubs (paus)
 * 
 * Ace --> 1
 * Numbers: 2 to 9
 * Number 10 --> T
 * Jack --> 11
 * Queen --> 12
 * King --> 13
 */
public class Card {
	protected char nape;
	protected int number;

	public Card(char n, int num) {
		this.nape = n;
		this.number = num;
	}

	/**
	 * @return the nape
	 */
	public char getNape() {
		return nape;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	public Card(String s) {
		// Se quisermos dar uma string c a carta em vez de numero e naipe separado
		// Vem na forma sempre Numero Naipe
		char c = s.charAt(0);

		switch (c) {
			case 'K':
				this.number = 13;
				break;
			case 'Q':
				this.number = 12;
				break;
			case 'J':
				this.number = 11;
				break;
			case 'T':
				this.number = 10;
				break;
			case 'A':
				this.number = 1;
				break;
			default:
				this.number = Character.getNumericValue(c);
				break;
		}

		c = s.charAt(1);
		if (c == 'H' || c == 'D' || c == 'S' || c == 'C') {
			this.nape = c;
		}
	}
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
         
        Card c = (Card) obj;
         
        return Integer.compare(this.number, c.number) == 0
                && Character.compare(this.nape, c.nape) == 0;        
    }
	@Override
	public String toString() {
		char[] str = new char[2];

		switch (this.number) {
			case 1:
				str[0] = 'A';
				break;
			case 10:
				str[0] = 'T';
				break;
			case 12:
				str[0] = 'Q';
				break;
			case 11:
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

	public void printUnconverted() {
		System.out.println("(" + this.number + this.nape + ")");
	}
}
