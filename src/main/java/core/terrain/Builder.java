package core.terrain;


public interface Builder {

    void setSize(int width, int height);
    void buildGrass(int number);
    void buildWater(int number);
    void build();
}
