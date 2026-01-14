package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@Configurable
public class ShootingSubsystem extends SubsystemBase {

    public MotorEx shut;

    public static double sht = 0.83     ;
//    public static float idl ;

    public ShootingSubsystem(HardwareMap hardwareMap){
        super();
        shut = new MotorEx(hardwareMap,"shooter");
    }
    public void setPower(double sht){
        this.sht = sht;
    }

    public void shoot(double ang){
        shut.set(ang);
    }
    public void idle(){
        shut.set(0);
    }
    public void in(){shut.set(-0.8);}










}
