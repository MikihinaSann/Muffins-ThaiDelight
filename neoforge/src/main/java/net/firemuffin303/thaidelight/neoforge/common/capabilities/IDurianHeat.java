package net.firemuffin303.thaidelight.neoforge.common.capabilities;

public interface IDurianHeat {
    void setTimer(int value);
    void addTimer(int value);
    int getTimer();
    void setHeat(boolean value);
    boolean isHeatUp();
}
