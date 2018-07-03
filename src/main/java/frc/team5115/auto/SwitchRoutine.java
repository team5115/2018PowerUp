package frc.team5115.auto;

import frc.team5115.robot.Robot;
import frc.team5115.statemachines.StateMachineBase;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class SwitchRoutine extends StateMachineBase {

    //define our states
    public static final int INIT = 1;
    public static final int DRIVING = 2;
    public static final int FINISHED = 3;

    Trajectory target;
    Trajectory.Config globalConfig;



    AutoDrive drive;

    public SwitchRoutine() {

    }
    public void update () {
        //Run switch block and check for number
        switch (state) {
            case INIT:
                //logic is as follows
                //if our objective(the switch) is on the left
                if (Robot.OP.switchpos == 'L'){
                    //check if we're lining up with the switch
                    if (Robot.OP.switchOurs()){
                        //if we are, set our follower to follow LL
                        target = Pathfinder.readFromFile(Robot.tw.LL);
                    } else if (Robot.OP.start == 'C'){
                        //if not, check if we're in the center, and set our follower to follow centerL
                        target = Pathfinder.readFromFile(Robot.tw.CL);
                    } else {
                        //if we arent in the center either, assume the switch is on the other side, follow LR
                        target = Pathfinder.readFromFile(Robot.tw.LR);
                    }
                    //this is the exact same thing, but for the right
                } else if(Robot.OP.switchpos == 'R'){
                    if (Robot.OP.switchOurs()){
                        target = Pathfinder.readFromFile(Robot.tw.RR);
                    } else if (Robot.OP.start == 'C'){
                        target = Pathfinder.readFromFile(Robot.tw.CR);
                    } else {
                        target = Pathfinder.readFromFile(Robot.tw.RL);
                    }
                }
                drive = new AutoDrive(target);
                drive.setState(AutoDrive.DRIVING);
                setState(DRIVING);
                break;

            //when in case driving
            case DRIVING:
                if(drive.state == AutoDrive.FINISHED){
                    setState(FINISHED);
                }
                drive.update();
                break;

            case FINISHED:
                Robot.drivetrain.drive(0,0);
                break;
        }
    }


}