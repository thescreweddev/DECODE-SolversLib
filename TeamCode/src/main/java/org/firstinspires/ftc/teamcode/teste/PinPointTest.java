package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp
public class PinPointTest extends OpMode {


    GoBildaPinpointDriver pinpoint;


    @Override
    public void init() {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.resetPosAndIMU();



    }

    @Override
    public void loop() {
        pinpoint.update();
        telemetry.addData("HEADING", pinpoint.getHeading(AngleUnit.DEGREES));
        telemetry.update();

    }
}
