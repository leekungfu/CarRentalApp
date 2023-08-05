import { InputOutlined } from "@mui/icons-material";
import {
  FormControl,
  InputLabel,
  Input,
  FormHelperText,
  TextField,
  Box,
} from "@mui/material";

const HomeGuest = () => {
  return (
    <Box>
      <TextField id="outlined-basic" label="Outlined" variant="outlined" />
      <TextField id="filled-basic" label="Filled" variant="filled" />
      <TextField id="standard-basic" label="Standard" variant="standard" />
    </Box>
  );
};

export default HomeGuest;
