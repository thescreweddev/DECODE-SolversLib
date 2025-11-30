package org.firstinspires.ftc.teamcode.teste;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp
public class SenzorCuloare extends LinearOpMode {
    ColorSensor c1,c2,c3;
    CRServo servo;

    @Override
    public void runOpMode(){
        c1 = hardwareMap.get(ColorSensor.class, "c1");
        c2 = hardwareMap.get(ColorSensor.class, "c2");
        c3 = hardwareMap.get(ColorSensor.class, "c3");
        servo = hardwareMap.get(CRServo.class, "servo");

        waitForStart();
        while(opModeIsActive()){

            servo.setPower(1);
            telemetry.addData("red1", c1.red());
            telemetry.addData("green1", c1.green());
            telemetry.addData("blue1",c1.green());
            telemetry.addData("alpha1",c1.alpha());

            telemetry.addData("red2", c2.red());
            telemetry.addData("green2", c2.green());
            telemetry.addData("blue2",c2.green());
            telemetry.addData("alpha2",c2.alpha());

            telemetry.addData("red3", c3.red());
            telemetry.addData("green3", c3.green());
            telemetry.addData("blue3",c3.green());
            telemetry.addData("alpha3",c3.alpha());

            telemetry.update();
        }
    }
}
