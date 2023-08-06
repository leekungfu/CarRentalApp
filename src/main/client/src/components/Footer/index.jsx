import {
  Facebook,
  Instagram,
  Phone,
  Place,
  SupportAgent,
  Twitter,
  YouTube,
} from "@mui/icons-material";
import {
  Avatar,
  Box,
  Card,
  CardContent,
  Container,
  CssBaseline,
  Grid,
  Link,
  Paper,
  Stack,
  Toolbar,
  Typography,
} from "@mui/material";
import { Fragment } from "react";

const Footer = () => {
  return (
    <Fragment>
      <Container
        sx={{
          backgroundColor: "#1876d1",
        }}
        maxWidth="xl"
      >
        <Grid container spacing={2}>
          <Grid item xs={3}>
            <Stack spacing={2} alignItems="center">
              <Box sx={{ pt: 8, pb: 1 }}>
                <Typography
                  variant="h5"
                  sx={{ color: "white", fontWeight: 600, pb: 5 }}
                >
                  CAR RENTAL
                </Typography>
                <Typography variant="subtitle1" sx={{ color: "white", pb: 5 }}>
                <Link href="#" color="inherit">Search Cars And Rates</Link>
                </Typography>
                <Typography variant="h6" sx={{ color: "white " }}>
                  Find us on:
                </Typography>
              </Box>
            </Stack>
            <Stack direction="row" spacing={2} sx={{ pb: 3, pl: "90px" }}>
              <Box sx={{ display: "flex", justifyContent: "center" }}>
                <Avatar sx={{ bgcolor: "#ad0b0b", width: 45, height: 45 }}>
                  <Facebook fontSize="medium" />
                </Avatar>
              </Box>
              <Box sx={{ display: "flex", justifyContent: "center" }}>
                <Avatar sx={{ bgcolor: "#ad0b0b", width: 45, height: 45 }}>
                  <Instagram fontSize="medium" />
                </Avatar>
              </Box>
              <Box sx={{ display: "flex", justifyContent: "center" }}>
                <Avatar sx={{ bgcolor: "#ad0b0b", width: 45, height: 45 }}>
                  <YouTube fontSize="medium" />
                </Avatar>
              </Box>
              <Box sx={{ display: "flex", justifyContent: "center" }}>
                <Avatar sx={{ bgcolor: "#ad0b0b", width: 45, height: 45 }}>
                  <Twitter fontSize="medium" />
                </Avatar>
              </Box>
            </Stack>
          </Grid>

          <Grid item xs={3}>
            <Stack spacing={2} alignItems="center">
              <Box sx={{ pt: 8, pb: 1 }}>
                <Typography
                  variant="h5"
                  sx={{ color: "white", fontWeight: 600, pb: 5 }}
                >
                  CUSTOMER ACCESS
                </Typography>
                <Typography variant="subtitle1" sx={{ color: "white", pb: 2 }}>
                  <Link href="#" color="inherit">Manage My Booking</Link>
                </Typography>
                <Typography variant="subtitle1" sx={{ color: "white ", pb: 2 }}>
                <Link href="#" color="inherit">My Wallet</Link>
                </Typography>
                <Typography variant="subtitle1" sx={{ color: "white ", pb: 2 }}>
                <Link href="#" color="inherit">My Car</Link>
                </Typography>
              </Box>
            </Stack>
          </Grid>

          <Grid item xs={2}>
            <Stack spacing={2} alignItems="center">
              <Box sx={{ pt: 8, pb: 1 }}>
                <Typography
                  variant="h5"
                  sx={{ color: "white", fontWeight: 600, pb: 5 }}
                >
                  JOIN US
                </Typography>
                <Typography variant="subtitle1" sx={{ color: "white", pb: 2 }}>
                <Link href="#" color="inherit">New User Sign Up</Link>
                </Typography>
              </Box>
            </Stack>
          </Grid>

          <Grid item xs={4}>
            <Stack spacing={2}>
              <Box sx={{ pt: 8, pb: 1 }}>
                <Typography
                  variant="h5"
                  sx={{ color: "white", fontWeight: 600, pb: 5 }}
                >
                  HAVE A QUESTION?
                </Typography>
                <Stack direction="row" spacing={1}>
                  <Place sx={{ color: "white" }} />
                  <Typography
                    noWrap
                    variant="subtitle1"
                    sx={{ color: "white", pb: 2 }}
                  >
                    Location: Alley 193 Trung Kinh - Cau Giay - Ha Noi
                  </Typography>
                </Stack>
                <Stack direction="row" spacing={1}>
                  <Phone sx={{ color: "white" }} />
                  <Typography
                    variant="subtitle1"
                    sx={{ color: "white ", pb: 2 }}
                  >
                    Tel: + 1900 8899
                  </Typography>
                </Stack>
                <Stack direction="row" spacing={1}>
                  <SupportAgent sx={{ color: "white" }} />
                  <Typography
                    variant="subtitle1"
                    sx={{ color: "white ", pb: 2 }}
                  >
                    Support: carrental@support.com
                  </Typography>
                </Stack>
              </Box>
            </Stack>
          </Grid>
        </Grid>
        <Grid>
          <Typography
            sx={{
              color: "white",
              display: "flex",
              justifyContent: "center",
              pb: 10,
            }}
            variant="h6"
          >
            Copyright © 2023 All rights reserved
          </Typography>
        </Grid>
      </Container>
    </Fragment>
  );
};

export default Footer;
