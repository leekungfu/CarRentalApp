import {
  Box,
  Button,
  Card,
  CardContent,
  Container,
  Divider,
  FormControl,
  Grid,
  Input,
  InputLabel,
  MenuItem,
  OutlinedInput,
  Pagination,
  Select,
  Skeleton,
  Stack,
  Typography,
} from "@mui/material";
import NavMenuCustomer from "../../components/NavMenuUser";
import { Label, List, Search } from "@mui/icons-material";
import styled from "styled-components";
import { useRef, useState } from "react";
import dayjs from "dayjs";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import { RSUITE_DATE_TIME_PICKER_DISPLAY_FORMAT } from "../../shared/configs/constants";
import { DateRangePicker } from "rsuite";
import { useTheme } from "@mui/material/styles";
import NavMenuUser from "../../components/NavMenuUser";

const StyledDateTimePicker = styled(DateTimePicker)`
  &.custom-date {
    padding: 7.5px 14px;
    font-size: 14;
  }
`;

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const provinces = [
  "Oliver Hansen",
  "Van Henry",
  "April Tucker",
  "Ralph Hubbard",
  "Omar Alexander",
  "Carlos Abbott",
  "Miriam Wagner",
  "Bradley Wilkerson",
  "Virginia Andrews",
  "Kelly Snyder",
];

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

const HomeCustomer = () => {
  const theme = useTheme();
  const grid = useRef(null);
  const [provinceName, setProvinceName] = useState([]);
  const handleChange = (event) => {
    const { target: value } = event;
    setProvinceName(typeof value === "string" ? value.split(",") : value);
  };

  return (
    <Box>
      <NavMenuUser />
      <Container maxWidth="lg">
        <Card elevation={0}>
          <CardContent>
            <Typography sx={{ pt: 5, pb: 5, fontWeight: 600 }} variant="h5">
              SEARCH FOR RENT NOW
            </Typography>
            <Stack direction="row" spacing={3} alignItems="center">
              <FormControl size="small" sx={{ m: 1, width: "30%" }}>
                <Select
                  size="small"
                  multiple
                  value={provinceName}
                  onChange={handleChange}
                  input={<OutlinedInput />}
                  MenuProps={MenuProps}
                >
                  {provinces.map((province, index) => (
                    <MenuItem
                      key={index}
                      value={province}
                      style={getStyles(province, provinceName, theme)}
                    >
                      {province}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <DateRangePicker
                format={"yyyy-MM-dd HH:mm:ss"}
                defaultCalendarValue={[
                  new Date("2022-02-01 00:00:00"),
                  new Date("2022-05-01 23:59:59"),
                ]}
              />
              <Button
                sx={{
                  minWidth: 100,
                  color: "white",
                  borderColor: "#fca311",
                  "&:hover": {
                    borderColor: "#fca311",
                  },
                }}
                variant="outlined"
                size="medium"
                onClick={() => grid.current.reload()}
                endIcon={<Search />}
              >
                Search
              </Button>
            </Stack>
          </CardContent>
        </Card>
      </Container>
    </Box>
  );
};

export default HomeCustomer;
