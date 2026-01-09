package org.firstinspires.ftc.teamcode.Decode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
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

    public Path scorePreload,firststack;

    public void buildPaths(){
        scorePreload = new Path( new BezierLine(start, preload));
        scorePreload.setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(143));

        firststack = new Path(new BezierLine(preload,path2));
        firststack.setLinearHeadingInterpolation(Math.toRadians(143), Math.toRadians(180));

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
                        new InstantCommand(()-> sorter.retractPusher()),
                        new InstantCommand(()-> shooter.shoot(1)),
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
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.rotateToShoot(1)),
                        new WaitCommand(200),
                        new InstantCommand(()-> sorter.pushBall()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.rotateToShoot(2)),
                        new WaitCommand(200),
                        new InstantCommand(()-> sorter.pushBall()),
                        new WaitCommand(1000),
                        new InstantCommand(()-> sorter.retractPusher()),
                        new InstantCommand(()-> shooter.idle()),
                        new WaitCommand(500),
                        new InstantCommand(()-> sorter.rotateToSlot(0)),
                        new WaitCommand(200),
                        new InstantCommand(()-> follower.followPath(firststack))
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


