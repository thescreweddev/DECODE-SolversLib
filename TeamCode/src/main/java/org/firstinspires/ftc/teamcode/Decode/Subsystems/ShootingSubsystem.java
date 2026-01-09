package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class ShootingSubsystem extends SubsystemBase {

    Motor shooter;

    public static double sht = 0.83     ;
//    public static float idl ;

    public ShootingSubsystem(HardwareMap hardwareMap){
        super();
        shooter = new Motor(hardwareMap,"shooter");
    }
    public void setPower(double sht){
        this.sht = sht;
    }

    public void shoot(double ang){
        shooter.set(ang);
    }
    public void idle(){
        shooter.set(0);
    }
    public void in(){shooter.set(-0.8);}










}
