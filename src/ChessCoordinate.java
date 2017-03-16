// BoardCoordinate provides an abstraction for valid chess position on a square chess board of square BOARD_SIZE
// Can be setup using algebraic chess notation
class ChessCoordinate 
{
	//Maps the integer horizontal co-ordinates to constant letter representations from algebra co-ordinates
	private enum AlgebraicXCoordinate {
		A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

		private int xCoordinate;

		//Map each enum value to an integer representing the x-coordinate
		private AlgebraicXCoordinate(final int coordinate)
		{
			System.out.println("xCoordinate: " + coordinate);
			this.xCoordinate = coordinate;
		}

		public int getValue()
		{
			return this.xCoordinate;
		}

		public static AlgebraicXCoordinate fromInt(int coordinate) 
		{
		    for (AlgebraicXCoordinate algebraCoordinate : AlgebraicXCoordinate.values()) 
		    {
				//System.out.println("AlgebraicXCoordinate : " + algebraCoordinate + " coordinate " + coordinate);
		        System.out.println("algebraCoordinate: " + algebraCoordinate + " coordinate: " + coordinate);
				if (coordinate == algebraCoordinate.getValue())
		        {
		        	return algebraCoordinate;
		        }
		    }

		    throw new IllegalArgumentException("Invalid position co-ordinate for algebraic chess notation.");
		}
	}

	public int x; //horizontal coordinate
	public int y; //vertical coordinate

	public static int BOARD_SIZE = 63; //size of one side of the square chessboard

	//Throws IllegalArgumentException if invalid co-ordinate range
	public ChessCoordinate(int xCoordinate, int yCoordinate) throws IllegalArgumentException
	{
		if (isValidCoordinate(xCoordinate,yCoordinate))
		{
			x = xCoordinate;
			y = yCoordinate;
		} else {
			throw new IllegalArgumentException("Invalid position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}
	}
	//My stuff
	public ChessCoordinate(int position)throws IllegalArgumentException{

		String coordinates[] = getXandYFormPosition(position);
		int xCoordinate = Integer.parseInt(coordinates[0]);
		int yCoordinate = Integer.parseInt(coordinates[1]);

		if (isValidCoordinate(xCoordinate,yCoordinate))
		{
			x = xCoordinate;
			y = yCoordinate;
		} else {
			throw new IllegalArgumentException("Invalid position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}

	}

	//Constructor for a position coordinate from algebraic chess notation (eg. A8 => x=1,y=8 , B2 => x=2,y=2 ...)
	//Throws IllegalArgumentException if an invalid algebraic chess notation is provided
	public ChessCoordinate(String algebraCoordinate) throws IllegalArgumentException
	{
		if (algebraCoordinate.length() == 2)
		{
			try {
				x = AlgebraicXCoordinate.valueOf(algebraCoordinate.substring(0,1).toUpperCase()).getValue(); //Use AlgebraicXCoordinate enum
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid horizontal algebraic co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			try {	
				y = Integer.parseInt(algebraCoordinate.substring(1));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid vertical position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			if (!isValidCoordinate(x,y))
			{
				throw new IllegalArgumentException("Invalid algebraic position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

		} else {
			throw new IllegalArgumentException("Invalid algebraic position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}
	}

	public String[] getXandYFormPosition(int position){

		String [] coordinates = new String[2];

		if(position >= 0 && position <= 7){
			coordinates[0] = new Integer(position).toString();
			coordinates[1] = "0";
		}else if(position > 7 && position <= 15){
			coordinates[0] = new Integer(position - 8).toString();
			coordinates[1] = "1";
		}else if(position > 15 && position <= 23){
			coordinates[1] = "2";
			coordinates[0] = new Integer(position - 16).toString();
		}else if( position > 23 && position <= 31){
			coordinates[1] = "3";
			coordinates[0] = new Integer(position == 8?position - 24:position - 25).toString();
		}else if(position > 31 && position <= 39){
			coordinates[1] = "4";
			coordinates[0] = new Integer(position - 32).toString();
		}else if(position > 39 && position <= 47){
			coordinates[1] = "5";
			coordinates[0] = new Integer(position - 40).toString();
		}else if(position > 47 && position <= 55){
			coordinates[1] = "6";
			coordinates[0] = new Integer(position - 48).toString();
		}else if(position > 55 && position <= 63){
			coordinates[1] = "7";
			coordinates[0] = new Integer(position - 56).toString();
		}

		System.out.println("X is : " + coordinates[0] + " Y is : " + coordinates[1]);

		return coordinates;
	}

	//Is the coordinate a valid position on the defined board size
	public static boolean isValidCoordinate(int xCoordinate, int yCoordinate)
	{
		if (xCoordinate >= 0 && xCoordinate <= BOARD_SIZE && yCoordinate >= 0 && yCoordinate <= BOARD_SIZE)
		//if(true)
		{
			return true;
		} else {
			return false;
		}
	}

	//When does one BoardCoordinate equal another BoardCoordinate
	//Required method to override for use with 'contains' in collections
	//
	//Returns boolean
	public boolean equals(Object other)
	{
		ChessCoordinate another = (ChessCoordinate)other;

		if (another.x == this.x && another.y == this.y)
		{
			return true;
		} else {
			return false;
		}
	}

	//Allows for storage of class in Hash based collections
	//Builds a unique int as the hashcode based on concatenating the string representation of each part of the coordinate
	//
	//Returns int that is unique for each BoardCoordinate
	public int hashCode()
	{
		String xString = String.valueOf(this.x);
		String yString = String.valueOf(this.y);
		String stringCoordinate = xString + yString;
		return Integer.parseInt(stringCoordinate);
	}

	//Return the algebraic notation of the object's chess co-ordinate
	public String toString()
	{
		//String xCoordinate = AlgebraicXCoordinate.fromInt(this.x).name();
		String xCoordinate = String.valueOf(this.x);
		String yCoordinate = String.valueOf(this.y);
		return (xCoordinate + " and " + yCoordinate);
	}

}