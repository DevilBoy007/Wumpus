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

    static void shoot(boolean f, Rooms[] x, int y, int z){
        if(x[z - 1].isAdj(y)){
            System.out.println("You hit the Wumpus!! Congratulations, the locals have heard the news and wish" +
                    " to make you their new king.");
        }
        else System.out.println("You hit nothing.");
    }

    static int wumpusRoom() {
        int wumpusRoom = (int) (2 + (8 * Math.random()));
        return wumpusRoom;
    }

    static int spiderRoom() {
        int spiders = (int) (2 + (8 * Math.random()));
        return spiders;
    }

    static int spiderRoomTwo() {
        int secondSpiders = (int) (2 + (8 * Math.random()));
        return secondSpiders;
    }

    static int darkPit() {
        int pit = (int) (2 + (8 * Math.random()));
        return pit;
    }

    static int darkPitTwo() {
        int secondPit = (int) (2 + (8 * Math.random()));
        return secondPit;
    }

    public static void main(String[] args) throws FileNotFoundException{

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
                System.out.println("Room " + room);
                room = move(roomDetails,room, nextRoom);
            }
            else if(user.equalsIgnoreCase("s")){
                shoot(isPlaying, arrows, roomDetails, wumpusRoom, room);
        }
		}
    }
}