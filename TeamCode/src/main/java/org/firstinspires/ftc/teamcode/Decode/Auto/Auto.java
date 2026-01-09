package org.firstinspires.ftc.teamcode.Decode.Auto;

import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.pedropathing.util.Timer;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
@Disabled
public class Auto extends OpMode {

    public Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    public IntakeSubsystem intake;
    public ShootingSubsystem shooter;
    public SortSubsystem sorter;
    public StopperSubsystem stopper;

    private final Pose start = new Pose(123.878,124.228,Math.toRadians(37));
    private final Pose preload = new Pose(108.65,121.25);
    private final Pose path2 = new Pose(85,83.5);
    private final Pose path3 = new Pose(127,83.5);
    private final Pose path4 = new Pose(113,112);
    private final Pose path5 = new Pose(113,112);
    private final Pose path6 = new Pose(84.5,60);
    private final Pose path7 = new Pose(134.5,59.31);
    private final Pose path8 = new Pose(113,112);


    private Path scorePreload;
    private PathChain firststack,takeStack1,goShoot1,rotationPath,secondStack,takeStack2,goShoot2;

    public void buildPaths() {
        scorePreload = new Path(new BezierLine(start,preload));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(37));

        firststack = follower.pathBuilder()
                .addPath(new BezierLine(preload, path2))
                .setLinearHeadingInterpolation(Math.toRadians(37),Math.toRadians(0))
                .build();
    }

    public void autonomousPathUpdate(){
        switch(pathState){
            case 0:
                new SequentialCommandGroup(
                        new InstantCommand(()->{
                            sorter.rotateToShoot(0);
                            shooter.shoot(1);
                            new WaitCommand(300);
                            follower.followPath(scorePreload);
                            setPathState(1);
                        })
                );
//                sorter.rotateToShoot(0);
//                shooter.shoot();
//                follower.followPath(scorePreload);
//                setPathState(1);
                break;
            case 1:
                sorter.pushBall();
                setPathState(2);
                break;


            case 2:
                if(!follower.isBusy()) {
                    /* Score Preload */
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(firststack,true);
                    setPathState(2);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }


    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(start);

        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShootingSubsystem(hardwareMap);
        sorter = new SortSubsystem(hardwareMap);
        stopper = new StopperSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        // Feedback to Driver Hub for debugging
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();

    }
    @Override
    public void init_loop() {}
    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }
    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {}
}
