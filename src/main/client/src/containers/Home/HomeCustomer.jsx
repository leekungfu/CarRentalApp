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
import NavMenuCustomer from "../../components/NavMenuCustomer";
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

const data = [
  {
    src: "https://i.ytimg.com/vi/pLqipJNItIo/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLBkklsyaw9FxDmMKapyBYCn9tbPNQ",
    title: "Don Diablo @ Tomorrowland Main Stage 2019 | Official…",
    channel: "Don Diablo",
    views: "396k views",
    createdAt: "a week ago",
  },
  {
    src: "https://i.ytimg.com/vi/_Uu12zY01ts/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCpX6Jan2rxrCAZxJYDXppTP4MoQA",
    title: "Queen - Greatest Hits",
    channel: "Queen Official",
    views: "40M views",
    createdAt: "3 years ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
  {
    src: "https://i.ytimg.com/vi/pLqipJNItIo/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLBkklsyaw9FxDmMKapyBYCn9tbPNQ",
    title: "Don Diablo @ Tomorrowland Main Stage 2019 | Official…",
    channel: "Don Diablo",
    views: "396k views",
    createdAt: "a week ago",
  },
  {
    src: "https://i.ytimg.com/vi/_Uu12zY01ts/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCpX6Jan2rxrCAZxJYDXppTP4MoQA",
    title: "Queen - Greatest Hits",
    channel: "Queen Official",
    views: "40M views",
    createdAt: "3 years ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
  {
    src: "https://i.ytimg.com/vi/pLqipJNItIo/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLBkklsyaw9FxDmMKapyBYCn9tbPNQ",
    title: "Don Diablo @ Tomorrowland Main Stage 2019 | Official…",
    channel: "Don Diablo",
    views: "396k views",
    createdAt: "a week ago",
  },
  {
    src: "https://i.ytimg.com/vi/_Uu12zY01ts/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCpX6Jan2rxrCAZxJYDXppTP4MoQA",
    title: "Queen - Greatest Hits",
    channel: "Queen Official",
    views: "40M views",
    createdAt: "3 years ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
  {
    src: "https://i.ytimg.com/vi/kkLk2XWMBf8/hqdefault.jpg?sqp=-oaymwEYCNIBEHZIVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLB4GZTFu1Ju2EPPPXnhMZtFVvYBaw",
    title: "Calvin Harris, Sam Smith - Promises (Official Video)",
    channel: "Calvin Harris",
    views: "130M views",
    createdAt: "10 months ago",
  },
];

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

const HomeCustomer = (props) => {
  const theme = useTheme();
  const grid = useRef(null);
  const [provinceName, setProvinceName] = useState([]);
  const handleChange = (event) => {
    const { target: value } = event;
    setProvinceName(typeof value === "string" ? value.split(",") : value);
  };
  const { loading = false } = props;

  return (
    <Box>
      <NavMenuCustomer />
      <Container maxWidth="lg" sx={{ pb: 10 }}>
        <Card elevation={0}>
          <CardContent>
            <Typography sx={{ pt: 5, pb: 5, fontWeight: 600 }} variant="h5">
              SEARCH FOR RENT NOW
            </Typography>
            <Stack direction="row" spacing={2} alignItems="center">
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
                style={{
                  minWidth: 100,
                  border: "solid",
                }}
                variant="outlined"
                size="small"
                onClick={() => grid.current.reload()}
                endIcon={<Search />}
              >
                Search
              </Button>
            </Stack>
          </CardContent>
        </Card>
      </Container>
      <Container maxWidth="lg">
        <Card elevation={0}>
          <CardContent>
            <Stack direction="row" alignItems="center" sx={{ pb: 1 }}>
              <List fontSize="large" />
              <Typography variant="h6">
                List cars available for rent:
              </Typography>
            </Stack>
            <Grid container columnSpacing={2} rowSpacing={3}>
              {(loading ? Array.from(new Array(4)) : data).map(
                (item, index) => (
                  <Grid item xs={4} key={index}>
                    {item ? (
                      <Box
                        sx={{
                          border: "0.5px solid #ccc",
                          borderRadius: "10px",
                          overflow: "hidden",
                        }}
                      >
                        <img
                          style={{ width: "100%", height: 210 }}
                          alt={item.title}
                          src={item.src}
                        />
                      </Box>
                    ) : (
                      <Skeleton
                        variant="rectangular"
                        width={210}
                        height={118}
                      />
                    )}

                    {item ? (
                      <Box sx={{ pr: 2 }}>
                        <Typography gutterBottom variant="body2">
                          {item.title}
                        </Typography>
                        <Typography
                          display="block"
                          variant="caption"
                          color="text.secondary"
                        >
                          {item.channel}
                        </Typography>
                        <Typography variant="caption" color="text.secondary">
                          {`${item.views} • ${item.createdAt}`}
                        </Typography>
                      </Box>
                    ) : (
                      <Box sx={{ pt: 0.5 }}>
                        <Skeleton />
                        <Skeleton width="60%" />
                      </Box>
                    )}
                  </Grid>
                )
              )}
            </Grid>

            <Pagination
              sx={{ display: "flex", justifyContent: "end", mt: 10 }}
              count={10}
              color="standard"
            />
          </CardContent>
        </Card>
      </Container>
    </Box>
  );
};

export default HomeCustomer;
