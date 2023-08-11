import {
  Box,
  Container,
  Grid,
  OutlinedInput,
  Paper,
  Stack,
  Typography,
  FormControlLabel,
  Checkbox,
} from "@mui/material";
import React from "react";
import Provinces from "../../Select/Provinces";
import FrontOfCar from "../../UploadFile/FrontOfCar";
import RightOfCar from "../../UploadFile/RightOfCar";
import LeftOfCar from "../../UploadFile/LeftOfCar";
import BackOfCar from "../../UploadFile/BackOfCar";

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
        <Grid container sx={{ pt: 2 }}>
          <Grid item xs={4}>
            <Stack direction="column" spacing={1}>
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">Bluetooth</Typography>}
              />
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">GPS</Typography>}
              />
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">Camera</Typography>}
              />
            </Stack>
          </Grid>
          <Grid item xs={4}>
            <Stack spacing={1}>
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">Sun roof</Typography>}
              />
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">Child lock</Typography>}
              />
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">Child seat</Typography>}
              />
            </Stack>
          </Grid>
          <Grid item xs={4}>
            <Stack spacing={1}>
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">DVD</Typography>}
              />
              <FormControlLabel
                control={<Checkbox />}
                label={<Typography variant="subtitle1">USB</Typography>}
              />
            </Stack>
          </Grid>
          <Grid item xs={4} sx={{ pt: 2 }}>
            <Stack spacing={1}>
            <Typography variant="subtitle2" fontWeight={600}>
                Front image
              </Typography>
              <FrontOfCar/>
              <Typography variant="subtitle2" fontWeight={600}>
                Right image
              </Typography>
              <RightOfCar/>
            </Stack>
          </Grid>
          <Grid item xs={2}></Grid>
          <Grid item xs={4} sx={{ pt: 2 }}>
            <Stack spacing={1}>
            <Typography variant="subtitle2" fontWeight={600}>
                Left image
              </Typography>
              <LeftOfCar/>
              <Typography variant="subtitle2" fontWeight={600}>
                Back image
              </Typography>
              <BackOfCar/>
            </Stack>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default Details;
