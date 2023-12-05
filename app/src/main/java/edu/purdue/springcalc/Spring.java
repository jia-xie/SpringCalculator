package edu.purdue.springcalc;

public class Spring {
    int endType;
    int material;
    float wireDiameter, outerDiameter, freeLength, solidLength;

    public float calcPitch() {
        return 0;
    }

    public int calcTotalCoil() {
        return 1;
    }

    public int calcActiveCoil() {
        return 2;
    }

    public float calcSpringRate() {
        return 3;
    }

    public float calcForce() {
        return 4;
    }

    public float calcFoS() {
        return 5;
    }

    public int getEndType() {
        return endType;
    }

    public void setEndType(int endType) {
        this.endType = endType;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public float getWireDiameter() {
        return wireDiameter;
    }

    public void setWireDiameter(float wireDiameter) {
        this.wireDiameter = wireDiameter;
    }

    public float getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(float outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public float getFreeLength() {
        return freeLength;
    }

    public void setFreeLength(float freeLength) {
        this.freeLength = freeLength;
    }

    public float getSolidLength() {
        return solidLength;
    }

    public void setSolidLength(float solidLength) {
        this.solidLength = solidLength;
    }
}
