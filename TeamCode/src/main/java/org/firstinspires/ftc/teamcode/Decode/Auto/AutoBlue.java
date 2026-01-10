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

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "🔷BlueGoal🔷")
public class AutoBlue extends CommandOpMode {

    public Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    public IntakeSubsystem intake;
    public ShootingSubsystem shooter;
    public SortSubsystem sorter;
    public StopperSubsystem stopper;

    private final Pose start = new Pose(20.5, 124.228, Math.toRadians(143));
    private final Pose preload = new Pose(40.83, 120.25);
    private final Pose path2 = new Pose(42.487, 84.585);
    private final Pose path3 = new Pose(127,83.5);
    private final Pose path4 = new Pose(120.46,126.33);
    private final Pose b1 = new Pose(102,87);
    private final Pose b2 = new Pose(113,87);
    private final Pose b3 = new Pose(119,87);

    public Path scorePreload,firststack,takeStack1,goShoot1, tele, bila1,bila2,bila3;

    public void buildPaths(){
        scorePreload = new Path( new BezierLine(start, preload));
        scorePreload.setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(143));

        firststack = new Path(new BezierLine(preload,path2));
        firststack.setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(180));

        takeStack1 = new Path(new BezierLine(path2,path3));
        takeStack1.setLinearHeadingInterpolation(Math.toRadians(355), Math.toRadians(355));



        goShoot1 = new Path(new BezierLine(b3/* path4 */,preload));                                              //    PATH4
        goShoot1.setLinearHeadingInterpolation(Math.toRadians(5), Math.toRadians(37));

        tele = new Path(new BezierLine(preload, path4));
        tele.setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(355));



        bila1 = new Path(new BezierLine(path2,b1));
        bila1.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));

        bila2 = new Path(new BezierLine(b1,b2));
        bila2.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));

        bila3 = new Path(new BezierLine(b2,b3));
        bila3.setLinearHeadingInterpolation(Math.toRadians(355),Math.toRadians(355));

    }


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
        //setPathState(0);

        waitForStart();

        schedule(

                new SequentialCommandGroup(
                        new InstantCommand(() -> sorter.retractPusher()),
                        new InstantCommand(() -> shooter.shoot(0.71)),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.rotateToShoot(0)),
                        new InstantCommand(() -> sorter.rotateToSlot(1)),
                        new InstantCommand(() -> sorter.rotateToShoot(0)),
                        new WaitCommand(2000),
                        new InstantCommand(() -> follower.followPath(scorePreload)),
                        new WaitCommand(1000),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(() -> sorter.rotateToShoot(1)),
                        new WaitCommand(300),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(() -> sorter.rotateToShoot(2)),
                        new WaitCommand(300),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new InstantCommand(() -> shooter.idle()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.rotateToSlot(0)),
                        new WaitCommand(200),
                        new InstantCommand(() -> follower.followPath(firststack)),
                        new WaitCommand(1000),
                        new InstantCommand(() -> intake.active()),
                        new InstantCommand(() -> stopper.Retract()),



                        /*
                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(()-> follower.setMaxPower(0.54)),
                                        new InstantCommand(()-> follower.followPath(takeStack1))
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1200),
                                        new InstantCommand(()-> sorter.rotateToSlot(1)),
                                        new WaitCommand(365),
                                        new InstantCommand(()-> sorter.rotateToSlot(2)),
                                        new WaitCommand(1500),
                                        new InstantCommand(()-> intake.idle()),
                                        new InstantCommand(()-> stopper.Stop()),
                                        new InstantCommand(()-> follower.setMaxPower(1)),
                                        new InstantCommand(()-> shooter.shoot(0.71))
                                )
                        ),
                        */

                        new WaitCommand(1000),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(() -> follower.setMaxPower(0.54)),
                                        new InstantCommand(() -> follower.followPath(bila1))
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1100),
                                        new InstantCommand(() -> sorter.rotateToSlot(1))

                                )
                        ),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(() -> follower.setMaxPower(0.54)),
                                        new InstantCommand(() -> follower.followPath(bila2))
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1300),
                                        new InstantCommand(() -> sorter.rotateToSlot(2))

                                )
                        ),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(() -> follower.setMaxPower(0.54)),
                                        new InstantCommand(() -> follower.followPath(bila3))
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1200),
                                        new InstantCommand(() -> intake.idle()),
                                        new InstantCommand(() -> stopper.Stop()),
                                        new InstantCommand(() -> follower.setMaxPower(1)),
                                        new InstantCommand(() -> shooter.shoot(0.71))
                                )
                        ),


                        new InstantCommand(() -> sorter.rotateToShoot(0)),
                        new InstantCommand(() -> follower.followPath(goShoot1)),
                        new WaitCommand(2000),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(() -> sorter.rotateToShoot(1)),
                        new WaitCommand(300),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new WaitCommand(400),
                        new InstantCommand(() -> sorter.rotateToShoot(2)),
                        new WaitCommand(300),
                        new InstantCommand(() -> sorter.pushBall()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> sorter.retractPusher()),
                        new InstantCommand(() -> shooter.idle()),
                        new WaitCommand(500),
                        new InstantCommand(() -> sorter.rotateToSlot(0)),
                        new InstantCommand(() -> follower.setMaxPower(0.55)),
                        new WaitCommand(500),
                        new InstantCommand(() -> follower.followPath(tele)),

                        new WaitCommand(300)


                )
        );
    }

    @Override
    public void run() {
        super.run();
        follower.update();
        //autonomousPathUpdate();
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }
}


