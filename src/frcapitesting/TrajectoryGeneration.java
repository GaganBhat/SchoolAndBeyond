package frcapitesting;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import java.util.List;

public class TrajectoryGeneration {

    public static void main(String[] args) {
        TrajectoryConfig config =
                new TrajectoryConfig(5, 5)
                        .setKinematics(new DifferentialDriveKinematics(3)).setReversed(true);


        // An example trajectory to follow.  All units in meters.
        Trajectory testTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                        new Translation2d(5, 2.5)
                ),
                new Pose2d(10, 5, new Rotation2d(0)),
                // Pass config
                config
        );

        Trajectory quinticTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        new Pose2d(7,2.5,new Rotation2d(0)),
                        new Pose2d(15,0, Rotation2d.fromDegrees(-90))
                ),
                // Pass config
                config
        );
//
        Trajectory quinticTrajectory2 = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        new Pose2d(7,0,new Rotation2d(0)),
                        new Pose2d(10,4, Rotation2d.fromDegrees(90)),
                        new Pose2d(10,8, Rotation2d.fromDegrees(90)),
                        new Pose2d(7,10, new Rotation2d().fromDegrees(180)),
                        new Pose2d(3,10, new Rotation2d().fromDegrees(180)),
                        new Pose2d(0,7, new Rotation2d().fromDegrees(-90)),
                        new Pose2d(0,0, new Rotation2d().fromDegrees(-90))
                ),
                // Pass config
                config
        );

        System.out.println("X,Y");
        for(Trajectory.State state : testTrajectory.getStates()){
            Translation2d translation2d = state.poseMeters.getTranslation();
            System.out.println(translation2d.getX() + "," + translation2d.getY());
        }

    }


}
