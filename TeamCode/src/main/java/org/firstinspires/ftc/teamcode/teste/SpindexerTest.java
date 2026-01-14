package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


import org.firstinspires.ftc.teamcode.Decode.Commands.PushBallCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.ResetSorterCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.RetractPusherCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.RotateToShoot;
import org.firstinspires.ftc.teamcode.Decode.Commands.RotateToSlotCommand;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;

@TeleOp
public class SpindexerTest extends CommandOpMode {

    SortSubsystem sorter;
    StopperSubsystem stopper;
    IntakeSubsystem intake;
    ShootingSubsystem shooter;
    GamepadEx gm1,gm2;

    @Override
    public void initialize() {
        stopper = new StopperSubsystem(hardwareMap);
        sorter = new SortSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShootingSubsystem(hardwareMap);
        gm1 = new GamepadEx(gamepad1);
        gm2 = new GamepadEx(gamepad2);

//-------------------------------------------------------------------------------------

        gm2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new RotateToSlotCommand(sorter,0)
        );
        gm2.getGamepadButton(GamepadKeys.Button.TRIANGLE).whenPressed(
                new RotateToShoot(sorter,0)
        );

        gm2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new RotateToSlotCommand(sorter,1)
        );
        gm2.getGamepadButton(GamepadKeys.Button.SQUARE).whenPressed(
                new RotateToShoot(sorter,1)
        );

        gm2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new RotateToSlotCommand(sorter,2)
        );
        gm2.getGamepadButton(GamepadKeys.Button.CROSS).whenReleased(
                new RotateToShoot(sorter,2)
        );

        //---------------------------------------------------------------------------

        gm1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenHeld(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.active()),
                        new InstantCommand(()-> stopper.Stop())
                )

        ).whenReleased(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.idle()),
                        new InstantCommand(()-> stopper.Retract())
                )        );
        gm1.getGamepadButton(GamepadKeys.Button.CIRCLE).whenHeld(
                new InstantCommand(()-> shooter.shoot(1))
        ).whenReleased(
                new InstantCommand(()-> shooter.idle())
        );
        gm1.getGamepadButton(GamepadKeys.Button.TRIANGLE).whenHeld(
                new PushBallCommand(sorter)
        ).whenReleased(
                new RetractPusherCommand(sorter)
        );

    }


    @Override
    public void run() {
        super.run();

//        telemetry.addData("red",sorter.colorSensor.red());
//        telemetry.addData("blue",sorter.colorSensor.blue());
//        telemetry.addData("green",sorter.colorSensor.green());
        telemetry.update();
    }
}
