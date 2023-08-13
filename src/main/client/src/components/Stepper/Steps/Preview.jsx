import React, { useState } from "react";
import AutoPlaySwipePreview from "../AutoPlaySwipePreview";
import { Typography, Grid, Rating, Box, Stack } from "@mui/material";

const Preview = () => {
  const [value, setValue] = useState(3.5);
  return (
    <div>
      <Grid container alignItems="center">
        <Grid item xs={6}>
          <AutoPlaySwipePreview />
        </Grid>
        <Grid item xs={6}>
          <Typography variant="h6">Mercedes-Benz Pickup Truck 2008</Typography>
          <Stack direction="row" spacing={1}>
            <Typography fontWeight={600} variant="subtitle1">
              Rating:
            </Typography>
            <Rating
              defaultValue={3.5}
              precision={0.5}
              readOnly
            />
          </Stack>
          <Typography variant="subtitle1">Number of rides: 0</Typography>
          <Typography variant="subtitle1">Price: 1.000.000 VND</Typography>
          <Typography variant="subtitle1">
            Location: Phường Ngọc Hà, Thành phố Hà Giang, Tỉnh Hà Giang
          </Typography>
          <Typography variant="subtitle1">
            Status: <span style={{ color: "#408971" }}>Availabel</span>
          </Typography>
        </Grid>
      </Grid>
    </div>
  );
};

export default Preview;
