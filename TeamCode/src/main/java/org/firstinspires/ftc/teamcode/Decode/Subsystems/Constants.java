package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.MathFunctions;

import org.opencv.core.Mat;


@Configurable
public class Constants {






//    public static double angle(double ds){
//        return MathFunctions.clamp( 3.05807e-8 * Math.pow(ds,4) - 0.0000109878 * Math.pow(ds, 3) +  0.00155972 * Math.pow(ds, 2) -0.105015 * ds + 2.97808  ,0,1 );
//    }

//    public static double power(double ds){
//        return MathFunctions.clamp(1.45699e-8 * Math.pow(ds,4) -0.0000041059 * Math.pow(ds,3) +0.000355953 * Math.pow(ds, 2) -0.00686338 * ds +0.653685 ,0,1 );
//    }
public static double power(double ds) {
    return MathFunctions.clamp(
            3.8125e-7 * Math.pow(ds, 4)
                    - 0.0000844769 * Math.pow(ds, 3)
                    + 0.00671618 * Math.pow(ds, 2)
                    - 0.217613 * ds
                    + 2.86277,
            0, 1
    );
}

    public static double angle(double ds) {
        return MathFunctions.clamp(
                6.6875e-7 * Math.pow(ds, 4)
                        - 0.000146579 * Math.pow(ds, 3)
                        + 0.012018 * Math.pow(ds, 2)
                        - 0.452309 * ds
                        + 7.04644,
                0, 1
        );
    }
}
