package org.firstinspires.ftc.teamcode.Decode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.ShootingSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "🟢LEAVE🟢", group = "A")
public class LeaveStart extends CommandOpMode {

    public Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    private final Pose start = new Pose(56,10,Math.toRadians(90));
    private final Pose up = new Pose(56,36, Math.toRadians(90));
    private  Path leave;

    public void buildPaths(){
        leave = new Path(new BezierLine(start, up));
        leave.setLinearHeadingInterpolation(start.getHeading(),up.getHeading());
    }


    @Override
    public void initialize() {

        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(start /*start*/);

        //setPathState(0);

        waitForStart();

        schedule(
                new InstantCommand(()-> follower.setMaxPower(0.56)),
                new WaitCommand(500),
                new InstantCommand(()-> follower.followPath(leave))
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
