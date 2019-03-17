import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    int d = 0; // Difficulty
    Simulator s = new Simulator(d);
    Region[] rs = {
        new Region("North East", 15000000 , s),
        new Region("South", 50000000, s),
        new Region("Midwest", 50000000, s),
        new Region("West Coast", 100000000, s)
    };
    s.regions = rs;
    s.setDifficulty(d);
    while (true) {
      Event makeFacts = new Event(0, 2, "make Factories " + s.date, s.date);
      makeFacts.badIndustry = 5;
      makeFacts.appliesTo = rs[0];
      ArrayList<Event> res = new ArrayList();
      Event riots = new Event(1, 1, "Riots", s.date + 2);
      riots.appliesTo = makeFacts.appliesTo;
      riots.text = "Riots in " + riots.appliesTo.name;
      res.add(riots);
      makeFacts.resultingEvents = res;
      ArrayList<Event> events = new ArrayList();
      events.add(makeFacts);
      s.tick(events);
      for (Event event : s.results) {
        //if(event.triggerTime == s.date) {
          System.out.println(event.name);
          System.out.println(event.text);
        //}
      }
      System.out.println("Pop: " + s.population);
      System.out.println("dPop: " + s.dPopulation);
      System.out.println("cO2: " + s.cO2);
      System.out.println("dcO2: " + s.dcO2);
      System.out.println("dcO2 of region: " + s.regions[0].dcO2);
      System.out.println("badIndustry: " + s.regions[0].badIndustry);
      System.out.println("avgTemp: " + s.avgTemp);
      System.out.println("dAvgTemp: " + s.davgTemp);
      System.out.println("seaLevel: " + s.seaLevel);
      System.out.println("dSeaLevel: " + s.dseaLevel);
      System.out.println("animalSpecies: " + s.animalSpecies);
      System.out.println("date: " + s.date);
      System.out.println("-------------------------");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
