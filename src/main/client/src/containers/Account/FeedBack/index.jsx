import React from "react";
import NavMenuUser from "../../../components/NavMenuUser";
import {
  Box,
  Breadcrumbs,
  Card,
  CardContent,
  Container,
  Grid,
  Paper,
  Rating,
  Stack,
  Typography,
} from "@mui/material";
import { Commute, Home, NavigateNext, Person } from "@mui/icons-material";
import { Link } from "react-router-dom";
import { useState } from "react";
import styled from "styled-components";

const StyledTypography = styled(Typography)`
  font-weight: bold !important;
`;

const MyFeedBack = () => {
  const [value, setValue] = useState(4.25);

  return (
    <div>
      <NavMenuUser />
      <Container maxWidth="lg" sx={{ pt: 5 }}>
        <Container maxWidth="lg" sx={{ mt: 5 }}>
          <Breadcrumbs
            separator={<NavigateNext fontSize="small" />}
            aria-label="breadcrumb"
          >
            <Stack direction="row" alignItems="center">
              <Home sx={{ mr: 0.5 }} fontSize="inherit" />
              <Typography
                component={Link}
                to="/homeowner"
                variant="subtitle1"
                fontWeight="bold"
                sx={{
                  color: "#7f7f7f !important",
                  "&:hover": {
                    color: "#fca311 !important",
                  },
                }}
              >
                Home
              </Typography>
            </Stack>
            <Stack direction="row" alignItems="center">
              <Commute sx={{ mr: 0.5 }} fontSize="inherit" />
              <Typography
                variant="subtitle1"
                fontWeight="bold"
                sx={{ display: "flex", alignItems: "center" }}
              >
                My Wallet
              </Typography>
            </Stack>
          </Breadcrumbs>
        </Container>
        <Container maxWidth="lg" sx={{ mt: 5, mb: 5 }}>
          <StyledTypography variant="h6">My Feedback</StyledTypography>
          <Stack spacing={2} alignItems="center" sx={{ mt: 5, mb: 5 }}>
            <StyledTypography variant="subtitle1">
              Average Ratings
            </StyledTypography>
            <StyledTypography variant="h4">{value}</StyledTypography>
            <Rating value={value} precision={0.05} readOnly size="large" />
          </Stack>
        </Container>
        <Container maxWidth="lg" sx={{ mb: 5 }}>
          <StyledTypography variant="subtitle1">Details</StyledTypography>
          <Grid container columnSpacing={5} sx={{ mt: 2 }}>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">All</Typography>
                    <Typography variant="subtitle2">(270)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">5 Star</Typography>
                    <Typography variant="subtitle2">(173)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">4 Star</Typography>
                    <Typography variant="subtitle2">(76)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">3 Star</Typography>
                    <Typography variant="subtitle2">(21)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">2 Star</Typography>
                    <Typography variant="subtitle2">(0)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2}>
              <Card variant="outlined">
                <CardContent>
                  <Stack alignItems="center">
                    <Typography variant="subtitle2">1 Star</Typography>
                    <Typography variant="subtitle2">(0)</Typography>
                  </Stack>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Container>
        <Container maxWidth="lg" sx={{ mb: 10 }}>
          <Card variant="outlined">
            <CardContent>
              <Grid container>
                <Grid item xs={8}>
                  <Stack spacing={2}>
                    <Box sx={{ display: "flex", alignItems: "center" }}>
                      <Person />
                      <StyledTypography variant="subtitle2">
                        TienHoang1211
                      </StyledTypography>
                      <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Maecenas vehicula placerat faucibus. Sed convallis
                        tempus rhoncus. Sed vel dapibus est.
                      </Typography>
                    </Box>
                  </Stack>
                </Grid>
                <Grid item xs={4}></Grid>
              </Grid>
            </CardContent>
          </Card>
        </Container>
      </Container>
    </div>
  );
};

export default MyFeedBack;
