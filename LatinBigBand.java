import java.util.Scanner;

public class LatinBigBand {

    //Create a Scanner object to read input.
    public static Scanner keyboard = new Scanner (System.in);

    //Declare the constant global variables.
    final static double MAXROWWEIGHT = 100.00;
    final static double MAXPERSONWEIGHT = 200.00;
    final static double MINPERSONWEIGHT = 45.00;

    public static void main(){

        //Declare the variables and array(s).
        double[][] band;
        int rows, col;
        char option;


        //Display the welcome screen.
        System.out.println("Welcome to the Latin Big Band");
        System.out.println("--------------------------------");
        System.out.print("Please enter number of rows\t\t\t: ");

        //Validate the rows and store the input in the first dimension of the array.
        rows = keyboard.nextInt();
        while(rows > 10 || rows < 0){
            System.out.print("ERROR: Out of range, try again\t\t\t: ");
            rows = keyboard.nextInt();
        }
        band = new double [rows][];

        //Prompt the user for the number of positions in each row.
        for (int i = 0; i < band.length; i++){
            System.out.print("Please enter the number of positions in row " + (char)(i + 'A') + "\t: ");
            col = keyboard.nextInt();
            while(col > 8 && col < 0){
                System.out.println("ERROR: Out of range, try again\t\t\t: ");
            }

            //Create the second dimension.
            band[i] = new double[col];
        }

        System.out.println();

        //Call the menu method and process the option.
        option = menu();

        while (option != 'X' && option != 'x')
        {
            switch (option){
                case 'A':
                case 'a':
                    //add add method
                    add(band);
                    option = menu();
                    break;
                case 'R':
                case 'r':
                    //add remove method
                    remove(band);
                    option = menu();
                    break;
                case 'P':
                case 'p':
                    print(band);
                    option = menu();
                    break;
                default:
                    System.out.println("ERROR your selection is not valid");
            }

        }

    }
    public static void print (double band [][]){
        double sumRow;
        double average;
        int c;
        for (int i = 0; i < band.length; i++){
            System.out.print((char)(i + 'A') + ":\t");
            sumRow = 0;

            for ( c = 0; c  < band[i].length; c++){
                System.out.print(band[i][c] + "\t");
                sumRow += band[i][c];
            }
            average = sumRow / band[i].length;

            for (int k = c; k  < 8; k++){
                System.out.print("\t");

            }
            System.out.print("[" + sumRow + " ,\t" + average + "]");
            //Skip a line.
            System.out.println();
        }
    }
    public static char menu (){
        char option;
        System.out.print("(A)dd, (R)emove, (P)rint, e(X)it \t\t: ");
        option = keyboard.next().charAt(0);
        return option;

        /////////////
        //if (band[newrowletter][newcolpos] == 0.0)  //then it's empty
    }
    public static void add (double band [][]){
        char rowL;
        int element;
        int pos;
        double weight;
        System.out.print("Please enter row letter\t\t\t\t: ");
        rowL = keyboard.next().toUpperCase().charAt(0);

        //Obtain the row element by subtracting the value of the input by 65.
        //rowL -= 65;
        element = (int)(rowL - 'A');

        //Validate the row letter.
        while(element > band.length){
            System.out.println("ERROR: Out of range, try again\t\t\t: ");
            rowL = keyboard.next().toUpperCase().charAt(0);
            //rowL -= 65;
            element = (int)(rowL - 'A');
        }

        //Prompt the user for the position number.
        System.out.println("Please enter position number (1 to " + (band[element].length + ")\t:\t"));

        pos = keyboard.nextInt();

        //Validate the position.
        while(pos > band[element].length){
            System.out.println("ERROR: Out of range, try again\t\t\t: ");
            pos = keyboard.nextInt();
        }

        System.out.println("Please enter weight (45.0 to 200.0) : ");
        weight = keyboard.nextDouble();

        //Validate minimum and maximum weight of the position.
        while(weight > MAXPERSONWEIGHT || weight < MINPERSONWEIGHT){
            System.out.println("ERROR: Out of range, try again\t\t\t: ");
            weight = keyboard.nextDouble();
        }
        //Validate the weight of the position with the max weight of the row.

        System.out.println("$$$$$$$$$$$ " + weight);
        while(weight > (MAXROWWEIGHT * band[element].length)){
            System.out.println("ERROR: That would exceed the average weight limit.");
            System.out.println();
            menu();
        }
        //print(band);

        //Validate the position occupancy.

        if (band [element][pos] == 0.0)
        {
            System.out.println("Non zero");
            band [element][pos -1 ] = weight;

            System.out.println("****** Musician added.");
        }
        else
        {
            System.out.println("ERROR: There is already a musician there.");
            System.out.println();
            menu();
        }
    }
    public static void remove (double band [][]){
        char rrowL;
        int rpos;

        //Prompt the user for a row letter.
        System.out.println("Please enter row letter\t\t\t: ");
        rrowL = keyboard.next().toUpperCase().charAt(0);

        //Obtain the row element by subtracting the value of the input by 65.
        rrowL -= 65;

        //Validate the row letter.
        while(rrowL > band.length){
            System.out.println("ERROR: Out of range, try again\t\t\t: ");
            rrowL = keyboard.next().toUpperCase().charAt(0);
            rrowL -= 65;
        }
        //Prompt the user for the position number.
        System.out.println("Please enter position number (1 to " + (band[rrowL].length + "\t:\t"));

        rpos = keyboard.nextInt();

        //Validate the position.
        while(rpos > band.length){
            System.out.println("ERROR: Out of range, try again\t\t\t: ");
            rpos = keyboard.nextInt();
        }

        //Validate the position occupancy.
        if (band[rrowL][rpos-1] == 0.0){
            System.out.println("ERROR: That position is vacant.");
            System.out.println();
            menu();
        }
        //If the position is not empty, notify the user and display the menu.
        else{
            band[rrowL][rpos-1] = 0.0;
            System.out.println("****** Musician removed.");
            System.out.println();
            menu();
        }
    }
}
