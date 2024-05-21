import java.util.*;

public class Game {
    public int money = 10000; //players money
    public int raceGoal;
    public static Double worstTeamRating;
    HashMap<String, Car> team = new HashMap<>(); //Create a hashmap used to use different parts of Car object with help from "value"
    Scanner input = new Scanner(System.in);

    //Car object with different ratings/stats on different parts. and name for every team
    Car playerCar = new Car("Your Team", 64, 64, 61, 60, 0) ; //
    Car Redbull = new Car("Redbull", 96, 97, 90, 99, 0);
    Car Ferrari = new Car("Ferrari", 90, 87, 80, 87, 0);
    Car Mercedes = new Car("Mercedes", 81, 82, 84, 80, 0);
    Car Mclaren = new Car("Mclaren", 76, 79, 77, 81, 0);
    Car Aston_Martin = new Car("Aston Martin", 80, 77, 75, 80, 0);
    Car Alpine = new Car("Alpine", 73, 72, 67, 75, 0);
    Car Williams = new Car("Williams", 73, 80, 70, 69, 0);
    Car Haas = new Car("Haas", 68, 71, 66, 67, 0);
    Car Stake = new Car("Stake", 61, 64, 60, 64, 0);



    Driver Verstappen = new Driver("Max Verstappen", Redbull.getEngine(), Redbull.getAerodynamics(), Redbull.getReliability(), Redbull.getWeight(), Redbull.getPoints());
    public Game() { //This one is called from Main

        StartMenu();

    }

