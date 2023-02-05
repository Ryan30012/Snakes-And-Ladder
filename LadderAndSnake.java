
public class LadderAndSnake {
    int[][] board = {
            {38,0,0,14,0,0,0,0,31,0},
            {0,0,0,0,6,0,0,0,0,0},
            {42,0,0,0,0,0,0,84,0,0},
            {0,0,0,0,44,0,0,0,0,0},
            {0,0,0,0,0,0,0,30,0,0},
            {0,0,0,0,0,0,0,0,0,67},
            {0,0,0,60,0,0,0,0,0,0},
            {100,19,0,0,0,0,0,0,0,91},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,78,76,0,24,0,68,0,0}
    };
    int numOfPlayer;

    LadderAndSnake(){
        numOfPlayer = 2;
    }

    LadderAndSnake(int numOfPlayer){
        this.numOfPlayer = numOfPlayer;
        if(numOfPlayer<2)
            System.exit(0);
        if (numOfPlayer>2)
            System.out.println("WARNING: The number of player is set to 2 for now!");
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public int flipDice(){
        int dice=0;
        do {
            dice = (int) Math.floor(Math.random() * (7 - 1 + 1));
        }while(dice<1||dice>6);
        return dice;
    }

    public void play(){
        int orderPlay1 = flipDice();
        int orderPlay2 = flipDice();
        int firstPlayer=1;
        int secondPlayer=2;
        int attempt = 1;
        while(orderPlay1==orderPlay2){
            orderPlay2 = flipDice();
            orderPlay1 = flipDice();
            attempt++;
        }
        if (orderPlay1<orderPlay2){
            firstPlayer = 2;
            secondPlayer = 1;
        }

        int play1Spot = 1;
        int play2Spot = 1;
        boolean firsTurn = true;
        int temp=0;
        int prev1;
        int prev2;
        int dice;

        while(play2Spot!=100){
            if(firsTurn==false)
                System.out.println("Game not over, flipping again");

            //player1 flips dice
            dice=flipDice();
            play1Spot+=dice;//=41;
            prev1 = play1Spot;
            System.out.print("Player "+firstPlayer+" rolled a "+dice+":");
            //player 1 moves
            temp = checkSpotForSAndL(play1Spot);
            if(temp!=0)
                play1Spot=temp;
            //check if players are on the same spot
            if(play1Spot==play2Spot)
                play1Spot=0;

            if (play1Spot< prev1)
                System.out.println("gone to square "+ prev1 +" then down to "+play1Spot);
            else if (play1Spot== prev1)
                System.out.println("now in square "+play1Spot);
            else
                System.out.println("gone to square "+ prev1 +" then up to "+play1Spot);

            //check if player 1 won the game
            if (play1Spot==100)
                break;

            //player2 flips dice
            dice=flipDice();

            play2Spot+=dice;

            prev2 = play2Spot;
            System.out.print("Player "+secondPlayer+" rolled a "+dice+":");
            //player 2 moves
            temp=checkSpotForSAndL(play2Spot);
            if (temp!=0)
                play2Spot=temp;
            //check if players are on the same spot
            if(play1Spot==play2Spot) {
                play2Spot = 0;
            }
            if (play2Spot< prev2) {
                System.out.println("gone to square " + prev2 + " then down to " + play2Spot);
            }
            else if (play2Spot== prev2) {
                System.out.println("now in square " + play2Spot);
            }
            else {
                System.out.println("gone to square " + prev2 + " then up to " + play2Spot);
            }
                /*
            display if player moved up or down here
             */
            firsTurn = false;
        }

        if (play1Spot==100)
            System.out.println("Player "+firstPlayer+" has won the game!!");
        else
            System.out.println("Player "+secondPlayer+" has won the game!!");

    }

    public int checkSpotForSAndL(int playSpot){
        //check if the player spot is over 100
        int over=0;
        if(playSpot>100){
            over=101-playSpot;
            playSpot=100+over;
        }

        /********************************************************/

        int row = (int)(playSpot-1)/10;
        int index = (playSpot-1)%10;
        if (row%2==1) {
            if (getBoard()[row][9 - index] != 0)
                return getBoard()[row][9 - index];
        }
        else {
            if (getBoard()[row][index] != 0)
                return getBoard()[row][index];
        }
        return 0;
    }
}
