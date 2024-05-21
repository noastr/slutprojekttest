public class Vehicle { // Thought on having other vehicles as well, like supercars or motorcycles
    public String name; //the name of the team
    public int engine; //the rating for engine

    //every car is a vehicle and has an engine and a name
    public Vehicle(String name, int engineStats) {
        this.name = name;
        engine = engineStats;
    }
}