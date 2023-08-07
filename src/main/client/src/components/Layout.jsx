import React, { Component } from "react";
import { Box } from "@mui/material";
import NavBar from "./NavBar";
import Footer from "./Footer";

export class Layout extends Component {
  static displayName = Layout.name;

  render() {
    return (
      <>
        <Box
          className="app-layout"
          sx={{
            display: "flex",
            flexDirection: "column",
            height: "100%",
          }}
        >
          <NavBar />
          <Box
            className="app-content"
            sx={{
              position: "relative",
              width: "100%",
              flex: 1,
            }}
          >
            {this.props.children}
          </Box>
        </Box>
      </>
    );
  }
}
