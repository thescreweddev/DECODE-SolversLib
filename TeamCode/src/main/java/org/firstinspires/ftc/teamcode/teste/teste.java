package org.firstinspires.ftc.teamcode.teste;

import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.DriveSubsystem;

@TeleOp
public class teste extends OpMode {
    DriveSubsystem driveSubsystem;
    Servo servo;
    @Override
    public void init() {
        driveSubsystem = new DriveSubsystem(hardwareMap);
        servo = hardwareMap.get(Servo.class,"spin");
    }

    @Override
    public void loop() {
        if(gamepad1.x){
            driveSubsystem.fl.set(1);
            servo.setPosition(0);
        } else if (gamepad1.y) {
            driveSubsystem.fr.set(1);
        }else if (gamepad1.a) {
            driveSubsystem.bl.set(1);
        }else if (gamepad1.b) {
            driveSubsystem.br.set(1);
        }else{
            driveSubsystem.fl.set(0);
            driveSubsystem.fr.set(0);
            driveSubsystem.bl.set(0);
            driveSubsystem.br.set(0);
        }
        telemetry.addData("FRONLEFT",gamepad1.x);
        telemetry.addData("FRONTRIGHT",gamepad1.y);
        telemetry.addData("BACKLEFT",gamepad1.a);
        telemetry.addData("BACKRIGHT",gamepad1.b);
        telemetry.update();


    }
}
