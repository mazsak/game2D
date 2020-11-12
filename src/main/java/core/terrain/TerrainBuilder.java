package core.terrain;


public interface TerrainBuilder {

    void setSize(int width, int height);
    void buildGrass(int number);
    void buildWater(int number);
    void buildFlower(int number);
    void build();
    void addLineToMapString(String line);
    String pack();
}
