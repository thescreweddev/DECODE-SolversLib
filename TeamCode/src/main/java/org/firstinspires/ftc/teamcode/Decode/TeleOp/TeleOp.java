package org.firstinspires.ftc.teamcode.Decode.TeleOp;

import com.bylazar.gamepad.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Decode.Commands.AutoRotateCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.FieldCentric;
import org.firstinspires.ftc.teamcode.Decode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.ShootSequence;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends CommandOpMode {

    int colorcase=1;

    DriveSubsystem driveSubsystem;
    SortSubsystem sortSubsystem;
    IntakeSubsystem intakeSubsystem;
    ShootingSubsystem shooter;
    public Slot.BallColor[] matchCase;

    Slot.BallColor[] colorCase = {

            Slot.BallColor.GREEN,
            Slot.BallColor.VIOLET,
            Slot.BallColor.VIOLET
    };

    FieldCentric drive;
    GamepadEx gm1;

    @Override
    public void initialize() {
        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        shooter = new ShootingSubsystem(hardwareMap);
        sortSubsystem = new SortSubsystem(hardwareMap);


        gm1 = new GamepadEx(gamepad1);

        drive = new FieldCentric(      //initializare comanda de drive prin constructorul specific si valorile de la stick uri
                driveSubsystem,
                gm1::getLeftX,
                gm1::getLeftY,
                gm1::getRightX
        );
        driveSubsystem.setDefaultCommand(drive);

        //                      SETARE COD CULOARE

        gm1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                ()->{
                    colorCase[0] = Slot.BallColor.VIOLET;
                    colorCase[1] = Slot.BallColor.GREEN;
                    colorCase[2] = Slot.BallColor.VIOLET;
                    colorcase =2;
                }
        );
        gm1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                ()->{
                    colorCase[0] = Slot.BallColor.GREEN;
                    colorCase[1] = Slot.BallColor.VIOLET;
                    colorCase[2] = Slot.BallColor.VIOLET;
                    colorcase = 1;
                }
        );
        gm1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                ()->{
                    colorCase[0] = Slot.BallColor.VIOLET;
                    colorCase[1] = Slot.BallColor.VIOLET;
                    colorCase[2] = Slot.BallColor.GREEN;
                    colorcase = 3;
                }
        );


        //
        gm1.getGamepadButton(GamepadKeys.Button.CROSS).whenPressed(
                new ShootSequence(sortSubsystem,intakeSubsystem,matchCase)
        );

        telemetry.addData("STATS:","DONE UPDATE");
        telemetry.update();



      //  CommandScheduler.getInstance().setDefaultCommand(intakeSubsystem,intakeCommand);
    }

 //   {button pt shoot si matchcase  ;}

    public void run(){
        super.run();

            schedule(
                    new AutoRotateCommand(intakeSubsystem,sortSubsystem,colorCase)
            );

        telemetry.addData("green",sortSubsystem.colorSensor.green());
        telemetry.addData("blue",sortSubsystem.colorSensor.blue());
        telemetry.addData("red",sortSubsystem.colorSensor.red());
        telemetry.addData("case",colorcase);
        telemetry.update();
    }
}
