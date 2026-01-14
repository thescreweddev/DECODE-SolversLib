package org.firstinspires.ftc.teamcode.Decode.TeleOp;

import com.bylazar.gamepad.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Decode.Commands.AutoRotateCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.FieldCentric;
import org.firstinspires.ftc.teamcode.Decode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.PushBallCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.RetractPusherCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.RobotCentric;
import org.firstinspires.ftc.teamcode.Decode.Commands.RotateToShoot;
import org.firstinspires.ftc.teamcode.Decode.Commands.RotateToSlotCommand;
import org.firstinspires.ftc.teamcode.Decode.Commands.ShootSequence;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.BallDetectionSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Constants;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.TuretSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends CommandOpMode {

   // int colorcase=1;

    DriveSubsystem driveSubsystem;
    BallDetectionSubsystem baller;
    SortSubsystem sorter;
    IntakeSubsystem intake;
    ShootingSubsystem shooter;
    StopperSubsystem stopper;
    TuretSubsystem ajustare;

    public static Pose2D pose ;
    public static double robotX = 71.5;
    public static double robotY = 71.5;
    public static double robotHeading = Math.toRadians(37);

    double dx,dy,goalx = 12  ,goaly = 12 ,distance;



    boolean start = false,inter,outint;
   // public Slot.BallColor[] matchCase;

//    Slot.BallColor[] colorCase = {
//
//            Slot.BallColor.GREEN,
//            Slot.BallColor.VIOLET,
//            Slot.BallColor.VIOLET
//    };

    FieldCentric driveFieldCentric;
    RobotCentric driveRobotCentric;
    GamepadEx gm1,gm2;

    @Override
    public void initialize() {
        ajustare = new TuretSubsystem(hardwareMap);
        driveSubsystem = new DriveSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShootingSubsystem(hardwareMap);
        sorter = new SortSubsystem(hardwareMap);
        stopper = new StopperSubsystem(hardwareMap);
        baller = new BallDetectionSubsystem(hardwareMap);



        gm1 = new GamepadEx(gamepad1);
        gm2 = new GamepadEx(gamepad2);

//        driveFieldCentric = new FieldCentric(      //initializare comanda de drive prin constructorul specific si valorile de la stick uri
//                driveSubsystem,
//                gm1::getLeftX,
//                gm1::getLeftY,
//                gm1::getRightX
//        );
//        driveSubsystem.setDefaultCommand(driveFieldCentric);

        driveRobotCentric = new RobotCentric(
                driveSubsystem,
                gm1::getLeftX,
                gm1::getLeftY,
                gm1::getRightX
        );
        driveSubsystem.setDefaultCommand(driveRobotCentric);



        //-------------------------------------------------------------------------------------

        gm1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new ParallelCommandGroup(
                        new RotateToSlotCommand(sorter,0),
                        new InstantCommand(()-> baller.setBallerState(false))
                )
        );
        gm1.getGamepadButton(GamepadKeys.Button.TRIANGLE).whenPressed(
                new ParallelCommandGroup(
                        new RotateToShoot(sorter,0),
                        new InstantCommand(()-> baller.setBallerState(true))
                )
        );

        gm1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new ParallelCommandGroup(
                        new RotateToSlotCommand(sorter,1),
                        new InstantCommand(()-> baller.setBallerState(false))
                )
        );
        gm1.getGamepadButton(GamepadKeys.Button.SQUARE).whenPressed(
                new ParallelCommandGroup(
                        new RotateToShoot(sorter,1),
                        new InstantCommand(()-> baller.setBallerState(true))
                )
        );

        gm1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new ParallelCommandGroup(
                        new RotateToSlotCommand(sorter,2),
                        new InstantCommand(()-> baller.setBallerState(false))
                )
        );
        gm1.getGamepadButton(GamepadKeys.Button.CROSS).whenReleased(
                new ParallelCommandGroup(
                        new RotateToShoot(sorter,2),
                        new InstantCommand(()-> baller.setBallerState(true))
                )
        );

        //---------------------------------------------------------------------------

        gm1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenHeld(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.active()),
                        new InstantCommand(()-> stopper.Retract())
                )

        ).whenReleased(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.idle()),
                        new InstantCommand(()-> stopper.Stop())
                )        );

        //---------------------------------------------------------------------------

        gm1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(
                new InstantCommand(()-> inter = true)
                ).whenReleased(
                new InstantCommand(()-> inter = false)
        );
        gm1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON).whenHeld(
                new PushBallCommand(sorter)
        ).whenReleased(
                new RetractPusherCommand(sorter)
        );


        gm1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON).whenHeld(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.spit()),
                        new InstantCommand(()-> stopper.Retract())
                )
        ).whenReleased(
                new ParallelCommandGroup(
                        new InstantCommand(()-> intake.idle()),
                        new InstantCommand(()-> stopper.Stop())
                )

        );
        //==========================================================================

        gm2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new InstantCommand(()-> shooter.setPower(1))
        );
        gm2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(()-> shooter.setPower(0.83))
        );

        gm1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new InstantCommand(()-> outint = true)
        ).whenReleased(
                new InstantCommand(()-> outint = false)
        );







        //==========================================================================

        gm2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new InstantCommand(()-> driveSubsystem.resetPinpoint())
        ).whenReleased(
                new InstantCommand(()-> gamepad2.rumble(100))
        );





        //                      SETARE COD CULOARE

