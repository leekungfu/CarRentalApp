import {
  AppBar,
  Box,
  Button,
  ButtonBase,
  Container,
  CssBaseline,
  IconButton,
  Toolbar,
  Typography,
} from "@mui/material";
import { useState, Fragment } from "react";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import LoginForm from "../LoginForm";
import SignUpForm from "../SignUpForm";

const pages = ["HOME", "ABOUT US", <SignUpForm />, <LoginForm />];

const NavBar = () => {
  return (
    <Fragment>
      <CssBaseline />
      <AppBar position="sticky" color="default">
        <Container maxWidth="xl">
          <Toolbar>
            <Typography
              variant="h5"
              noWrap
              sx={{
                fontFamily: "inherit",
                fontWeight: 800,
                letterSpacing: ".1rem",
                textDecoration: "none",
              }}
            >
              RENTAL A CAR {" "}<span style={{ color: "#fca311" }}>TODAY</span>
            </Typography>
            <DirectionsCarIcon
            fontSize="medium"
              sx={{ display: { xs: "none", md: "flex" }, ml: 1 }}
            />
            <Box
              sx={{
                flexGrow: 1,
                display: { xs: "none", md: "flex" },
                justifyContent: "end",
              }}
            >
              {pages.map((page, index) => (
                <Button
                  key={index}
                  sx={{ display: "block", fontWeight: 600, bgcolor: "rgba(0,0,0,0)", }}
                >
                  {page}
                </Button>
              ))}
            </Box>
          </Toolbar>
        </Container>
      </AppBar>
    </Fragment>
  );
};

export default NavBar;
