package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TuretSubsystem extends SubsystemBase {

    public CRServoEx rotire;
    public ServoEx angle;
    public Limelight3A limelight;
    public double posRotire;

    public TuretSubsystem(HardwareMap hardwareMap){
        super();
        rotire = new CRServoEx(hardwareMap,"rotire");
        angle = new ServoEx(hardwareMap,"angle");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

        posRotire = 0;
    }



}
