import { Box, Card, CardContent, Container, Typography } from "@mui/material";
import NavMenuCustomer from "../../components/NavMenuCustomer";
import { Label } from "@mui/icons-material";
import styled from "styled-components";
import * as React from "react";
import dayjs from "dayjs";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";

const StyledDateTimePicker = styled(DateTimePicker)`
  &.custom-date {
    padding: 7.5px 14px;
    font-size: 14;
  }
`;

const HomeCustomer = () => {
  
  const [value, setValue] = React.useState(dayjs('2022-04-07'));
  return (
    <Box>
      <NavMenuCustomer />
      <Container maxWidth="md">
        <Typography sx={{ pt: 5 }} variant="h5">
          SEARCH FOR RENT NOW
        </Typography>
        <Card>
          <CardContent>
            <Box direction="y" style={{ flexDirection: "column" }}>
              <div className="actions" style={{ width: "100%" }}>
                <span>Từ ngày</span>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DateTimePicker
                    renderInput={(props) => <TextField {...props} />}
                    label="DateTimePicker"
                    value={value}
                    onChange={(newValue) => {
                      setValue(newValue);
                    }}
                  />
                </LocalizationProvider>
              </div>
            </Box>
          </CardContent>
        </Card>
      </Container>
    </Box>
  );
};

export default HomeCustomer;
