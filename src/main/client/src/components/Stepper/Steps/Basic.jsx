import {
  Box,
  Grid,
  Input,
  OutlinedInput,
  Paper,
  Typography,
} from "@mui/material";
import { Fragment } from "react";

const Basic = () => {
  return (
    <Fragment>
      <Paper>
        <Grid container>
          <Grid item xs={4}>
            <OutlinedInput placeholder="Plate Number" required />
            <OutlinedInput placeholder="Plate Number" required />
            <OutlinedInput placeholder="Plate Number" required />
          </Grid>
        </Grid>
      </Paper>
    </Fragment>
  );
};

export default Basic;
