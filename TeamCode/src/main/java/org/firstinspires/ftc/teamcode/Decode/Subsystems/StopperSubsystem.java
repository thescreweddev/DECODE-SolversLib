package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class StopperSubsystem extends SubsystemBase {

    public ServoEx stopper;

    public StopperSubsystem(HardwareMap hardwareMap){
        super();
        stopper = new ServoEx(hardwareMap, "stopper");

    }

    public void Stop(){
        stopper.set(1);
    }
    public void Retract(){
        stopper.set(0.65);
    }
}