    public void StartMenu() { //Just askes if the player wants to play or quit (quite unnecessary when thinking about it)
        boolean menuBoolean = true;
        while (menuBoolean) { //This while-loop, loops until the user has used the right input. When the user does, it breaks and continues with the code.
            System.out.println("\n      Menu \n\n1. Start game \n2. Quit game");
            //This try-catch checks if the input in valid, and if something is wrong it repeats the segment and scans again
            try {
                int choice = input.nextInt();
                if (choice == 1) { //Start the game
                    menuBoolean = false;
                    MainMenu();
                } else if (choice == 2) {
                    System.exit(0); //Quits the game
                } else {
                    System.out.println("Invalid choice, please select 1 or 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please try again! Type in the numbers on the side of the path to continue. \nIn this case, type 1 or 2");
                input.nextLine();
            }
        }
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"clears" the console
    }

    public void MainMenu() {

        int choice = 0;
        do {
            try {
                System.out.println("Menu \n\n1.Next Race \n2.Upgrades & Research \n3.Constructor Rating");
                choice = input.nextInt();
                if (choice == 1) { // Start the game
                    Race();
                } else if (choice == 2) {
                    Upgrades();
                } else if (choice == 3) {
                    BotStats();
                }

            } catch (InputMismatchException e) {
                System.out.println("Try again! Type in the numbers on the side of the path to continue. \nIn this case, type 1, 2 or 3");
                input.nextLine(); // Clear the buffer
            }
        } while (choice < 1 || choice >3); // Repeat until a valid choice (1-3) is entered
    }


    public void Race() {
        //This race-sim works with how good each car is. It takes the average value of a car (ex. 89) and subtracts the lowest average rating on the grid
        // (ex. 89-66=33) and adds 5. This is because the number each team gets, will be randomized. the highest number wins. So the best car has better chance to win,
        //this is because it has a higher number to achieve, but it can still be rolled as a low number. The worst team can only roll up to a 5. In order for them to win
        //every other car must roll under 5. It's possible, but very slim.

        PredictedPosition(); //Calls the predicted place of the players car to then make a decision on where to finish in order for a bonus
        Random random = new Random();

        System.out.println();
        System.out.println("Your goal for this race is p" + raceGoal + "\nIf you succeed you will earn a 10000$ bonus!");

        input.nextLine();

        //The hashmap created in the beginning of Game.java, it's explained in detail at DisplayStats()
        team.put(playerCar.name, playerCar);
        team.put(Redbull.name, Redbull);
        team.put(Ferrari.name, Ferrari);
        team.put(Mercedes.name, Mercedes);
        team.put(Mclaren.name, Mclaren);
        team.put(Aston_Martin.name, Aston_Martin);
        team.put(Alpine.name, Alpine);
        team.put(Williams.name, Williams);
        team.put(Haas.name, Haas);
        team.put(Stake.name, Stake);

        for (Car carName : team.values()) { //Same loop as in DisplayStats()
            //Points = average stats on car - worst average rating + 5.
            //Then it's randomized from 0 to what number the team gets.
            carName.points = random.nextDouble(carName.pointSystem());
        }

        //this array sorts the teams based on highest randomized number. In other word it is the race results
        ArrayList<Double> racingGrid = new ArrayList<>();
        racingGrid.add(playerCar.points);
        racingGrid.add(Redbull.points);
        racingGrid.add(Mclaren.points);
        racingGrid.add(Mercedes.points);
        racingGrid.add(Aston_Martin.points);
        racingGrid.add(Haas.points);
        racingGrid.add(Williams.points);
        racingGrid.add(Ferrari.points);
        racingGrid.add(Stake.points);
        racingGrid.add(Alpine.points);

        Collections.sort(racingGrid);
        Collections.reverse(racingGrid);


        //this block prints out the race result in order from 1-10
        int position = 1;  // Keeps track of the current position
        for (double points : racingGrid) { //Just copies over the value from the specific teams points into the double points
            for (Car carName : team.values()) { //Does the same but with the teams name
                if (carName.points == points) { //Matches the copied versions to the real score to know which team scored what
                    System.out.println(position + ". " + carName.name + " Points: " + points);
                    position++;
                    break;  // Once car is found, break out of inner loop
                }
            }
        }

        // this code checks if the player reached the goal in order for the bonus
        if (racingGrid.indexOf(playerCar.points) <= raceGoal - 1) {
            System.out.println("Your Team reached the goal for this race! Therefore your bonus will be granted.");
            money += 10000;
        } else {
            System.out.println("Your team did not reach the goal for this race. No bonus granted");
        }

        input.nextLine();

        money += 1000;
        MainMenu();

    }

    public void Upgrades() {
        System.out.println("Your balance is " + money + "$");
        Random random = new Random();

        int upgradeChoice;
        int randPrice = random.nextInt(5001) + 3000; //Randomizes the price for the upgrade from 3000 to 4000
        int remainMoney = money - randPrice; //The remaining money the user would have if they proceeded with the purchase
        int randAmount = random.nextInt(4); //Randomizes how much the upgrade will do from 0-3. Example: Engine has 60 and buying this upgrade might get it to 63. But if the user is unlucky, the upgrade may fail and won't gain any points.

        try {
            System.out.println("What do you want to upgrade? \n1. Engine\n2. Aerodynamics\n3. Reliability\n4. Weight \n5. Information about Research & Development \n6. Game Menu");
            upgradeChoice = input.nextInt();

            switch (upgradeChoice) { //switch case is easier to cope with when there is so many options for the user to make. it also has its own "default" which can do things if the users input doesn't match what it should
                case 1: // FYI, case 1-4 has all the same code, only that it upgrades different parts

                    if (money >= randPrice) { //this if statement check if the user has enough money to buy the upgrade, otherwise they will be sent back to the menu
                        System.out.println("This upgrade costs " + randPrice + "$, you have " + money + "$ currently. \nIf you proceed with this purchase, you will have " + remainMoney + "$ remaining. Would you still like to proceed? y/n");
                        String upgradeProceed = input.next();

                        switch (upgradeProceed) {
                            case "y" -> {
                                money -= randPrice; //User pays for the upgrade and "money" will have that same value throughout the code bc of the -=
                                playerCar.engine += randAmount; //the attribute upgrades 0-3 points and stays that way bc +=. in this case its the engine
                                System.out.println(playerCar.engine - randAmount + " -> " + playerCar.engine);
                                System.out.println("Your engine went up with " + randAmount + " points for " + randPrice + "$");
                            }
                            case "n" -> System.out.println("Going back...");
                            default -> System.out.println("Please type in 'y' for yes, and 'n' for no.");
                        }

                    } else {
                        System.out.println("Insufficient funds... You only have " + money);

                    }
                    break;

                case 2:

                    if (money >= randPrice) {
                        System.out.println("This upgrade costs " + randPrice + "$, you have " + money + "$ currently. \nIf you proceed with this purchase, you will have " + remainMoney + "$ remaining. Would you still like to proceed? y/n");
                        String upgradeProceed = input.next();

                        switch (upgradeProceed) {
                            case "y" -> {
                                money -= randPrice;
                                playerCar.aerodynamics += randAmount;
                                System.out.println(playerCar.aerodynamics - randAmount + " -> " + playerCar.aerodynamics);
                                System.out.println("Your aerodynamics went up with " + randAmount + " points for " + randPrice + "$");
                            }
                            case "n" -> System.out.println("Going back...");
                            default -> System.out.println("Please type in 'y' for yes, and 'n' for no.");
                        }

                    } else {
                        System.out.println("Insufficient funds... You only have " + money);

                    }
                    break;

                case 3:

                    if (money >= randPrice) {
                        System.out.println("This upgrade costs " + randPrice + "$, you have " + money + "$ currently. \nIf you proceed with this purchase, you will have " + remainMoney + "$ remaining. Would you still like to proceed? y/n");
                        String upgradeProceed = input.next();

                        switch (upgradeProceed) {
                            case "y" -> {
                                money -= randPrice;
                                playerCar.reliability += randAmount;
                                System.out.println(playerCar.reliability - randAmount + " -> " + playerCar.reliability);
                                System.out.println("Your reliability went up with " + randAmount + " points for " + randPrice + "$");
                            }
                            case "n" -> System.out.println("Going back...");
                            default -> System.out.println("Please type in 'y' for yes, and 'n' for no.");
                        }

                    } else {
                        System.out.println("Insufficient funds... You only have " + money);

                    }
                    break;

                case 4:

                    if (money >= randPrice) {
                        System.out.println("This upgrade costs " + randPrice + "$, you have " + money + "$ currently. \nIf you proceed with this purchase, you will have " + remainMoney + "$ remaining. Would you still like to proceed? y/n");
                        String upgradeProceed = input.next();

                        switch (upgradeProceed) {
                            case "y" -> {
                                money -= randPrice;
                                playerCar.weight += randAmount;
                                System.out.println(playerCar.weight - randAmount + " -> " + playerCar.weight);
                                System.out.println("Your weight went up with " + randAmount + " points for " + randPrice + "$");
                            }
                            case "n" -> System.out.println("Going back...");
                            default -> System.out.println("Please type in 'y' for yes, and 'n' for no.");
                        }


                    } else {
                        System.out.println("Insufficient funds... You only have " + money);

                    }
                    break;

                case 5:
                    System.out.println("Research and Upgrades are your chance to make your cars performance better. \nWhen you buy an upgrade, your rating will go up by 1 to 3 points for that attribute. \nThere is a chance to fail your research and therefore pay for nothing. \nTo upgrade, simply type in 1 to 4 to upgrade the attribute you want!\n");
                    break;

                case 6:
                    MainMenu();
                    break;
            }

            // Handle confirmation for upgrade purchase (loop for valid input)
            if (upgradeChoice >= 1 && upgradeChoice <= 4 && money >= randPrice) {
                String upgradeProceed;
                do {
                    System.out.println("This upgrade costs " + randPrice + "$, you have " + money + "$ currently. \nIf you proceed with this purchase, you will have " + remainMoney + "$ remaining. Would you still like to proceed? y/n");
                    upgradeProceed = input.next().toLowerCase(); // Convert to lowercase for easier comparison
                } while (!upgradeProceed.equals("y") && !upgradeProceed.equals("n")); // Repeat until valid input (y or n)

                switch (upgradeProceed) {
                    case "y":
                        money -= randPrice;
                        playerCar.engine += randAmount; // Assuming engine upgrade (adjust for other cases)
                        System.out.println(playerCar.engine - randAmount + " -> " + playerCar.engine);
                        System.out.println("Your engine went up with " + randAmount + " points for " + randPrice + "$");
                        break;
                    case "n":
                        System.out.println("Going back...");
                        break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Please try again! Type in the numbers on the side of the path to continue. \n In this case, type 1, 2, 3 or 4");
            input.nextLine();
        }
        Upgrades();

    }

    private void BotStats() { //This prints every rating for each car to the player, so that they can compare different teams
        System.out.println("Cars bot stat");
        DisplayStats();

        System.out.println("Type anything to return to the main menu.");
        input.nextLine();
        MainMenu();
    }

    private void DisplayStats() {

//This is the hashmap created in the start of game.java. It's made so that the code can print the objects attributes
        team.put(playerCar.name, playerCar);
        team.put(Redbull.name, Redbull);
        team.put(Ferrari.name, Ferrari);
        team.put(Mercedes.name, Mercedes);
        team.put(Mclaren.name, Mclaren);
        team.put(Aston_Martin.name, Aston_Martin);
        team.put(Alpine.name, Alpine);
        team.put(Williams.name, Williams);
        team.put(Haas.name, Haas);
        team.put(Stake.name, Stake);

        //For every "team" this code is looped. It takes the value (which is the teams name in this case) and places it in the variable "carName"
        //That way, its possible to print for example Redbulls engine rating, as well as Mclarens. This is instead of making 10 different blocks of code for each team
        for (Car carName : team.values()) {
            System.out.println(carName.name + ": Engine: " + carName.engine + ", Aerodynamic: " + carName.aerodynamics +
                    " , Reliability: " + carName.reliability + " , Weight: " + carName.weight);
            System.out.println();
        }
    }

    private void PredictedPosition() {
        // Sort every car in order based on their average stat (lowest to highest) and found it easiest with an arraylist
        //This is to make a prediction on where the user should end up in the race to earn a money bonus.
        ArrayList<Double> allCars = new ArrayList<>();
        allCars.add(playerCar.averageStats());
        allCars.add(Redbull.averageStats());
        allCars.add(Ferrari.averageStats());
        allCars.add(Mercedes.averageStats());
        allCars.add(Stake.averageStats());
        allCars.add(Mclaren.averageStats());
        allCars.add(Aston_Martin.averageStats());
        allCars.add(Alpine.averageStats());
        allCars.add(Williams.averageStats());
        allCars.add(Haas.averageStats());

        Collections.sort(allCars);

        //this places the players car in a positional ranking based on average stats. Best stats = 0, worst = 9
        double gridPlacement = allCars.indexOf(playerCar.averageStats());

        //Sets up goal based on where the players team is on the grid. This goal is later used for the player to earn a bonus.
        if (gridPlacement < 4) { //If the team is placed from 6-10 worst, they will have race position 5 as goal
            raceGoal = 5;
        } else if (gridPlacement <= 4 & gridPlacement > 2) { //If team is 5 or 4, the goal is p3
            raceGoal = 3;
        } else if (gridPlacement >= 2) { // top 3's goal is first place
            raceGoal = 1;
        }

        worstTeamRating = Collections.min(allCars); //this one takes the worst rating and saves it, it's used for the race-sim later
    }
}






