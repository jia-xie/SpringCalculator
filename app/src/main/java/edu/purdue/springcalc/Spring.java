package edu.purdue.springcalc;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import edu.purdue.springcalc.exceptions.InvalidDiameter;

public class Spring {
    int endType;
    int material;
    double wireDiameter, outerDiameter, freeLength, solidLength;

    public double calcPitch() {

        if (this.endType == SpringEndType.PLAIN) {
            return (freeLength - wireDiameter) / calcActiveCoil();
        } else if (this.endType == SpringEndType.PLAIN_AND_GROUND) {
            return (freeLength) / (calcActiveCoil() + 1);
        } else if ((this.endType == SpringEndType.SQUARED_OR_CLOSED)) {
            return (freeLength - 3 * wireDiameter) / calcActiveCoil();
        } else if (this.endType == SpringEndType.SQUARED_AND_GROUND) {
            return (freeLength - 2 * wireDiameter) / calcActiveCoil();
        }
        return -1;
    }

    public double calcTotalCoil() {
        if ((this.endType == SpringEndType.PLAIN) || (this.endType == SpringEndType.SQUARED_OR_CLOSED)) {
            return (solidLength / wireDiameter) - 1;
        } else if ((this.endType == SpringEndType.PLAIN_AND_GROUND) || (this.endType == SpringEndType.SQUARED_AND_GROUND)) {
            return (solidLength / wireDiameter);
        }
        return -1;
    }

    public double calcActiveCoil() {
        if (this.endType == SpringEndType.PLAIN) {
            return calcTotalCoil();
        } else if (this.endType == SpringEndType.PLAIN_AND_GROUND) {
            return calcTotalCoil() - 1;
        } else if ((this.endType == SpringEndType.SQUARED_OR_CLOSED) || (this.endType == SpringEndType.SQUARED_AND_GROUND)) {
            return calcTotalCoil() - 2;
        }
        return -1;
    }

    public double calcSpringRate() {
        return pow(10, 6) * (pow(wireDiameter, 4) * SpringMaterial.getShearModulus(wireDiameter, material)) /
                (8 * pow((outerDiameter - wireDiameter), 3) * calcActiveCoil());
    }

    public double calcForce() {
        return calcSpringRate() * (freeLength - solidLength) / 1000;
    }

    public double calcFoS() throws InvalidDiameter {
        double C = (outerDiameter - wireDiameter) / wireDiameter;
        double K = (4 * C + 2) / (4 * C - 3);
        double shearStress = K * ((8 * calcForce() * (outerDiameter - wireDiameter)) /
                (PI * pow(wireDiameter, 3)));
        try {
            return SpringMaterial.getYieldStrength(wireDiameter, material) / shearStress;
        } catch (InvalidDiameter e) {
            throw e;
        }
    }

}
