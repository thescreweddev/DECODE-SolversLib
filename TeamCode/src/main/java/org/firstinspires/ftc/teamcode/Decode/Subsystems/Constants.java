package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.pedropathing.math.MathFunctions;

import org.opencv.core.Mat;

public class Constants {


//    public static double angle(double ds){
//        return MathFunctions.clamp( 3.05807e-8 * Math.pow(ds,4) - 0.0000109878 * Math.pow(ds, 3) +  0.00155972 * Math.pow(ds, 2) -0.105015 * ds + 2.97808  ,0,1 );
//    }

//    public static double power(double ds){
//        return MathFunctions.clamp(1.45699e-8 * Math.pow(ds,4) -0.0000041059 * Math.pow(ds,3) +0.000355953 * Math.pow(ds, 2) -0.00686338 * ds +0.653685 ,0,1 );
//    }
    public static double angle(double ds) {
        return MathFunctions.clamp(
                6.14299e-8 * Math.pow(ds, 4)
                        - 0.0000205051 * Math.pow(ds, 3)
                        + 0.00254934 * Math.pow(ds, 2)
                        - 0.145368 * ds
                        + 3.5316,
                0, 1
        );
    }

    public static double power(double ds) {
        return MathFunctions.clamp(
                2.05905e-8 * Math.pow(ds, 4)
                        - 0.00000597677 * Math.pow(ds, 3)
                        + 0.000551306 * Math.pow(ds, 2)
                        - 0.0148477 * ds
                        + 0.763353,
                0, 1
        );
    }
}
