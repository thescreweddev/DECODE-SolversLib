package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TuretSubsystem extends SubsystemBase {

    public Servo rotire;

    public TuretSubsystem(HardwareMap hardwareMap){
        super();
        rotire = hardwareMap.get(Servo.class,"ajustare");
    }

    public void setPosition(double dis){
        rotire.setPosition(dis);
    }


}
