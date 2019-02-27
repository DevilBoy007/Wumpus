import java.io.*;
import java.util.*;
import java.math.*;
public class Wumpus extends Rooms{
    public Wumpus(Scanner roomFile) {
        super(roomFile);
    }

    static void dangerous(Rooms x, int a, int b, int c, int d, int e){
        if (x.isAdj(a)) System.out.println("Room " + a + " reeks of Wumpus");
        if (x.isAdj(b)) System.out.println("There's a faint click coming from " +
                "room " + b);
        if (x.isAdj(c)) System.out.println("There's a faint click coming from " +
                "room " + c);
        if (x.isAdj(d)) System.out.println("Room " + d + " sounds cavernous");
        if (x.isAdj(e)) System.out.println("Room " + e + " sounds cavernous");
    }

    //static boolean isSafe(Rooms x, int a, int b, int c, int d, int e){
      //  return(x.getNum()!=a&&x.getNum()!=b&&x.getNum()!=c&&x.getNum()!=d&&x.getNum()!=e);
    //}

    static int move(Rooms[] a,int x, int y){
        if (y == a[x - 1].getAdj1())
            return y;
        else if (y == a[x - 1].getAdj2())
            return y;
        else if (y == a[x - 1].getAdj3())
            return y;
        else {System.out.println("You can only get to rooms through the connecting tunnels\n"); return x;}
    }

    static boolean shoot(int wump, int target){
	    if(target==wump){
		    System.out.println("Congratulations!! You hit the Wumpus");
		    System.out.println("The local villagers have heard the good news and wish to make you their new king");
		    return false;
	    }
		    else System.out.println("You hit nothing."); return true;
    }
	
   static boolean isRepeating(int[] a, int x){
	   for(int i = 0; i<a.length; i++){
		   if(x==a[i])
			   return true;
	   }
		   return false;
	}
   static int [] hazardRooms=new int[5];	
	
    static int wumpusRoom() {
        int wumpusRoom = (int) (2 + (8 * Math.random()));
	    hazardRooms[0]=wumpusRoom;
        return wumpusRoom;
    }

    static int spiderRoom() {
        int spiders = (int) (2 + (8 * Math.random()));
	    while(isRepeating(hazardRooms, spiders))
		{spiders = (int) (2 + (8 * Math.random()));}
	hazardRooms[1]=spiders;
        return spiders;
    }

    static int spiderRoomTwo() {
        int secondSpiders = (int) (2 + (8 * Math.random()));
	     while(isRepeating(hazardRooms, secondSpiders))
		    {secondSpiders = (int) (2 + (8 * Math.random()));}
	hazardRooms[2]=secondSpiders;
        return secondSpiders;
    }

    static int darkPit() {
        int pit = (int) (2 + (8 * Math.random()));
	    while(isRepeating(hazardRooms, pit))
		    {pit = (int) (2 + (8 * Math.random()));}
	hazardRooms[3]=pit;
        return pit;
    }

    static int darkPitTwo() {
        int secondPit = (int) (2 + (8 * Math.random()));
	     while(isRepeating(hazardRooms, secondPit))
		   {secondPit = (int) (2 + (8 * Math.random()));}
	hazardRooms[4]=secondPit;
        return secondPit;
    }

    public static void main(String[] args) throws IOException{

        Scanner cin = new Scanner(System.in);
        Scanner roomFile;
        roomFile = new Scanner(new FileReader("wumpusText.txt"));
        int numRoom = roomFile.nextInt();

        Rooms[] roomDetails;
        roomDetails = new Rooms[numRoom];

        for (int i = 0; i < roomDetails.length; i++) {

            roomDetails[i] = new Rooms(roomFile);
        }
        int room = 1;
        int arrows=3;
        int nextRoom;
        int wumpusRoom=wumpusRoom();
        int spiderRoom=spiderRoom();
        int spiderRoomTwo=spiderRoomTwo();
        int darkPit=darkPit();
        int darkPitTwo = darkPitTwo();
        String user;
        wumpusRoom();
        spiderRoom();
        spiderRoomTwo();
        darkPit();
        darkPitTwo();
        boolean isPlaying=true;
		System.out.println("The Evil Wumpus has been terrorizing the local village,");
		System.out.println("You must locate and slay the beast in it's cave lair!\n");
		System.out.println("You have been equipped with a special bow that allows you to fire three of your " +
                "arrows at once in different directions.");
		while(isPlaying) {
		    if(room==wumpusRoom) isPlaying=false;
            System.out.println("You have " + arrows + " arrows.");
            System.out.println("You are in room " + roomDetails[room - 1].getNum() + ".\n");
            System.out.println(roomDetails[room - 1].getText());
            System.out.println("There are tunnels leading to rooms " + roomDetails[room - 1].getAdj1() + ", " + roomDetails[room - 1].getAdj2() + ", and " + roomDetails[room - 1].getAdj3());
            dangerous(roomDetails[room - 1], wumpusRoom, spiderRoom, spiderRoomTwo, darkPit, darkPitTwo);
            System.out.println("You can (m)ove or (s)hoot\nWhat would you like to do?");
            user = cin.next();
            if (user.equalsIgnoreCase("m")) {
                System.out.println("Which room would you like to move to?");
                nextRoom = cin.nextInt();
                room = move(roomDetails,room, nextRoom);
            }
            else if(user.equalsIgnoreCase("s")){
		    if(arrows==0)
		System.out.println("You have no arrows!!");
		else
			arrows--;
			System.out.println("Which room would you like to shoot into?");
			System.out.println(roomDetails[room-1].getAdj1() + " " + roomDetails[room-1].getAdj2() + " " + roomDetails[room-1].getAdj3());
		    nextRoom=cin.nextInt();
		    if(!(roomDetails[room-1].isAdj(nextRoom)))
			    System.out.println("You can only shoot into rooms that you are next to... obviously");
		    else
                	isPlaying = shoot(wumpusRoom, nextRoom);
        }
		}
    }
}
