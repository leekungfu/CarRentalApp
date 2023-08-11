import {
  Box,
  Container,
  OutlinedInput,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import React from "react";
import Provinces from "../../Select/Provinces";

const Details = () => {
  return (
    <Box>
      <Paper elevation={0}>
        <Stack direction="column" spacing={2}>
          <OutlinedInput fullWidth placeholder="Mileage" />
          <OutlinedInput
            fullWidth
            placeholder="Fuel Consumption (liter/100km)"
          />
          <Provinces />
          <OutlinedInput fullWidth placeholder="Street" />
          <OutlinedInput fullWidth placeholder="Description of vehicle" />
        </Stack>
      </Paper>
    </Box>
  );
};

export default Details;
