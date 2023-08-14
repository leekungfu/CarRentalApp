import {
  Card,
  CardContent,
  Box,
  Tabs,
  Tab,
  Grid,
  Stack,
  Typography,
  Button,
} from "@mui/material";
import React from "react";
import { useState } from "react";
import styled from "styled-components";
import CustomTabPanels from "../../../components/CustomTabPanels/CustomTabPanels";
import { AttachMoney, Info, More } from "@mui/icons-material";
import Details from "../../../components/Stepper/Steps/Details";
import Pricing from "../../../components/Stepper/Steps/Pricing";

const StyledTypography = styled(Typography)`
  font-weight: bold;
`;

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

const ViewDetailsTabs = () => {
  const [tab, setTab] = useState(0);

  const handleChange = (event, newValue) => {
    setTab(newValue);
  };

  return (
    <div>
      <Card>
        <CardContent>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <Tabs value={tab} onChange={handleChange}>
              <Tab
                sx={{ fontWeight: "bold" }}
                icon={<Info />}
                label="Basic Information"
                {...a11yProps(0)}
              />
              <Tab
                sx={{ fontWeight: "bold" }}
                icon={<More />}
                label="Details"
                {...a11yProps(1)}
              />
              <Tab
                sx={{ fontWeight: "bold" }}
                icon={<AttachMoney />}
                label="Pricing"
                {...a11yProps(2)}
              />
            </Tabs>
          </Box>
          <CustomTabPanels value={tab} index={0}>
            <Grid container>
              <Grid item xs={6}>
                <Stack spacing={2}>
                  <StyledTypography variant="subtitle1">
                    Plate number:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">
                    Brand name:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">
                    Production year:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">
                    Transmission:
                  </StyledTypography>
                </Stack>
              </Grid>
              <Grid item xs={6}>
                <Stack spacing={2}>
                  <StyledTypography variant="subtitle1">
                    Color:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">
                    Model:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">
                    No. of seats:
                  </StyledTypography>
                  <StyledTypography variant="subtitle1">Fuel:</StyledTypography>
                </Stack>
              </Grid>
              <Grid item xs={12}>
                <StyledTypography sx={{ pt: 2 }} variant="subtitle1">
                  Documents:
                </StyledTypography>
              </Grid>
            </Grid>
          </CustomTabPanels>
          <CustomTabPanels value={tab} index={1}>
            <Details />
            <Stack
              direction="row"
              spacing={1.5}
              justifyContent="end"
              sx={{ mt: 5 }}
            >
              <Button
                sx={{
                  border: "solid 1px",
                  color: "white",
                  borderColor: "#fca311",
                  "&:hover": {
                    borderColor: "#fca311",
                  },
                  width: "16%",
                }}
                variant="outlined"
              >
                Discard
              </Button>
              <Button
                sx={{
                  color: "white",
                  border: "solid 1px",
                  borderColor: "#fca311",
                  "&:hover": {
                    borderColor: "#fca311",
                  },
                  width: "16%",
                }}
                variant="outlined"
              >
                Save
              </Button>
            </Stack>
          </CustomTabPanels>
          <CustomTabPanels value={tab} index={2}>
            <Pricing />
            <Stack
              direction="row"
              spacing={3.5}
              justifyContent="end"
              sx={{ mt: 5 }}
            >
              <Button
                sx={{
                  border: "solid 1px",
                  color: "white",
                  borderColor: "#fca311",
                  "&:hover": {
                    borderColor: "#fca311",
                  },
                  width: "18%",
                }}
                variant="outlined"
              >
                Discard
              </Button>
              <Button
                sx={{
                  color: "white",
                  border: "solid 1px",
                  borderColor: "#fca311",
                  "&:hover": {
                    borderColor: "#fca311",
                  },
                  width: "18%",
                }}
                variant="outlined"
              >
                Save
              </Button>
            </Stack>
          </CustomTabPanels>
        </CardContent>
      </Card>
    </div>
  );
};

export default ViewDetailsTabs;
