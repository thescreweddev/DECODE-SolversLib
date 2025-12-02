package org.firstinspires.ftc.teamcode.Decode.TeleOp;

import com.bylazar.gamepad.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Decode.Commands.FieldCentric;
import org.firstinspires.ftc.teamcode.Decode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends CommandOpMode {


    DriveSubsystem driveSubsystem;
   // IntakeSubsystem intakeSubsystem;
   // IntakeCommand intakeCommand;
   // SortSubsystem sortSubsystem;
    FieldCentric drive;
    GamepadEx gm1;

    @Override
    public void initialize() {
        driveSubsystem = new DriveSubsystem(hardwareMap);
        gm1 = new GamepadEx(gamepad1);

        drive = new FieldCentric(      //initializare comanda de drive prin constructorul specific si valorile de la stick uri
                driveSubsystem,
                gm1::getLeftX,
                gm1::getLeftY,
                gm1::getRightX
        );
        driveSubsystem.setDefaultCommand(drive);
        telemetry.addData("STATS:","DONE UPDATE");
        telemetry.update();

      //  CommandScheduler.getInstance().setDefaultCommand(intakeSubsystem,intakeCommand);
    }

   //schedule ;
   //button   ;
    public void run(){
        super.run();
        telemetry.addData("PINPINT_HEADING",driveSubsystem.pinpoint.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}
