package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@Configurable
public class ShootingSubsystem extends SubsystemBase {

    public DcMotorEx shut,shut2;

    public static double sht = 0.83     ;
//    public static float idl ;

    public ShootingSubsystem(HardwareMap hardwareMap){
        super();
        shut  = hardwareMap.get(DcMotorEx.class,"shooter");
        shut2 = hardwareMap.get(DcMotorEx.class,"shooter2");
    }
    public void setPower(double sht){
        this.sht = sht;
    }

    public void shoot(double ang){
        shut.setPower(ang);
        shut2.setPower(ang);
    }
    public void idle(){
        shut.setPower(0);
        shut2.setPower(0);
    }
    public void in(){
        shut.setPower(-0.8);
        shut2.setPower(-0.8);
    }










}
