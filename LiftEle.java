package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LiftEle extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor elevatorMotor  = null;
    public static final double Ground = 0;
    public static final double LOW_LEVEL = 1100;//
    public static final double MIDDLE_LEVEL = 2200;//
    public static final double HIGH_LEVEL = 3100;//

    @Override
    public void runOpMode() throws InterruptedException {
        elevatorMotor = hardwareMap.get(DcMotor.class, "eleMotor");

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        elevatorMotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.y) {
                elevatorMotor.setPower(.3);
            }else if (gamepad1.b){
                elevatorMotor.setPower(-.3);
            }else{
                elevatorMotor.setPower(0);
            }

        }
    }
}
/*
package org.firstinspires.ftc.teamcode;

import ftc.crazycatladies.nyan.actuators.DcMotorPosition;
import ftc.crazycatladies.nyan.actuators.MultiPositionMotor;

public class Lift extends MultiPositionMotor<Lift.LiftPosition> {
    public enum LiftPosition implements DcMotorPosition {
        GROUND(0),
        LOW_LEVEL(1100),
        MIDDLE_LEVEL(2200),
        HIGH_LEVEL(3100),
        PRE_INTAKE(800),
        DRIVE(1100);

        protected int position;

        LiftPosition(int p) {
            position = p;
        }

        @Override
        public int getPosition() {
            return position;
        }
    }

    Lift() {
        super("lift", 2, false);
    }

    public void moveTo(LiftPosition position) {
        super.moveTo(position, 1.0, 0.95);
    }
}

*/
