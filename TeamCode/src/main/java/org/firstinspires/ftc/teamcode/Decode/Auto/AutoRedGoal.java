package org.firstinspires.ftc.teamcode.Decode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Example Auto", group = "Examples")
public class AutoRedGoal extends OpMode {


  //  public static class Paths {

        public PathChain Path1;
        public PathChain Path2;
        public PathChain Path3;
        public PathChain Path4;
        public PathChain Path5;
        public PathChain Path6;
        public PathChain Path7;
        public PathChain Path8;

 //       public Paths(Follower follower) {
//            Path1 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(123.878, 124.228), new Pose(108.650, 121.250))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(37))
//                    .build();

//            Path2 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(108.650, 121.250), new Pose(85.000, 83.500))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(0))
//                    .build();
//
//            Path3 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(85.000, 83.500), new Pose(127.000, 83.500))
//                    )
//                    .setConstantHeadingInterpolation(Math.toRadians(0))
//                    .build();
//
//            Path4 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(127.000, 83.500), new Pose(113.000, 112.000))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(55))
//                    .build();
//
//            Path5 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(113.000, 112.000), new Pose(113.000, 112.000))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(55), Math.toRadians(88))
//                    .build();
//
//            Path6 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(113.000, 112.000),
//                                    new Pose(111.456, 59.665),
//                                    new Pose(84.500, 60.000)
//                            )
//                    )
//                    .setTangentHeadingInterpolation()
//                    .setReversed()
//                    .build();
//
//            Path7 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(84.500, 60.000), new Pose(134.500, 59.310))
//                    )
//                    .setConstantHeadingInterpolation(Math.toRadians(0))
//                    .build();
//
//            Path8 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(134.500, 59.310),
//                                    new Pose(109.181, 52.316),
//                                    new Pose(113.000, 112.000)
//                            )
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(55))
//                    .build();
   //     }
 //   }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
//
//public static class Paths {
//
//    public PathChain Path1;
//    public PathChain Path2;
//    public PathChain Path3;
//    public PathChain Path4;
//    public PathChain Path5;
//    public PathChain Path6;
//    public PathChain Path7;
//    public PathChain Path8;
//
//    public Paths(Follower follower) {
//        Path1 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(123.878, 124.228), new Pose(108.650, 121.250))
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(37))
//                .build();
//
//        Path2 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(108.650, 121.250), new Pose(85.000, 83.500))
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(37), Math.toRadians(0))
//                .build();
//
//        Path3 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(85.000, 83.500), new Pose(127.000, 83.500))
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        Path4 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(127.000, 83.500), new Pose(113.000, 112.000))
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(55))
//                .build();
//
//        Path5 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(113.000, 112.000), new Pose(113.000, 112.000))
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(55), Math.toRadians(88))
//                .build();
//
//        Path6 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierCurve(
//                                new Pose(113.000, 112.000),
//                                new Pose(111.456, 59.665),
//                                new Pose(84.500, 60.000)
//                        )
//                )
//                .setTangentHeadingInterpolation()
//                .setReversed(true)
//                .build();
//
//        Path7 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierLine(new Pose(84.500, 60.000), new Pose(134.500, 59.310))
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .build();
//
//        Path8 = follower
//                .pathBuilder()
//                .addPath(
//                        new BezierCurve(
//                                new Pose(134.500, 59.310),
//                                new Pose(109.181, 52.316),
//                                new Pose(113.000, 112.000)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(55))
//                .build();
//    }
//}











/*
ShootPose1 - ShooterActivate

      path1

Push - ShootPose2 - Push - ShootPose3 - Push - StopShooter - Chamber1

      path2

IntakeActive

      path3
      waitCommand - Chamber2 - Chamber3

      path4
      intakeStop
      ShootChamber
      ShooterActivate

Push - Chamber - Push - Chamber - Push - StopShooter - Chamber1

      rotationPath(5)

      path6

intakeActive

      path7
      waitCommand - Chamber2 - Chamber3

      path8
      intakeStop
      ShootChamber
      ShooterActivate

Push - Chamber - Push - Chamber - Push - StopShooter - Chamber1




 */











//
