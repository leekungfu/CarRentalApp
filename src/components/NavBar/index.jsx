import {
  AppBar,
  Box,
  Button,
  Container,
  CssBaseline,
  IconButton,
  Link,
  Menu,
  MenuItem,
  Toolbar,
  Typography,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { useState, Fragment } from "react";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import HideOnScroll from "./HideOnScroll";

const pages = ["Home", "About us", "Sign up", "Log in"];

const NavBar = (props) => {
  const [anchorElNav, setAnchorElNav] = useState(null);

  const handleOpenNavMenu = (e) => {
    setAnchorElNav(e.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <Fragment>
      <CssBaseline />
      <HideOnScroll {...props}>
        <AppBar position="static">
          <Container maxWidth="xl">
            <Toolbar disableGutters>
              <DirectionsCarIcon
                sx={{ display: { xs: "none", md: "flex" }, mr: 1 }}
              />
              <Typography
                variant="h6"
                noWrap
                component="a"
                href="/"
                sx={{
                  mr: 2,
                  display: { xs: "none", md: "flex" },
                  fontFamily: "monospace",
                  fontWeight: 700,
                  letterSpacing: ".3rem",
                  color: "inherit",
                  textDecoration: "none",
                }}
              >
                Rental A Car Today
              </Typography>

              <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
                <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleOpenNavMenu}
                  color="inherit"
                >
                  <MenuIcon />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorElNav}
                  anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "left",
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "left",
                  }}
                  open={Boolean(anchorElNav)}
                  onClose={handleCloseNavMenu}
                  sx={{
                    display: { xs: "block", md: "none" },
                  }}
                >
                  {pages.map((page) => (
                    <MenuItem key={page} onClick={handleCloseNavMenu}>
                      <Typography textAlign="center">{page}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
              <DirectionsCarIcon
                sx={{ display: { xs: "flex", md: "none" }, mr: 1 }}
              />
              <Typography
                variant="h5"
                noWrap
                component="a"
                href=""
                sx={{
                  mr: 2,
                  display: { xs: "flex", md: "none" },
                  flexGrow: 1,
                  fontFamily: "monospace",
                  fontWeight: 700,
                  letterSpacing: ".3rem",
                  color: "inherit",
                  textDecoration: "none",
                }}
              >
                Rental A Car Today
              </Typography>
              <Box
                sx={{
                  flexGrow: 1,
                  display: { xs: "none", md: "flex" },
                  justifyContent: "end",
                }}
              >
                {pages.map((page) => (
                  <Button
                    key={page}
                    onClick={handleCloseNavMenu}
                    sx={{ my: 2, color: "white", display: "block" }}
                  >
                    {page}
                  </Button>
                ))}
              </Box>
            </Toolbar>
          </Container>
        </AppBar>
      </HideOnScroll>
      <Toolbar />
    </Fragment>
  );
};

export default NavBar;
