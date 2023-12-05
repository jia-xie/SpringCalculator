package edu.purdue.springcalc;

import static java.lang.Math.pow;

import edu.purdue.springcalc.exceptions.InvalidDiameter;

public class SpringMaterial {
    public static final int MUSIC_WIRE = 0;
    public static final int HARD_DRAWN = 1;
    public static final int CHROME_VANADIUM = 2;
    public static final int CHROME_SILICON = 3;
    public static final int STAINLESS = 4;
    public static final int PHOSPHOR_BRONZE = 5;

    public static final int[] A = {2211, 1783, 2005, 1974};
    public static final int[] A_STAINLESS = {1867, 2065, 2911};
    public static final int[] A_PB = {145, 121, 110};
    public static final double[] M = {0.145, 0.190, 0.168, 0.108};
    public static final double[] M_STAINLESS = {0.146, 0.263, 0.478};
    public static final double[] M_PB = {0, 0.028, 0.064};

    private static final double MM_TO_INCH = 1 / 25.4;

    /**
     * get the Ultimate strength using Table 10-4 in Shigley textbook
     *
     * @param diameter mm
     * @return Ultimate Strength in MPa
     */
    public static double getUltimateStrength(double diameter, int materialType) throws InvalidDiameter {
        switch (materialType) {
            case MUSIC_WIRE:
                if (diameter < 0.1 || diameter > 6.5) {
                    throw new InvalidDiameter("Range of diameter for music wire is [0.1, 6.5] mm");
                }

            break;case HARD_DRAWN:
                if (diameter < 0.7 || diameter > 12.7) {
                    throw new InvalidDiameter("Range of diameter for hard-drawn wire is [0.7, 12.7] mm");
                }
            break;case CHROME_VANADIUM:
                if (diameter < 0.8 || diameter > 11.1) {
                    throw new InvalidDiameter("Range of diameter for chrome-vanadium wire wire is [0.8, 11.1] mm");
                }
            break;case CHROME_SILICON:
                if (diameter < 1.6 || diameter > 9.5) {
                    throw new InvalidDiameter("Range of diameter for chrome-silicon wire wire is [1.6, 9.5] mm");
                }
            break;case STAINLESS:
                if (diameter < 0.3 || diameter > 10) {
                    throw new InvalidDiameter("Range of diameter for 302 Stainless wire wire is [0.3, 10] mm");
                }
            break;case PHOSPHOR_BRONZE:
                if (diameter < 0.1 || diameter > 7.5) {
                    throw new InvalidDiameter("Range of diameter for phosphor-bronze wire wire is [0.1, 7.5] mm");
                }
        }

        if ((materialType == MUSIC_WIRE) || (materialType == HARD_DRAWN) ||
                (materialType == CHROME_VANADIUM) || (materialType == CHROME_SILICON)) {
            return (A[materialType] / pow(diameter, M[materialType]));
        } else if (materialType == STAINLESS) {
            if (diameter >= 0.3 && diameter < 2.5) {
                return (A_STAINLESS[0] / pow(diameter, M_STAINLESS[0]));
            } else if (diameter >= 2.5 && diameter < 5) {
                return (A_STAINLESS[1] / pow(diameter, M_STAINLESS[1]));
            } else {
                return (A_STAINLESS[2] / pow(diameter, M_STAINLESS[2]));
            }
        } else {
            if (diameter >= 0.1 && diameter < 0.6) {
                return (A_PB[0] / pow(diameter, M_PB[0]));
            } else if (diameter >= 0.6 && diameter < 2) {
                return (A_PB[1] / pow(diameter, M_PB[1]));
            } else {
                return (A_PB[2] / pow(diameter, M_PB[2]));
            }
        }
    }

    public static double getYieldStrength(double diameter, int materialType) throws InvalidDiameter {

        switch (materialType) {
            case MUSIC_WIRE:
            case HARD_DRAWN:
            case STAINLESS:
            case PHOSPHOR_BRONZE:
                return 0.45 * SpringMaterial.getUltimateStrength(diameter, materialType);
            case CHROME_VANADIUM:
            case CHROME_SILICON:
                return 0.65 * SpringMaterial.getUltimateStrength(diameter, materialType);
        }
        return -1;
    }
    /**
     * calculate the young's modulus
     *
     * @param diameter     in mm
     * @param materialType material type
     * @return in GPa
     */
    public static double getYoungModulus(double diameter, int materialType) {
        diameter = diameter * MM_TO_INCH;
        switch (materialType) {
            case MUSIC_WIRE:
                if (diameter < 0.0325) {
                    return 203.4;
                } else if (diameter >= 0.0325 && diameter < 0.0635) {
                    return 200;
                } else if (diameter >= 0.0635 && diameter < 0.1255) {
                    return 196.5;
                } else {
                    return 193;
                }

            case HARD_DRAWN:
                if (diameter < 0.0325) {
                    return 198.6;
                } else if (diameter >= 0.0325 && diameter < 0.0635) {
                    return 197.9;
                } else if (diameter >= 0.0635 && diameter < 0.1255) {
                    return 197.2;
                } else {
                    return 196.5;
                }
            case CHROME_VANADIUM:
            case CHROME_SILICON:
                return 203.4;

            case STAINLESS:
                return 193;
            case PHOSPHOR_BRONZE:
                return 103.4;
        }
        return 0;
    }
    /**
     * calculate the yield strength
     *
     * @param diameter     in mm
     * @param materialType material type
     * @return in GPa
     */
    public static double getShearModulus(double diameter, int materialType) {
        diameter = diameter * MM_TO_INCH;
        switch (materialType) {
            case MUSIC_WIRE:
                if (diameter < 0.0325) {
                    return 82.7;
                } else if (diameter >= 0.0325 && diameter < 0.0635) {
                    return 81.7;
                } else if (diameter >= 0.0635 && diameter < 0.1255) {
                    return 81;
                } else {
                    return 80;
                }

            case HARD_DRAWN:
                if (diameter < 0.0325) {
                    return 80.7;
                } else if (diameter >= 0.0325 && diameter < 0.0635) {
                    return 80.0;
                } else if (diameter >= 0.0635 && diameter < 0.1255) {
                    return 79.3;
                } else {
                    return 78.6;
                }
            case CHROME_VANADIUM:
            case CHROME_SILICON:
                return 77.2;

            case STAINLESS:
                return 69;
            case PHOSPHOR_BRONZE:
                return 41.4;
        }
        return 0;
    }
}
