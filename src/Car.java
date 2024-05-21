public class Car extends Vehicle {
    public int aerodynamics; // -||- for aero
    public int reliability; // -||- for reliability
    public int weight; // -||- for weight
    public double points; //is the interval that gets randomized for the race-sim


    //The Cars attributes is linked to an array
    public Car(String name, int engineStats, int aerodynamicStats, int reliabilityStats, int weightStats, double pointsRandom) {
        super(name, engineStats);
        setEngine(engineStats);
        setAerodynamics(aerodynamicStats);
        setReliability(reliabilityStats);
        setWeight(weightStats);
        setPoints(pointsRandom);

        averageStats();
    }

    //This takes the 4 values of each car and makes an average rating for the car. This is used to see where each car's performance is, compared to each other.
    public double averageStats() {
        double sum = 0.0;
        sum += getEngine();
        sum += getAerodynamics();
        sum += getReliability();
        sum += getWeight();
        return sum / 4;
    }

    public double pointSystem() { //Makes the interval that each car has to randomize
        setPoints(averageStats() - Game.worstTeamRating + 5);
        return getPoints();
    }
    public int getAerodynamics() {
        return aerodynamics;
    }

    public void setAerodynamics(int aerodynamics) {
        this.aerodynamics = aerodynamics;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getEngine(){
        return engine;
    }

    public void setEngine(int engine){
        this.engine = engine;
    }
}
