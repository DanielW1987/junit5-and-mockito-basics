package rocks.danielw.service;

public class FlowerService {

  public String analyze(String value) {
    switch (value) {
      case "puppy":
      case "poppy":
        return "puppy or poppy";
      default:
          return "Default";
    }
  }

  public boolean isABigFlower(String name, int petals) {
    return true;
  }

}
