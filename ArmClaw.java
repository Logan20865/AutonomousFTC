package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class ArmClaw extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public Servo leftClaw = null;
    public Servo rightClaw = null;

    double clawOffset = 0;
    public static final double MID_SERVO = .5;
    public static final double CLAW_SPEED = 0.01;                 // sets rate to move servo
    public static final double ARM_UP_POWER = 0.45;
    public static final double ARM_DOWN_POWER = -0.45;


    @Override
    public void runOpMode() {
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        leftClaw.setPosition(1);
        rightClaw.setPosition(1);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Use gamepad left & right Bumpers to open and close the claw
            if (gamepad1.right_bumper) {
                clawOffset += CLAW_SPEED;
            }else if (gamepad1.left_bumper) {
                clawOffset -= CLAW_SPEED;
            }
            // Move both servos to new position.  Assume servos are mirror image of each other.
            clawOffset = Range.clip(clawOffset, -.5, .5);
            leftClaw.setPosition(MID_SERVO - clawOffset);
            rightClaw.setPosition(MID_SERVO + clawOffset);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("claw", "Offset = %.2f", clawOffset);
            telemetry.update();
        }
    }
}