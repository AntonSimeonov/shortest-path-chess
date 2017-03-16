import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anton on 10.03.17.
 */
public class Answer {

    public static int answer(int start, int end){
        int startPosition = start;
        int endPosition = end;
        int steps = 0;
        try {
                BoardShortestPath knightShortestPath = new BoardShortestPath(startPosition, endPosition);
                steps = knightShortestPath.shortestPath();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        return steps;
    }

    //-----------------------------------------
    static class Knight {

        public ArrayList<BoardCoordinate> validMoves(BoardCoordinate startPosition)
        {
            ArrayList<BoardCoordinate> validCoordiantes = new ArrayList<BoardCoordinate>();

            int[] moves = {-2, -1, 1, 2};

            for (int xMove : moves) {
                for (int yMove : moves) {
                    if (Math.abs(xMove) != Math.abs(yMove)) {
                        if (isValidCoordinate(startPosition.x + xMove, startPosition.y + yMove))
                        {
                            validCoordiantes.add((new BoardCoordinate((startPosition.x + xMove),(startPosition.y + yMove))));
                        }
                    }
                }
            }

            return validCoordiantes;
        }

    }

    public static boolean isValidCoordinate(int xCoordinate, int yCoordinate)
    {
        if (xCoordinate >= 0 && xCoordinate <= 7 && yCoordinate >= 0 && yCoordinate <= 7)
        {
            return true;
        } else {
            return false;
        }
    }

    static class BoardCoordinate
    {

        public int x;
        public int y;

        public BoardCoordinate(int xCoordinate, int yCoordinate) throws IllegalArgumentException
        {
            if (isValidCoordinate(xCoordinate,yCoordinate))
            {
                x = xCoordinate;
                y = yCoordinate;
            } else {
                throw new IllegalArgumentException("Invalid position co-ordinate.");
            }
        }

        public BoardCoordinate(int position)throws IllegalArgumentException{

            String coordinates[] = getXandYFormPosition(position);
            int xCoordinate = Integer.parseInt(coordinates[0]);
            int yCoordinate = Integer.parseInt(coordinates[1]);

            if (isValidCoordinate(xCoordinate,yCoordinate))
            {
                x = xCoordinate;
                y = yCoordinate;
            } else {
                throw new IllegalArgumentException("Invalid position co-ordinate.");
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
                coordinates[0] = new Integer(position - 24).toString();
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


        public boolean equals(Object other)
        {
            BoardCoordinate another = (BoardCoordinate)other;

            if (another.x == this.x && another.y == this.y)
            {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode()
        {
            String xString = String.valueOf(this.x);
            String yString = String.valueOf(this.y);
            String stringCoordinate = xString + yString;
            return Integer.parseInt(stringCoordinate);
        }


        public String toString()
        {

            String xCoordinate = String.valueOf(this.x);
            String yCoordinate = String.valueOf(this.y);
            return (xCoordinate + " and " + yCoordinate);
        }

    }

    //---------------------------------------------------
    static class BoardShortestPath {

        public BoardCoordinate startPos;
        public BoardCoordinate endPos;
        public Knight knight;

        public BoardShortestPath(int boardStartPosition, int boardEndPosition){
            startPos = new BoardCoordinate(boardStartPosition);
            endPos = new BoardCoordinate(boardEndPosition);
            knight = new Knight();
        }


        public int shortestPath()
        {

            HashMap<BoardCoordinate, BoardCoordinate> parentNode = new HashMap<BoardCoordinate, BoardCoordinate>();


            Queue<BoardCoordinate> positionQueue = new LinkedList<BoardCoordinate>();

            parentNode.put(startPos,null);
            positionQueue.add(startPos);


            while (positionQueue.peek() != null)
            {
                BoardCoordinate currentPosition = positionQueue.poll();

                if (currentPosition.equals(endPos))
                {
                    break;
                }


                ArrayList<BoardCoordinate> nextPositions = knight.validMoves(currentPosition);
                for (BoardCoordinate adjacentPosition : nextPositions)
                {
                    if (!parentNode.containsKey(adjacentPosition))
                    {
                        parentNode.put(adjacentPosition,currentPosition);
                        positionQueue.add(adjacentPosition);
                    }
                }
            }

            BoardCoordinate currentNode = endPos;
            String shortestPath = "";
            int steps = 0;
            while (parentNode.get(currentNode) != null)
            {
                shortestPath = currentNode.toString() + " " + shortestPath;
                currentNode = parentNode.get(currentNode);
                steps++;
            }

            if (shortestPath.length() == 0)
            {
                shortestPath = startPos.toString();
            }

            //Print out the shortest path found, excluding start position and including end position
            System.out.println(shortestPath);

            return steps;
        }


    }

}
