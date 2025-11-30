package org.firstinspires.ftc.teamcode.Decode.Subsystems;

import com.bylazar.gamepad.Gamepad;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import java.util.function.Supplier;

public class DriveSubsystem extends SubsystemBase {

    MecanumDrive mecanumdrive;
    public GoBildaPinpointDriver pinpoint;
    Motor fr,fl,br,bl;
    //

public DriveSubsystem(HardwareMap hardwaremap){
    super();

    pinpoint = hardwaremap.get(GoBildaPinpointDriver.class, "pinpoint");

    fr = new Motor(hardwaremap,"fr", Motor.GoBILDA.RPM_312);
    fl = new Motor(hardwaremap,"fl", Motor.GoBILDA.RPM_312);
    br = new Motor(hardwaremap,"br", Motor.GoBILDA.RPM_312);
    bl = new Motor(hardwaremap,"bl", Motor.GoBILDA.RPM_312);

    fr.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    fl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    br.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    bl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    fr.setInverted(true);
    fl.setInverted(true);

    this.mecanumdrive = new MecanumDrive(false, fl, fr, bl, br);

}
    public void driveFieldCentric(double strafeSpeed, double forwardSpeed, double turnSpeed, double angles){
            mecanumdrive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, angles);  //squaredInputs??
    }

}
