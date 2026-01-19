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
                5.45772e-9 * Math.pow(ds, 4)
                        -0.00000492711 * Math.pow(ds, 3)
                        +0.00110173 * Math.pow(ds, 2)
                        -0.0907883 * ds
                        +2.81918,
                0, 1
        );
    }

    public static double power(double ds) {
        return MathFunctions.clamp(
                1.05277e-7 * Math.pow(ds, 4)
                        -0.0000294312 * Math.pow(ds, 3)
                        +0.00272515 * Math.pow(ds, 2)
                        -0.0966844 * ds
                        +1.83056,
                0, 1
        );
    }
}
