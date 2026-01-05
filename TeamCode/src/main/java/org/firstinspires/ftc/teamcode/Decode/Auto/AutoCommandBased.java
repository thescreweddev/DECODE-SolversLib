package org.firstinspires.ftc.teamcode.Decode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
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

@Autonomous
public class AutoCommandBased extends CommandOpMode {

    public Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    public IntakeSubsystem intake;
    public ShootingSubsystem shooter;
    public SortSubsystem sorter;
    public StopperSubsystem stopper;

    private final Pose start = new Pose(123.878,124.228,Math.toRadians(37));
    private final Pose preload = new Pose(111.7,121.5);
    private final Pose path2 = new Pose(99,86);
    private final Pose path3 = new Pose(127,83.5);
    private final Pose path4 = new Pose(113,112);
    private final Pose path5 = new Pose(113,112);
    private final Pose path6 = new Pose(84.5,60);
    private final Pose path7 = new Pose(134.5,59.31);
    private final Pose path8 = new Pose(113,112);
//
//    private final Pose test = new Pose(107, 60);
//
//    private final Pose rotate = new Pose(108.65, 121.25);


//    private final Pose start = new Pose(123.878,124.228);
    private final Pose test1 = new Pose(31.5, 126);
    private final Pose test2 = new Pose(112.5, 102);



    private Path scorePreload,firststack, takeStack1,goShoot1;
    private PathChain rotationPath,secondStack,takeStack2,goShoot2;

    public void buildPaths() {
        scorePreload = new Path(new BezierLine(start,preload));
        scorePreload.setLinearHeadingInterpolation(start.getHeading(), Math.toRadians(37));

        firststack = new Path(new BezierLine(preload,path2));
        firststack.setLinearHeadingInterpolation(Math.toRadians(37),Math.toRadians(355));

        takeStack1 = new Path(new BezierLine(path2,path3));
        takeStack1.setLinearHeadingInterpolation(Math.toRadians(355), Math.toRadians(5));

        goShoot1 = new Path(new BezierLine(path3,preload));
        goShoot1.setLinearHeadingInterpolation(Math.toRadians(5), Math.toRadians(37));

//        firststack = follower.pathBuilder()
//                .addPath(new BezierLine(test1, test2))
//                .setLinearHeadingInterpolation(Math.toRadians(270),Math.toRadians(270))
//                .build();

////        rot = follower.pathBuilder()
////                .addPath(new BezierLine(preload,rotate))
////                .setLinearHeadingInterpolation(Math.toRadians(37 ), Math.toRadians(350))
////                .build();
//
//        takeStack1 = new Path(new BezierLine(test, path3));
//        takeStack1.setLinearHeadingInterpolation(Math.toRadians(270),Math.toRadians(0));

//        takeStack1 = follower.pathBuilder()
//                .addPath(new BezierLine(rotate, path3))
//                .setLinearHeadingInterpolation(Math.toRadians(350),Math.toRadians(0))
//                .build();

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
                        new InstantCommand(()-> shooter.shoot()),
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
                        new InstantCommand(()-> follower.followPath(firststack)),
                        new WaitCommand(1000),
                        new InstantCommand(()-> intake.active()),
                        new InstantCommand(()-> stopper.Retract()),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new SequentialCommandGroup(
                                        new InstantCommand(()-> follower.setMaxPower(0.6)),
                                        new InstantCommand(()-> follower.followPath(takeStack1))
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1100),
                                        new InstantCommand(()-> sorter.rotateToSlot(1)),
                                        new WaitCommand(365),
                                        new InstantCommand(()-> sorter.rotateToSlot(2)),
                                        new WaitCommand(1500),
                                        new InstantCommand(()-> intake.idle()),
                                        new InstantCommand(()-> stopper.Stop()),
                                        new InstantCommand(()-> follower.setMaxPower(1)),
                                        new InstantCommand(()-> shooter.shoot())
                                )
                        ),
                        new InstantCommand(()-> sorter.rotateToShoot(0)),
                        new InstantCommand(()-> follower.followPath(goShoot1)),
                        new WaitCommand(2000),
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
                        new InstantCommand(()-> sorter.rotateToSlot(0))




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


//    public void autonomousPathUpdate(){
//        switch(pathState){
//            case 0:
//                schedule(
//                        new SequentialCommandGroup(
//                                new InstantCommand(()->sorter.rotateToShoot(0)),
//                                new InstantCommand(()-> shooter.shoot()),
//                                new WaitCommand(2000),
//                                new InstantCommand(()-> follower.followPath(scorePreload))
//                        )
//                );
////                sorter.rotateToShoot(0);
////                shooter.shoot();
////                follower.followPath(scorePreload);
//                setPathState(1);
//                break;
//            case 1:
//                sorter.pushBall();
//                setPathState(2);
//                break;
//
//
//            case 2:
//                if(!follower.isBusy()) {
//                    /* Score Preload */
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(firststack,true);
//                    setPathState(2);
//                }
//                break;
//        }
//    }
//    public void setPathState(int pState) {
//        pathState = pState;
//        pathTimer.resetTimer();
//    }


//    @Override
//    public void init() {
//        pathTimer = new Timer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//
//        follower = Constants.createFollower(hardwareMap);
//        buildPaths();
//        follower.setStartingPose(start);
//
//        intake = new IntakeSubsystem(hardwareMap);
//        shooter = new ShootingSubsystem(hardwareMap);
//        sorter = new SortSubsystem(hardwareMap);
//        stopper = new StopperSubsystem(hardwareMap);
//    }
//
//    @Override
//    public void loop() {
//        follower.update();
//        autonomousPathUpdate();
//        // Feedback to Driver Hub for debugging
//        telemetry.addData("path state", pathState);
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.update();
//
//    }
//    @Override
//    public void init_loop() {}
//    /** This method is called once at the start of the OpMode.
//     * It runs all the setup actions, including building paths and starting the path system **/
//    @Override
//    public void start() {
//        opmodeTimer.resetTimer();
//        setPathState(0);
//    }
//    /** We do not use this because everything should automatically disable **/
//    @Override
//    public void stop() {}
