package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

    @Autonomous(name = "AutoModeRight", group="Robot")
    //@Disabled
    public class AutoModeRight extends LinearOpMode {
        //private means setting value to nothing, only when setting to null
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor leftFront = null;
        private DcMotor leftBack = null;
        private DcMotor rightFront= null;
        private DcMotor rightBack  = null;
        private DcMotor elevatorMotor  = null;
        public Servo leftClaw = null;
        public Servo rightClaw = null;
        static final double DRIVE_POWER = .6;

        @Override
        public void runOpMode() throws InterruptedException {
            //initalized motors
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            leftBack = hardwareMap.get(DcMotor.class, "leftBack");
            rightFront = hardwareMap.get(DcMotor.class, "rightFront");
            rightBack = hardwareMap.get(DcMotor.class, "rightBack");
            elevatorMotor = hardwareMap.get(DcMotor.class, "eleMotor");

            //intialized servos
            leftClaw = hardwareMap.get(Servo.class, "leftClaw");
            rightClaw = hardwareMap.get(Servo.class, "rightClaw");

            leftClaw.setPosition(0);
            rightClaw.setPosition(0);

            waitForStart();

                driveRight(DRIVE_POWER, 1900);
                driveForward(DRIVE_POWER, 1900);
                sleep(1900);
                driveStop();

        }

        private void driveForward(double power, long time) throws InterruptedException
        {
            leftBack.setPower(power);
            leftFront.setPower(power);
            rightFront.setPower(power);
            rightBack.setPower(power);
            Thread.sleep(time);

        }
        private void driveRight(double power, long time) throws InterruptedException
        {
            leftBack.setPower(-power);
            leftFront.setPower(power);
            rightFront.setPower(-power);
            rightBack.setPower(power);
            Thread.sleep(time);
        }
        private void driveStop()
        {
            leftBack.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
    }
