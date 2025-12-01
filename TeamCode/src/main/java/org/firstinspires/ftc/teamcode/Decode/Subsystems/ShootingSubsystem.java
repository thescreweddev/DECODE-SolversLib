package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class ShootingSubsystem extends SubsystemBase {

    Motor shooter;

    public ShootingSubsystem(HardwareMap hardwareMap){
        super();
        shooter = new Motor(hardwareMap,"shooter");
    }

    public void shoot(){
        shooter.set(1);
    }
    public void idle(){
        shooter.set(0);
    }

}
