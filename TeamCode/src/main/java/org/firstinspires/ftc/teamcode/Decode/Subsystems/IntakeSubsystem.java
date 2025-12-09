package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class IntakeSubsystem extends SubsystemBase {

    Motor intLeft,intRight;
    public static boolean canIntake = true;
    public IntakeSubsystem(HardwareMap hardwareMap){
        super();
        intLeft = new Motor(hardwareMap,"iL", Motor.GoBILDA.RPM_1150);
        intRight = new Motor(hardwareMap,"iR", Motor.GoBILDA.RPM_1150);

        intLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        intRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        intRight.setInverted(true);
    }
    public void active(){
        intLeft.set(1);
        intRight.set(1);
    }
    public void idle(){
        intLeft.set(0);
        intRight.set(0);
    }
    public void spit(){
        intLeft.set(-1);
        intRight.set(-1);
    }

    @Override
    public void periodic() {
        super.periodic();
//        if(canIntake){
//            active();
//        }else{
//            idle();
//        }
    }


}
