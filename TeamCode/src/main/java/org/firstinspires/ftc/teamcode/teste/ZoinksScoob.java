package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;

@TeleOp
public class ZoinksScoob extends CommandOpMode {
    public GamepadEx gm1;
    public ShootingSubsystem shooter;

    @Override
    public void initialize() {

        shooter = new ShootingSubsystem(hardwareMap);
        gm1 = new GamepadEx(gamepad1);

        gm1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new InstantCommand(()-> shooter.shut.setVelocity(6000))
        ).whenReleased(
                new InstantCommand(()-> shooter.shut.setVelocity(0))
        );

        gm1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new InstantCommand(()-> shooter.shoot(1))
        ).whenReleased(
                new InstantCommand(()-> shooter.shoot(0))
        );

    }

    @Override
    public void run() {
        super.run();
        schedule(

        );



        telemetry.addData("Velocity:", shooter.shut.getVelocity());
        telemetry.addData("Velocity:", shooter.shut2.getVelocity());

        telemetry.update();


    }
}
