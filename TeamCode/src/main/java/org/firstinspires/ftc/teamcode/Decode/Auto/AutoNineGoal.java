package org.firstinspires.ftc.teamcode.Decode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Decode.Commands.RotateWhenFull;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.BallDetectionSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "🔴9_GOAL🔴", group = "1")
public class AutoNineGoal extends CommandOpMode {

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public Follower follower;
    public BallDetectionSubsystem baller;
    public RotateWhenFull rotaet;
    public boolean intervension = true;

    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    public IntakeSubsystem intake;
    public ShootingSubsystem shooter;
    public SortSubsystem sorter;
    public StopperSubsystem stopper;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------



    private final Pose start = new Pose(119,135.6,Math.toRadians(307));
    private final Pose preload = new Pose(111,121);
    private final Pose path2 = new Pose(97,88);
    private final Pose path3 = new Pose(127,83.5);
    private final Pose path4 = new Pose(120.46,126.33);
    private final Pose b1 = new Pose(102,87);
    private final Pose b2 = new Pose(113,87);
    private final Pose b3 = new Pose(119,87);



    private final Pose test1 = new Pose(31.5, 126);
    private final Pose test2 = new Pose(112.5, 102);



    private Path scorePreload,firststack, takeStack1,goShoot1,tele,bila1,bila2,bila3;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------



    public void buildPaths() {
        scorePreload = new Path(new BezierLine(start,preload));
        scorePreload.setLinearHeadingInterpolation(start.getHeading(), Math.toRadians(37));

        firststack = new Path(new BezierLine(preload,path2));
        firststack.setLinearHeadingInterpolation(Math.toRadians(37),Math.toRadians(355));

        takeStack1 = new Path(new BezierLine(path2,path3));
        takeStack1.setLinearHeadingInterpolation(Math.toRadians(355), Math.toRadians(355));



        goShoot1 = new Path(new BezierLine(b3/* path4 */,preload));                                              //    PATH4
        goShoot1.setLinearHeadingInterpolation(Math.toRadians(5), Math.toRadians(37));

        tele = new Path(new BezierLine(preload, path4));
        tele.setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(355));



//        bila1 = new Path(new BezierLine(path2,b1));
//        bila1.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));
//
//        bila2 = new Path(new BezierLine(b1,b2));
//        bila2.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));
//
//        bila3 = new Path(new BezierLine(b2,b3));
//        bila3.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));


    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------



    @Override
    public void initialize() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(start /*start*/);

        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShootingSubsystem(hardwareMap);
        sorter = new SortSubsystem(hardwareMap);
        stopper = new StopperSubsystem(hardwareMap);
        baller = new BallDetectionSubsystem(hardwareMap);

        rotaet = new RotateWhenFull(sorter, baller);
        rotaet.CurentIndex = 0;

        baller.canDetectIntake = false;
        intervension = false;
        //setPathState(0);

        waitForStart();

        schedule(
                new SequentialCommandGroup(
                        new InstantCommand(()-> intervension = false),
                        new InstantCommand(()-> baller.autoDetection(false)),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new InstantCommand(()-> shooter.shoot(0.71)),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.rotateToShoot(0)),
                        new InstantCommand(()-> sorter.rotateToSlot(1)),
                        new InstantCommand(()-> sorter.rotateToShoot(0)),
                        new WaitCommand(2000),
                        new InstantCommand(()-> follower.followPath(scorePreload)),
                        new WaitCommand(1000),
                        new InstantCommand(()-> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(()-> sorter.rotateToShoot(1)),
                        new WaitCommand(300),
                        new InstantCommand(()-> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(()-> sorter.rotateToShoot(2)),
                        new WaitCommand(300),
                        new InstantCommand(()-> sorter.pushBall()),
                        new WaitCommand(1000),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new InstantCommand(()-> shooter.idle()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.rotateToSlot(0)),
                        new WaitCommand(200),
                        new InstantCommand(()-> follower.followPath(firststack)),
                        new WaitCommand(1000),
                        new InstantCommand(()-> intake.active()),
                        new InstantCommand(()-> stopper.Retract()),
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(()-> baller.autoDetection(true)),
                                        new InstantCommand(()-> intervension = true),
                                        new InstantCommand(()-> follower.setMaxPower(0.40)),
                                        new WaitCommand(200),
                                        new InstantCommand(()-> follower.followPath(takeStack1))
                                )
                        )

                )
        );

/*
initialization,
shoot preload
go to first stack
turn on search
turn on intervention
paralel command to intake the balls and switch while doing it
after the path ends you turn off search and intervesion

switch to shoot the 3 balls
go to last stack
repeat the parralel go and take
turn off the 2 things
go shoot
park

 */

    }


    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {

        super.run();
        follower.update();
        sorter.setDefaultCommand(rotaet);
        if(baller.canDetectIntake == true){
            schedule(
                    new InstantCommand(()-> sorter.rotateToSlot(rotaet.index(rotaet.CurentIndex)))
            );
        }


        if(baller.canDetectIntake == false && intervension == true){
            schedule(
                    new SequentialCommandGroup(
                            new WaitCommand(600),
                            new InstantCommand(()-> baller.autoDetection(true) )
                    )
            );
        }
        //autonomousPathUpdate();

        telemetry.addData("ALpha1: ",baller.co1.alpha());
        telemetry.addData("ALpha2: ",baller.co2.alpha());
        telemetry.addData("index", rotaet.index(rotaet.CurentIndex));
        telemetry.addData("state: ", baller.canDetectIntake);
        telemetry.update();
    }

}
