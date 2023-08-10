import {
  Box,
  Grid,
  Input,
  OutlinedInput,
  Paper,
  Stack,
  Typography,
  FormControl,
  Radio,
  RadioGroup,
  FormControlLabel,
  Checkbox,
  Link,
} from "@mui/material";
import { Fragment, useState } from "react";
import ColorSelection from "../../Select/ColorSelection";
import BrandsSelection from "../../Select/BrandsSelection";
import ModelsSelection from "../../Select/ModelsSelection";
import ProductionYearSelection from "../../Select/ProductionYearSelection";
import NumberOfSeatsSelection from "../../Select/NumberOfSeats";

const Basic = () => {
  const [gearValue, setGearValue] = useState("");
  const [fuelValue, setFuelValue] = useState("");

  const handleGearChange = (event) => {
    setGearValue(event.target.value);
  };

  const handleFuelChange = (event) => {
    setFuelValue(event.target.value);
  };

  return (
    <Fragment>
      <Paper elevation={0}>
        <Grid container rowSpacing={1} columnSpacing={1}>
          <Grid item xs={12}>
            <Stack direction="row" spacing={2}>
              <OutlinedInput size="small" placeholder="Plate Number" required />
              <ColorSelection />
              <BrandsSelection />
            </Stack>
          </Grid>
          <Grid item xs={12}>
            <Stack direction="row" spacing={2}>
              <ModelsSelection />
              <ProductionYearSelection />
              <NumberOfSeatsSelection />
            </Stack>
          </Grid>
        </Grid>
        <Grid container sx={{ pt: 3 }}>
          <Grid item xs={6}>
            <RadioGroup value={gearValue} onChange={handleGearChange}>
              <FormControlLabel
                control={<Radio value="automatic" color="primary" />}
                label="Automatic"
              />
              <FormControlLabel
                control={<Radio value="manual" color="primary" />}
                label="Manual"
              />
            </RadioGroup>
          </Grid>
          <Grid item xs={6}>
            <RadioGroup value={fuelValue} onChange={handleFuelChange}>
              <FormControlLabel
                control={<Radio  value="gasoline" color="primary" />}
                label="Gasoline"
              />
              <FormControlLabel
                control={<Radio value="diesel" color="primary" />}
                label="Diesel"
              />
            </RadioGroup>
          </Grid>
        </Grid>
      </Paper>
    </Fragment>
  );
};

export default Basic;