//        gm1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
//                ()->{
//                    colorCase[0] = Slot.BallColor.VIOLET;
//                    colorCase[1] = Slot.BallColor.GREEN;
//                    colorCase[2] = Slot.BallColor.VIOLET;
//                    colorcase =2;
//                }
//        );
//        gm1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
//                ()->{
//                    colorCase[0] = Slot.BallColor.GREEN;
//                    colorCase[1] = Slot.BallColor.VIOLET;
//                    colorCase[2] = Slot.BallColor.VIOLET;
//                    colorcase = 1;
//                }
//        );
//        gm1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
//                ()->{
//                    colorCase[0] = Slot.BallColor.VIOLET;
//                    colorCase[1] = Slot.BallColor.VIOLET;
//                    colorCase[2] = Slot.BallColor.GREEN;
//                    colorcase = 3;
//                }
//        );


        //
//        gm1.getGamepadButton(GamepadKeys.Button.CROSS).whenPressed(
//                new ShootSequence(sortSubsystem,intakeSubsystem,matchCase)
//        );

        telemetry.addData("STATS:","DONE UPDATE");
        telemetry.update();



      //  CommandScheduler.getInstance().setDefaultCommand(intakeSubsystem,intakeCommand);
    }

 //   {button pt shoot si matchcase  ;}

    public void run(){
        super.run();
        if(!start){
            start = true;
            schedule(
                    new InstantCommand(()-> sorter.rotateToSlot(0)),
                    new WaitCommand(400),
                    new RetractPusherCommand(sorter),
                    new InstantCommand(()-> stopper.Stop())
            );
        }


//            schedule(
//                    new RotateToSlotCommand(sortSubsystem,1)
//            );

        if(inter && !outint){
            shooter.shoot(Constants.power(distance));
        }else if(!inter && outint){
            shooter.shoot(-0.67);                 //haha 67
        }else{
            shooter.idle();
        }

        driveSubsystem.pinpoint.update();
        driveSubsystem.pinpoint.getEncoderX();
        driveSubsystem.pinpoint.getEncoderY();



        pose = driveSubsystem.pinpoint.getPosition();
        robotX =  pose.getX(DistanceUnit.INCH);
        robotY =  pose.getY(DistanceUnit.INCH);
        robotHeading = pose.getHeading(AngleUnit.RADIANS);

        dx = goalx - robotX;
        dy = goaly - robotY;

        distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));



        telemetry.addData("distance: ", distance);
        telemetry.addData("motorPower", Constants.power(distance));
        telemetry.addData("angle: ",Constants.angle(distance));
        telemetry.addData("PPHeading: ", driveSubsystem.pinpoint.getHeading(AngleUnit.DEGREES));
        telemetry.addData("  ", "  ");
        telemetry.addData("alpha1:", baller.co1.alpha());
        telemetry.addData("alpha2:", baller.co2.alpha());
        telemetry.update();

        schedule(
                new InstantCommand(()-> ajustare.setPosition(Constants.angle(distance))),
                new InstantCommand(()-> shooter.setPower(Constants.power(distance))),
                new InstantCommand(()-> baller.ballInside(gamepad1))
        );
    }
}
