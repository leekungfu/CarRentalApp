import { createTheme } from "@mui/material/styles";
import { red } from "@mui/material/colors";

const DefaultTheme = createTheme({
  palette: {
    secondary: red,
  },
  components: {
    // Name of the component
    MuiButton: {
      styleOverrides: {
        // Name of the slot
        root: {
          // Some CSS
          textTransform: "none",
          color: "black",
          backgroundColor: "#fca311",
          fontWeight: 600,
        },
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        sizeSmall: {
          lineHeight: "1 !important",
          letterSpacing: "0.00938em !important",
        },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        sizeSmall: {
          lineHeight: "1.1876em !important",
          letterSpacing: "0.00938em !important",
          "& .MuiInputBase-input:not(.MuiInputBase-inputMultiline)": {
            paddingTop: `6.5px !important`,
            paddingBottom: "6.5px !important",
            height: "1.1876em !important",
          },
        },
      },
    },
    MuiInput: {
      styleOverrides: {
        input: {
          color: "hsl(0, 0%, 20%)",
        },
      },
    },
    MuiSelect: {
      styleOverrides: {
        outlined: {
          color: "hsl(0, 0%, 20%)",
          fontSize: "0.88rem",
        },
      },
    },
  },
});

export default DefaultTheme;
