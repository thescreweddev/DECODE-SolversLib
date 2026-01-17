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
                -5e-7 * Math.pow(ds, 4)
                        + 0.000112278 * Math.pow(ds, 3)
                        - 0.00909833 * Math.pow(ds, 2)
                        + 0.296956 * ds
                        - 2.345,
                0, 1
        );
    }

    public static double power(double ds) {
        return MathFunctions.clamp(
                1.83333e-7 * Math.pow(ds, 4)
                        - 0.0000357778 * Math.pow(ds, 3)
                        + 0.002455 * Math.pow(ds, 2)
                        - 0.0652413 * ds
                        + 1.16971,
                0, 1
        );
    }
}
