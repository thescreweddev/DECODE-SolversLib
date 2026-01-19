package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class BallDetectionSubsystem extends SubsystemBase {

    public RevColorSensorV3 co1,co2;
    public int ballCount;
    public boolean shooting,canDetectIntake = false, isDone = false,offset = false;

    public BallDetectionSubsystem(HardwareMap hardwareMap){
        super();
        co1 = hardwareMap.get(RevColorSensorV3.class,"c1");
        co2 = hardwareMap.get(RevColorSensorV3.class,"c2");
        ballCount = 0;
    }

    public void ballInside(Gamepad gamepad) {
        if((co1.alpha()>800 || co2.alpha()>800) && shooting == false){
            gamepad.rumble(100);
        }
    }

    public void setBallerState(boolean bull){
        shooting = bull;
    }

    public void autoDetection(boolean CANHEDOIT ){
        canDetectIntake = CANHEDOIT;
    }





}
