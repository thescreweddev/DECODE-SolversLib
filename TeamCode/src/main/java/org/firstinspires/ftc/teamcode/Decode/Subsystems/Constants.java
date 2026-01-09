package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.pedropathing.math.MathFunctions;

import org.opencv.core.Mat;

public class Constants {

    public static double angle(double ds){
        return MathFunctions.clamp( 2.10417e-7 * Math.pow(ds,4) - 0.0000355417 * Math.pow(ds, 3) +  0.00205521 * Math.pow(ds, 2) -0.0667762 * ds + 1.93661  ,0,1 );
    }

    public static double power(double ds){
        return MathFunctions.clamp(1.66667e-7 * Math.pow(ds,4) -0.0000359259 * Math.pow(ds,3) +0.00286111 * Math.pow(ds, 2) -0.0941852 * ds +1.79333 ,0,1 );
    }
}
