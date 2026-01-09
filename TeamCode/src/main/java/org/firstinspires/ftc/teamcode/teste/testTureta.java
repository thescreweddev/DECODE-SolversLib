package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;


@TeleOp
public class testTureta extends OpMode {

    public Servo servo;
    public Motor motor;
    public boolean t=false;
    public SortSubsystem sorter;

    public double motorSpeed = 0.85 ,servoPose;


    @Override
    public void init() {
        motor = new Motor(hardwareMap,"shooter");
        servo = hardwareMap.get(Servo.class," ajustare");
        sorter = new SortSubsystem(hardwareMap);
        servo.setPosition(0 );


    }

    @Override
    public void loop() {

        if(gamepad1.dpad_up && motorSpeed<1){
            motorSpeed= motorSpeed + 0.001;

        }
        if(gamepad1.dpad_down && motorSpeed>0){
            motorSpeed= motorSpeed - 0.001;
        }
        if(gamepad1.triangle && servoPose<1){
            servoPose= servoPose + 0.001;
        }
        if(gamepad1.cross && servoPose>0){
            servoPose= servoPose - 0.001;
        }

        if(gamepad1.right_bumper){
            t=true;
        }
        if(gamepad1.left_bumper){
            t=false;
        }

        if(gamepad1.right_stick_button){
            sorter.pushBall();
        }else{
            sorter.retractPusher();
        }

        if(t){
            motor.set(motorSpeed);
        }else{
            motor.set(0);
        }
        servo.setPosition(servoPose);

        telemetry.addData("motorSpeed: ",motorSpeed);
        telemetry.addData("servoPose: ",servoPose);
        telemetry.update();

    }
}
