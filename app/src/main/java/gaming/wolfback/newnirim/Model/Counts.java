package gaming.wolfback.newnirim.Model;

/**
 * Created by Jarren on 3/19/2016.
 */
public class Counts {
    //**************Nightmare stuff******************************************//
    public int getNightmareCount() {
        return nightmareCount;
    }

    public void incrementNightmareCount() {
        this.nightmareCount++;
    }
    private int nightmareCount = 0;

//********************Door stuff************************************************//
    public int getRedDoorCount() {
        return redDoorCount;
    }

    public int getBlueDoorCount() {
        return blueDoorCount;
    }

    public int getGreenDoorCount() {
        return greenDoorCount;
    }

    public int getBrownDoorCount() {
        return brownDoorCount;
    }

    public void incrementRedDoorCount() {
        this.redDoorCount++;
    }


    public void incrementGreenDoorCount() {
        this.greenDoorCount++;
    }

    public void incrementBlueDoorCount() {
        this.blueDoorCount++;
    }

    public void incrementBrownDoorCount() {
        this.brownDoorCount++;
    }

    private int redDoorCount = 0;
    private int greenDoorCount = 0;
    private int blueDoorCount = 0;
    private int brownDoorCount = 0;
}
