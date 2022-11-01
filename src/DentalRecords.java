import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class DentalRecords {
    private static final Scanner keyboard = new Scanner(System.in);

//---- Final variables
    private static final int ROWS_OF_TEETH = 2;
    private static final int MAX_FAMILY = 6;
    private static final int MAX_VALUE = 8;
    private static final int FORMATTING_CONSTANT_FOR_UPPERS_LOWERS = 22;

    public static void main(String[] args) {

//---- Variables for main method
        char[][][] familyTeethArray;
        String[] familyNames;
        int numMembers;
        int numFamilyIndex;
        int menuConstant = 0;
        char menuChoice;

//---- Print out welcome message
        System.out.println("Welcome to the Floridian Tooth Records\n--------------------------------------");

//---- Call method to get number of members
        numMembers = getFamilyMembers();

        familyTeethArray = new char[numMembers][][];

        familyNames = new String[numMembers];

//---- Build 3D array of tooth types for family
        for (numFamilyIndex = 0; numFamilyIndex < familyTeethArray.length; ++numFamilyIndex) {
            System.out.print("Please enter the name for family member " + (numFamilyIndex + 1) + "   : ");
            familyNames[numFamilyIndex] = keyboard.nextLine();

            familyTeethArray[numFamilyIndex] = describeTeeth(familyNames[numFamilyIndex]);

        }

//---- Run menu options
        do {
            System.out.print("\n(P)rint, (E)xtract, (R)oot, e(X)it          : ");
            menuChoice = keyboard.next().charAt(0);
            menuChoice = Character.toUpperCase(menuChoice);
            if (menuChoice == 'P') {
                printTeeth(familyTeethArray, familyNames);
            } else if (menuChoice == 'E') {
                extractTooth(familyTeethArray, familyNames);
            } else if (menuChoice == 'R') {
                rootCanal(familyTeethArray);
            } else if (menuChoice == 'X') {
                exitRecords();
            } else {

//---- Loop to get new input if input is not an option
               while(menuChoice != 'P' && menuChoice != 'E' && menuChoice != 'R' && menuChoice != 'X') {
                   System.out.print("Invalid menu option, try again              : ");
                   menuChoice = keyboard.next().charAt(0);
                   menuChoice = Character.toUpperCase(menuChoice);
                   if (menuChoice == 'P') {
                       printTeeth(familyTeethArray, familyNames);
                   } else if (menuChoice == 'E') {
                       extractTooth(familyTeethArray, familyNames);
                   } else if (menuChoice == 'R') {
                       rootCanal(familyTeethArray);
                   } else if (menuChoice == 'X') {
                       exitRecords();
                   } else {
                       menuChoice = ' ';
                   }
               }
            }
        } while (menuConstant == 0);

    }

//----Get number of family members
    private static int getFamilyMembers() {

        int familyMembers;
        System.out.print("Please enter number of people in the family : ");
        familyMembers = keyboard.nextInt();
//---- Check that familyMembers is in range
        while (familyMembers > MAX_FAMILY || familyMembers < 0) {
            System.out.print("Invalid number of people, try again         : ");
            familyMembers = keyboard.nextInt();

        }
        keyboard.nextLine();
        return (familyMembers);
    }

//---- Get tooth types for each family member
    private static char[][] describeTeeth(String familyNames) {

        String teeth;
        char[][] teethArray = new char[ROWS_OF_TEETH][];
        int index;
        boolean runAgain;
        int familyNameLength;
        int formatAmount;

//---- Get length of family name, then subtract from the formatting constant to get spacing amount to align ':'
        familyNameLength = familyNames.length();
        formatAmount = FORMATTING_CONSTANT_FOR_UPPERS_LOWERS - familyNameLength;

//---- Get uppers for member
            System.out.printf("Please enter uppers for " + familyNames + "%"+formatAmount+"s"," : ");

//---- Check if user inputs more than 8 teeth
            teeth = keyboard.nextLine();
            while (teeth.length()>MAX_VALUE) {
                System.out.print("Too many teeth, try again                   : ");
                teeth = keyboard.nextLine();
            }
            teeth = teeth.toUpperCase();

//---- Checks if teeth are of types I, B, or M, and if not
            do {
                runAgain = false;
                for (index=0; index<teeth.length(); ++index) {
                    if (teeth.charAt(index)!= 'I' && teeth.charAt(index)!='B' && teeth.charAt(index)!='M') {
                        System.out.print("Invalid teeth types, try again              : ");
                        teeth = keyboard.nextLine();
                        while (teeth.length()>MAX_VALUE) {
                            System.out.print("Too many teeth, try again                   : ");
                            teeth = keyboard.nextLine();
                        }
                        teeth = teeth.toUpperCase();
                        runAgain = true;
                        break;
                    }
                }
            } while (runAgain);

            teethArray[0] = teeth.toCharArray();

        System.out.printf("Please enter lowers for " + familyNames + "%"+formatAmount+"s"," : ");

        teeth = keyboard.nextLine();

//---- Check if user inputs more than 8 teeth
        while (teeth.length()>MAX_VALUE) {
            System.out.print("Too many teeth, try again                   : ");
            teeth = keyboard.nextLine();
        }
        teeth = teeth.toUpperCase();

//---- Checks if teeth are of types I, B, or M, if not continue running loop to get new inputs and check them
        do {
            runAgain = false;
            for (index=0; index<teeth.length(); ++index) {
                if (teeth.charAt(index)!= 'I' && teeth.charAt(index)!='B' && teeth.charAt(index)!='M') {
                    System.out.print("Invalid teeth types, try again              : ");
                    teeth = keyboard.nextLine();

//---- Check if new inputs are more than 8 teeth
                    while (teeth.length()>MAX_VALUE) {
                        System.out.print("Too many teeth, try again                   : ");
                        teeth = keyboard.nextLine();
                    }
                    teeth = teeth.toUpperCase();
                    runAgain = true;
                    break;
                    }
                }
            } while (runAgain);

//---- Set input to lower value in array
    teethArray[1] = teeth.toCharArray();

    return (teethArray);
    }

//---- Print out teeth array
    private static void printTeeth(char[][][] teethArray, String[] familyNames) {

        int plane;
        int row;
        int column;

        for (plane = 0; plane < teethArray.length; ++plane) {
            System.out.println("\n" + familyNames[plane]);
            for (row = 0; row < teethArray[plane].length; ++row) {
                if (row == 0) {
                    System.out.print("  Uppers:  ");
                } else {
                    System.out.print("\n  Lowers:  ");
                }
                for (column = 0; column < teethArray[plane][row].length; ++column) {
                    System.out.print((column + 1) + ":" + teethArray[plane][row][column] + "  ");
                }
            }
        }
        System.out.println();
    }

//---- Exit the Dental Records
    private static void exitRecords() {
        System.out.print("\nExiting the Floridian Tooth Records :-)");
        System.exit(0);
    }

//---- Extract tooth from array
    private static char[][][] extractTooth(char[][][] teethArray, String[] names) {

        String familyMember;
        String familyMemberUpperCase;
        int familyMemberIndex;
        char layer;
        int layerNum;
        int toothNum;
        int index;

//---- Get family member to extract tooth from
            System.out.print("Which family member                         : ");
            keyboard.nextLine();
            familyMember = keyboard.nextLine();

//---- Set the strings in the names array to all caps and the family member name to all caps, so they won't be case-sensitive on check
            for (index=0; index<names.length; ++index) {
                names[index] = names[index].toUpperCase();
            }
        familyMemberUpperCase = familyMember.toUpperCase();

//---- Check if family member entered is in names array
            while (!Arrays.asList(names).contains(familyMemberUpperCase)) {
                System.out.print("Invalid family member, try again            : ");
                familyMember = keyboard.nextLine();
                familyMemberUpperCase = familyMember.toUpperCase();
            }

        familyMemberIndex = Arrays.asList(names).indexOf(familyMemberUpperCase);
        System.out.print("Which tooth layer (U)pper or (L)ower        : ");
        layer = keyboard.next().charAt(0);
        layer = Character.toUpperCase(layer);

//---- Check if layer input is valid
        while (layer != 'U' && layer != 'L') {
            System.out.print("Invalid layer, try again                    : ");
            layer = keyboard.next().charAt(0);
            layer = Character.toUpperCase(layer);
        }
        if (layer == 'U') {
            layerNum = 0;
        } else  {
            layerNum = 1;
        }
            System.out.print("Which tooth number                          : ");
            toothNum = keyboard.nextInt() - 1;

//---- Check if input is in array and if not run loop to get new input
            while (toothNum > teethArray[familyMemberIndex][layerNum].length) {
                System.out.print("Invalid tooth number, try again             : ");
                toothNum = keyboard.nextInt() - 1;
            }

//---- Check if tooth is value M and if so run loop to get a new input
            while (teethArray[familyMemberIndex][layerNum][toothNum] == 'M') {
                System.out.print("Missing tooth, try again                    : ");
                toothNum = keyboard.nextInt() - 1;
            }

//---- Set tooth input to value M
            teethArray[familyMemberIndex][layerNum][toothNum] = 'M';

//---- Set name values back for output
        for (index=0; index<names.length; ++index) {
            names[index] = names[index].substring(0,1).toUpperCase() + names[index].substring(1).toLowerCase();
        }
            return (teethArray);
        }

//---- Get families root canal indices
    private static void rootCanal(char[][][] teethArray) {
        int plane;
        int row;
        int column;
        double totalB = 0;
        double totalI = 0;
        double totalM = 0;
        double firstRoot;
        double secondRoot;

//---- Counts total of each tooth value for quadratic equation
        for (plane = 0; plane < teethArray.length; plane++) {
            for (row = 0; row < teethArray[plane].length; row++) {
                for (column = 0; column < teethArray[plane][row].length; column++) {
                    if (teethArray[plane][row][column] == 'I') {
                        totalI++;
                    } else if (teethArray[plane][row][column] == 'B') {
                        totalB++;
                    } else if (teethArray[plane][row][column] == 'M') {
                        totalM++;
                    }
                }
            }
        }

//---- Quadratic equations to get root canal indices'
        firstRoot = ((totalB * -1.0) + Math.sqrt((totalB * totalB) - (4.0 * totalI * (totalM*-1.0)))) / (2.0 * totalI);
        System.out.format("One root canal at\t     %.2f", firstRoot);
        secondRoot = ((totalB * -1.0) - Math.sqrt((totalB * totalB) - (4.0 * totalI * (totalM*-1.0)))) / (2.0 * totalI);
        System.out.format("\nAnother root canal at\t %.2f", secondRoot);
        System.out.println();
    }
}

