import {
  Container,
  Card,
  CardContent,
  Stack,
  Grid,
  List,
  Typography,
  Box,
  Skeleton,
  Pagination,
  Button,
  Rating,
} from "@mui/material";
import React from "react";
import BreadcrumbsMenu from "../../../components/BreadcrumbsMenu";
import { useState } from "react";
import AddCar from "../../../components/Dialogs/AddCar";
import { Add } from "@mui/icons-material";
import NavMenuUser from "../../../components/NavMenuUser";
import ViewDetails from "../../../components/Dialogs/ViewDetails";
import ConfirmDeposit from "../../../components/Modals/ConfirmDeposit";
import ConfirmPayment from "../../../components/Modals/ConfirmPayment";

const data = [
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Availabel",
  },
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Booked",
  },
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Availabel",
  },
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Stopped",
  },
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Booked",
  },
  {
    src: "car-10.jpg",
    name: "Mercedes-Benz AMG GT 2021",
    rating: 4.5,
    nor: 4,
    price: "1.500.00 VND/day",
    location: "Alley 193 Trung Kinh - Cau Giay - Ha Noi",
    status: "Availabel",
  },
];

const MyCars = (props) => {
  const { loading = false } = props;
  const [rateValue, setRateValue] = useState(4.5);
  const [openAddCar, setOpenAddCar] = useState(false);
  const [openViewDetails, setOpenViewDetails] = useState(false);

  const handleClickOpenAddCar = () => {
    setOpenAddCar(true);
  };

  const handleClickOpenViewDetails = () => {
    setOpenViewDetails(true);
  };

  const handleClose = () => {
    setOpenAddCar(false);
    setOpenViewDetails(false);
    setOpenConfirmDeposit(false);
    setOpenConfirmPayment(false);
  };

  const [openConfirmDeposit, setOpenConfirmDeposit] = useState(false);
  const [openConfirmPayment, setOpenConfirmPayment] = useState(false);

  const handleClickOpenConfirmDeposit = () => {
    setOpenConfirmDeposit(true);
  };

  const handleClickOpenConfirmPayment = () => {
    setOpenConfirmPayment(true);
  };

  return (
    <div>
      <NavMenuUser />
      <Container maxWidth="lg" sx={{ pt: 5 }}>
        <BreadcrumbsMenu />
        <Button
          sx={{
            minWidth: 100,
            color: "white",
            borderColor: "#fca311",
            "&:hover": {
              borderColor: "#fca311",
            },
            ml: 2,
            mt: 5,
          }}
          variant="outlined"
          endIcon={<Add />}
          onClick={handleClickOpenAddCar}
        >
          ADD CAR
        </Button>
        <AddCar open={openAddCar} onClose={handleClose} />
        <Card elevation={0} sx={{ mb: 5 }}>
          <CardContent>
            <Stack direction="row" alignItems="center" sx={{ pb: 1 }}>
              <List fontSize="large" />
              <Typography variant="h6">LIST CARS:</Typography>
            </Stack>
            <Grid container columnSpacing={4} rowSpacing={5}>
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
                      <Grid container>
                        <Grid item xs={12}>
                          <Typography gutterBottom variant="subtitle1">
                            {item.name}
                          </Typography>
                        </Grid>
                        <Grid item xs={6}>
                          <Stack spacing={1}>
                            <Box sx={{ display: "flex" }}>
                              <Typography
                                display="block"
                                variant="body2"
                                color="text.secondary"
                                sx={{ mr: 0.5 }}
                              >
                                Rating:
                              </Typography>
                              <Rating
                                size="small"
                                name="half-rating-read"
                                defaultValue={item.rating}
                                precision={0.5}
                                readOnly
                              />
                            </Box>
                            <Typography variant="body2" color="text.secondary">
                              Price: {item.price}
                            </Typography>
                          </Stack>
                        </Grid>
                        <Grid item xs={6}>
                          <Stack spacing={1}>
                            <Typography variant="body2" color="text.secondary">
                              No. of rides: {item.nor}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                              Status:{" "}
                              <span
                                style={{
                                  color:
                                    item.status === "Booked"
                                      ? "#15616d"
                                      : item.status === "Stopped"
                                      ? "#d00000"
                                      : "#38b000",
                                  fontWeight: "bold",
                                }}
                              >
                                {item.status}
                              </span>
                            </Typography>
                          </Stack>
                        </Grid>
                        <Grid item xs={12}>
                          <Typography
                            sx={{ pt: 1, pb: 1 }}
                            variant="body2"
                            color="text.secondary"
                          >
                            Location: {item.location}
                          </Typography>
                          <Stack direction="row" spacing={3}>
                            <Button
                              sx={{
                                width: "50%",
                                color: "white",
                                borderColor: "#fca311",
                                "&:hover": {
                                  borderColor: "#fca311",
                                },
                              }}
                              variant="outlined"
                              onClick={handleClickOpenViewDetails}
                            >
                              View details
                            </Button>
                            <ViewDetails
                              open={openViewDetails}
                              onClose={handleClose}
                            />
                            {item.status === "Booked" ? (
                              <Button
                                sx={{
                                  width: "50%",
                                  color: "white",
                                  borderColor: "#15616d",
                                  backgroundColor: "#15616d !important",
                                  "&:hover": {
                                    borderColor: "#fca311",
                                  },
                                }}
                                variant="outlined"
                                onClick={handleClickOpenConfirmPayment}
                              >
                                Confirm payment
                              </Button>
                            ) : item.status === "Stopped" ? (
                              <Button
                                disabled
                                sx={{
                                  width: "50%",
                                  color: "white",
                                  bgcolor: "white",
                                  borderColor: "#fca311",
                                  "&:hover": {
                                    borderColor: "#fca311",
                                  },
                                }}
                                variant="outlined"
                              >
                                Confirm deposit
                              </Button>
                            ) : (
                              <Button
                                sx={{
                                  width: "50%",
                                  color: "white",
                                  borderColor: "#fca311",
                                  "&:hover": {
                                    borderColor: "#fca311",
                                  },
                                }}
                                variant="outlined"
                                onClick={handleClickOpenConfirmDeposit}
                              >
                                Confirm deposit
                              </Button>
                            )}
                          </Stack>
                        </Grid>
                      </Grid>
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
              variant="outlined"
              showFirstButton
              showLastButton
            />
          </CardContent>
        </Card>
      </Container>
      <ConfirmPayment open={openConfirmPayment} onClose={handleClose} />
      <ConfirmDeposit open={openConfirmDeposit} onClose={handleClose} />
    </div>
  );
};

export default MyCars;
