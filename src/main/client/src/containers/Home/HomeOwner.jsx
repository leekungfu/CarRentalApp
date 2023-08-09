import {
  Box,
  Button,
  Card,
  CardContent,
  Container,
  Grid,
  IconButton,
  Stack,
  Typography,
} from "@mui/material";
import NavMenuCustomer from "../../components/NavMenuCustomer";
import {
  CarRental,
  CurrencyExchange,
  CurrencyExchangeOutlined,
  HowToReg,
  MoneyOff,
  Payment,
  SwipeRight,
} from "@mui/icons-material";

const HomeOwner = () => {
  return (
    <>
      <Box>
        <NavMenuCustomer />
        <Container maxWidth="lg">
          <Card elevation={0}>
            <CardContent>
              <Box sx={{ pt: 3, pb: 4 }}>
                <Typography variant="h4" sx={{ fontWeight: 600 }}>
                  Don't miss out of your benefit today!
                </Typography>
              </Box>
              <Grid container columnSpacing={2} rowSpacing={5}>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <CurrencyExchange />
                    <Typography variant="h6">
                      How the insurance works?
                    </Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    From the minute you hand the keys over till the second you
                    get them back you are covered. Your private insurance is not
                    affected.
                  </Typography>
                </Grid>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <MoneyOff />
                    <Typography variant="h6">It's completely free</Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    We offer both owners and renters free sign-ups. It's only
                    once vehicle is rented-out that a share is deducted to cover
                    admin and insurance.
                  </Typography>
                </Grid>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <SwipeRight />
                    <Typography variant="h6">
                      You decide the renting price
                    </Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    When you list cars you decide these price. We can help with
                    as to price, but finally you decide!
                  </Typography>
                </Grid>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <CarRental />
                    <Typography variant="h6">
                      Handling over your vehicle
                    </Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    You arrange th time and location for the exchange of your
                    vehicle with the renter.Both parties will need to agree and
                    sign the vehicle rental sheet before and after key handover.
                  </Typography>
                </Grid>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <HowToReg />
                    <Typography variant="h6">You are in charge</Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    All renters are pre-screened by us to ensure safety and get
                    your approval. If you do not feel comfortable with someone
                    you are able to decline a booking.
                  </Typography>
                </Grid>
                <Grid item xs={4}>
                  <Stack
                    direction="row"
                    spacing={1}
                    sx={{ pb: 2, alignItems: "center" }}
                  >
                    <Payment />
                    <Typography variant="h6">Set payment</Typography>
                  </Stack>
                  <Typography variant="subtitle1">
                    We pay you once a month, and you can always view much your
                    car has earned under you user profile.
                  </Typography>
                </Grid>
              </Grid>
              <Box sx={{ pt: 10, pb: 3, display: "flex", justifyContent: "center" }}>
                <Typography variant="h5" sx={{ fontWeight: 600 }}>
                  Make money on your cars right now
                </Typography>
              </Box>
              <Stack direction="row" spacing={3} justifyContent="center">
                <Button>Add Car</Button>
                <Button>List your car</Button>
              </Stack>
            </CardContent>
          </Card>
        </Container>
      </Box>
    </>
  );
};

export default HomeOwner;
