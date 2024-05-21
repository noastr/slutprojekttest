import java.util.Random;

public class Driver  {

    private double skill;


    Random random = new Random();

    public Driver(String name, int engineStats, int aerodynamicStats, int reliabilityStats, int weightStats, double pointsRandom) {
        //super(name, engineStats, aerodynamicStats, reliabilityStats, weightStats, pointsRandom);
    }

    double randomSkill = random.nextDouble(1, 21);

    //@Override
    public double averageStats() {
        skill = averageStats() + randomSkill;
        return skill;
    }
}