import { Key, ManageAccounts } from "@mui/icons-material";
import {
  Box,
  Card,
  CardContent,
  CardHeader,
  Tab,
  Tabs,
  Container,
  Grid,
  Stack,
  OutlinedInput,
  InputLabel,
} from "@mui/material";
import React, { useState } from "react";
import CustomTabPanels from "./CustomTabPanels";
import dayjs from "dayjs";
import { DatePicker } from "@mui/x-date-pickers";
import DrivingLicense from "../../../components/UploadFile/DrivingLicense";
import Provinces from "../../../components/Select/Provinces";

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

const ProfileTabs = () => {
  const [tab, setTab] = useState(0);

  const [date, setDate] = useState(dayjs("2000-01-01"));

  const handleChange = (event, newValue) => {
    setTab(newValue);
  };
  return (
    <div>
      <Container maxWidth="lg">
        <Card>
          <CardHeader>MY PROFILE</CardHeader>
          <CardContent>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <Tabs value={tab} onChange={handleChange}>
                <Tab
                  icon={<ManageAccounts />}
                  label="Personal Information"
                  {...a11yProps(0)}
                />
                <Tab icon={<Key />} label="Security" {...a11yProps(1)} />
              </Tabs>
            </Box>
            <CustomTabPanels value={tab} index={0}>
              <Grid container>
                <Grid item xs={6}>
                  <Stack>
                    <InputLabel required>Full Name</InputLabel>
                    <OutlinedInput
                      fullWidth
                      placeholder="Example: John Wick"
                      required
                    />
                    <InputLabel required>Phone Number</InputLabel>
                    <OutlinedInput fullWidth placeholder="(+84)" required />
                    <InputLabel required>National ID</InputLabel>
                    <OutlinedInput
                      fullWidth
                      placeholder="Example: 122318181"
                      required
                    />
                  </Stack>
                </Grid>
                <Grid item xs={6}>
                  <Stack>
                    <InputLabel required>Date of birth</InputLabel>
                    <DatePicker
                      value={date}
                      onChange={(date) => setDate(date)}
                    />
                    <InputLabel required>Email</InputLabel>
                    <OutlinedInput
                      fullWidth
                      placeholder="name@gmail.com"
                      disabled
                    />
                    <InputLabel required>Driving License</InputLabel>
                    <OutlinedInput fullWidth placeholder="Street" />
                  </Stack>
                </Grid>
              </Grid>
              <Stack>
                <Provinces />
                <DrivingLicense />
              </Stack>
            </CustomTabPanels>
            <CustomTabPanels value={tab} index={1}>
              Item Two
            </CustomTabPanels>
          </CardContent>
        </Card>
      </Container>
    </div>
  );
};

export default ProfileTabs;
