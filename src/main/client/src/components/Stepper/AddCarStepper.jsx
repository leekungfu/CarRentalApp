import React from "react";
import { useState, Fragment } from "react";
import {
  Box,
  Button,
  Card,
  CardContent,
  Paper,
  Step,
  StepContent,
  StepLabel,
  Stepper,
  Typography,
} from "@mui/material";
import Basic from "./Steps/Basic";

const AddCarStepper = () => {
  const steps = ["Basic", "Details", "Pricing", "Finish"];

  const [activeStep, setActiveStep] = useState(0);

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Card>
        <CardContent>
          <Stepper orientation="horizontal" activeStep={activeStep}>
            {steps.map((label, index) => {
              const stepProps = {};
              const labelProps = {};
              return (
                <Step key={label} {...stepProps}>
                  <StepLabel {...labelProps}>{label}</StepLabel>
                </Step>
              );
            })}
          </Stepper>
          <Paper sx={{ mt: 5 }}>
            {activeStep === 0 && <Basic />}
            {activeStep === 1 && <Basic />}
            {activeStep === 2 && <Basic />}
          </Paper>
          {activeStep === steps.length ? (
            <Fragment>
              <Typography sx={{ mt: 2, mb: 1 }}>
                All steps are completed!
              </Typography>
              <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
                <Box sx={{ flex: "1 1 auto" }} />
                <Button onClick={handleReset} sx={{ color: "white" }}>
                  Reset
                </Button>
              </Box>
            </Fragment>
          ) : (
            <Fragment>
              <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
                <Button
                  disabled={activeStep === 0}
                  onClick={handleBack}
                  sx={{ mr: 1, color: "white" }}
                >
                  Back
                </Button>
                <Box sx={{ flex: "1 1 auto" }} />
                <Button onClick={handleNext} sx={{ color: "white" }}>
                  {activeStep === steps.length - 1 ? "Finish" : "Next"}
                </Button>
              </Box>
            </Fragment>
          )}
        </CardContent>
      </Card>
    </Box>
  );
};

export default AddCarStepper;
