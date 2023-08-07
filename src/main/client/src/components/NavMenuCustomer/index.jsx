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
import UserMenu from "../UserMenu";
  
  const pages = ["Home", "About us", <UserMenu/>];
  
  const NavMenuCustomer = () => {
    return (
      <Fragment>
        <AppBar position="sticky">
          <Container maxWidth="xl">
            <Toolbar>
              <DirectionsCarIcon
                sx={{ display: { xs: "none", md: "flex" }, mr: 1 }}
              />
              <Typography
                variant="h5"
                noWrap
                component="a"
                href="#"
                sx={{
                  fontFamily: "inherit",
                  fontWeight: 800,
                  letterSpacing: ".1rem",
                  color: "inherit",
                  textDecoration: "none",
                }}
              >
                RENTAL A CAR TODAY
              </Typography>
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
                    sx={{ color: "white", display: "block", fontWeight: 700 }}
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
  
  export default NavMenuCustomer;
  