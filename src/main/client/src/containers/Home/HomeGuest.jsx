import {
  ElectricBolt,
  Gavel,
  InputOutlined,
  Savings,
  SupportAgent,
  Visibility,
  VisibilityOff,
} from "@mui/icons-material";
import {
  FormControl,
  InputLabel,
  Input,
  FormHelperText,
  TextField,
  Box,
  OutlinedInput,
  InputAdornment,
  IconButton,
  Container,
  Grid,
  Typography,
  Stack,
  Paper,
  Card,
  CardContent,
  Button,
  CardHeader,
  Avatar,
} from "@mui/material";
import { useState } from "react";
import Footer from "../../components/Footer";

const HomeGuest = () => {
  return (
    <Box>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Box
            component="div"
            sx={{
              backgroundImage: 'url("../car_girl.jpg")',
              backgroundSize: "cover",
              backgroundPosition: "center",
              backgroundRepeat: "no-repeat",
              width: "100%",
              height: "700px",
            }}
          >
            <Container maxWidth="lg" sx={{ pb: 10 }}>
              <Stack spacing={2} sx={{ color: "white" }}>
                <Grid>
                  <Typography
                    sx={{ display: "flex", justifyContent: "center" }}
                    variant="h2"
                  >
                    Fast & Easy Way To Rent A Car
                  </Typography>
                  <Typography
                    sx={{
                      width: "100%",
                      display: "flex",
                      justifyContent: "center",
                      textAlign: "center",
                    }}
                    variant="h6"
                  >
                    Car is an essential vehicle in our life. It's created to
                    reserve lots of our purpose. Well, you need to rent a car,
                    or you have cars for rent - You're in the right place
                  </Typography>
                </Grid>

                <Card>
                  <CardContent>
                    <Stack direction="row" spacing={2}>
                      <Grid item xs={6}>
                        <Paper sx={{ p: 1, textAlign: "center" }} elevation={0}>
                          <Grid>
                            <Box sx={{ mx: 2, my: 5, padding: "20px" }}>
                              <Typography
                                sx={{ fontWeight: 700, pb: 5 }}
                                variant="h5"
                              >
                                Better Way to Rent Your Perfect Cars
                              </Typography>
                              <Typography sx={{ pb: 5, mx: 6 }} variant="h6">
                                List your car and make money from your asset
                                today!
                              </Typography>
                              <Button
                                sx={{ p: 2, width: "70%" }}
                                variant="contained"
                              >
                                Find A Rental Car Shop Near You
                              </Button>
                            </Box>
                          </Grid>
                        </Paper>
                      </Grid>

                      <Grid item xs={6}>
                        <Paper sx={{ p: 1, textAlign: "center" }} elevation={0}>
                          <Box sx={{ mx: 2, my: 5, padding: "20px" }}>
                            <Typography
                              sx={{ fontWeight: 700, pb: 5 }}
                              variant="h5"
                            >
                              Or You Have Perfect Cars For Rent
                            </Typography>
                            <Typography sx={{ pb: 5, mx: 6 }} variant="h6">
                              Choose between 100's of private cars for rent at
                              really low prices!
                            </Typography>
                            <Button
                              sx={{ p: 2, width: "70%" }}
                              variant="contained"
                            >
                              Show Your Car For Rent Now
                            </Button>
                          </Box>
                        </Paper>
                      </Grid>
                    </Stack>
                  </CardContent>
                </Card>

                <Card>
                  <CardContent>
                    <Stack
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingTop: 7,
                      }}
                      direction="row"
                      spacing={2}
                    >
                      <Box>
                        <Typography
                          sx={{ color: "#1876d1", pb: 2 }}
                          variant="h6"
                        >
                          Services
                        </Typography>
                        <Typography sx={{ fontWeight: 600 }} variant="h2">
                          Why Us?
                        </Typography>
                      </Box>
                    </Stack>
                    <Stack
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingY: 10,
                      }}
                      direction="row"
                      spacing={2}
                    >
                      <Grid item xs={3}>
                        <Paper elevation={0}>
                          <Box>
                            <Box
                              sx={{
                                display: "flex",
                                justifyContent: "center",
                                pb: 2,
                              }}
                            >
                              <Avatar
                                sx={{
                                  bgcolor: "#1876d1",
                                  width: 56,
                                  height: 56,
                                }}
                              >
                                <Savings fontSize="large" />
                              </Avatar>
                            </Box>
                            <Typography
                              sx={{ fontWeight: 700, fontSize: "22px" }}
                              variant="subtitle1"
                            >
                              Save Money
                            </Typography>
                            <Typography
                              sx={{ color: "gray", mx: 4 }}
                              variant="subtitle1"
                            >
                              We have no setup or registration fees. You're only
                              charged when you rent a car So get started for
                              FREE!.
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid item xs={3}>
                        <Paper elevation={0}>
                          <Box>
                            <Box
                              sx={{
                                display: "flex",
                                justifyContent: "center",
                                pb: 2,
                              }}
                            >
                              <Avatar
                                sx={{
                                  bgcolor: "#1876d1",
                                  width: 56,
                                  height: 56,
                                }}
                              >
                                <ElectricBolt fontSize="large" />
                              </Avatar>
                            </Box>
                            <Typography
                              sx={{ fontWeight: 700, fontSize: "22px" }}
                              variant="subtitle1"
                            >
                              Convenient
                            </Typography>
                            <Typography
                              sx={{ color: "gray", mx: 4 }}
                              variant="subtitle1"
                            >
                              We have a large selection of privately owned cars
                              to suit your needs throughout the country.
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid item xs={3}>
                        <Paper elevation={0}>
                          <Box>
                            <Box
                              sx={{
                                display: "flex",
                                justifyContent: "center",
                                pb: 2,
                              }}
                            >
                              <Avatar
                                sx={{
                                  bgcolor: "#1876d1",
                                  width: 56,
                                  height: 56,
                                }}
                              >
                                <Gavel fontSize="large" />
                              </Avatar>
                            </Box>
                            <Typography
                              sx={{ fontWeight: 700, fontSize: "22px" }}
                              variant="subtitle1"
                            >
                              Legal And Insurance
                            </Typography>
                            <Typography
                              sx={{ color: "gray", mx: 4 }}
                              variant="subtitle1"
                            >
                              We fully cover all rentals and even provide
                              roadside assistance. Our rating system and
                              extended member profile checks provide safety.
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid item xs={3}>
                        <Paper elevation={0}>
                          <Box>
                            <Box
                              sx={{
                                display: "flex",
                                justifyContent: "center",
                                pb: 2,
                              }}
                            >
                              <Avatar
                                sx={{
                                  bgcolor: "#1876d1",
                                  width: 56,
                                  height: 56,
                                }}
                              >
                                <SupportAgent fontSize="large" />
                              </Avatar>
                            </Box>
                            <Typography
                              sx={{ fontWeight: 700, fontSize: "22px" }}
                              variant="subtitle1"
                            >
                              24/7 Support
                            </Typography>
                            <Typography
                              sx={{ color: "gray", mx: 4 }}
                              variant="subtitle1"
                            >
                              Our team is ready to support you all along the way
                              with our 24/7 hotline and services.
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                    </Stack>
                  </CardContent>
                </Card>

                <Card sx={{ backgroundColor: "#f8f9fa" }}>
                  <CardContent>
                    <Stack
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingTop: 7,
                      }}
                      direction="row"
                      spacing={2}
                    >
                      <Box>
                        <Typography
                          sx={{ color: "#1876d1", pb: 2 }}
                          variant="h6"
                        >
                          Testimonial
                        </Typography>
                        <Typography sx={{ fontWeight: 600 }} variant="h2">
                          What People Say?
                        </Typography>
                      </Box>
                    </Stack>
                    <Stack
                      direction="row"
                      spacing={2}
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingY: 10,
                      }}
                    >
                      <Grid items xs={4}>
                        <Paper elevation={0}>
                          <Box sx={{ pt: 4, pb: 4 }}>
                            <Box
                              sx={{ display: "flex", justifyContent: "center" }}
                            >
                              <Avatar
                                sx={{ width: 80, height: 80 }}
                                src="../person_3.jpg"
                              />
                            </Box>
                            <Typography
                              variant="subtitle1"
                              sx={{ mx: 6, pt: 3, pb: 5 }}
                            >
                              Far far away, behind the word mountains, far from
                              the countries Vokalia and Consonantia, there live
                              the blind texts.
                            </Typography>
                            <Typography variant="subtitle2">
                              Roger Scott
                            </Typography>
                            <Typography
                              sx={{ color: "#1876d1" }}
                              variant="subtitle2"
                            >
                              System Analyst
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={0}>
                          <Box sx={{ pt: 4, pb: 4 }}>
                            <Box
                              sx={{ display: "flex", justifyContent: "center" }}
                            >
                              <Avatar
                                sx={{ width: 80, height: 80 }}
                                src="../person_3.jpg"
                              />
                            </Box>
                            <Typography
                              variant="subtitle1"
                              sx={{ mx: 6, pt: 3, pb: 5 }}
                            >
                              Far far away, behind the word mountains, far from
                              the countries Vokalia and Consonantia, there live
                              the blind texts.
                            </Typography>
                            <Typography variant="subtitle2">
                              Roger Scott
                            </Typography>
                            <Typography
                              sx={{ color: "#1876d1" }}
                              variant="subtitle2"
                            >
                              System Analyst
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={0}>
                          <Box sx={{ pt: 4, pb: 4 }}>
                            <Box
                              sx={{ display: "flex", justifyContent: "center" }}
                            >
                              <Avatar
                                sx={{ width: 80, height: 80 }}
                                src="../person_3.jpg"
                              />
                            </Box>
                            <Typography
                              variant="subtitle1"
                              sx={{ mx: 6, pt: 3, pb: 5 }}
                            >
                              Far far away, behind the word mountains, far from
                              the countries Vokalia and Consonantia, there live
                              the blind texts.
                            </Typography>
                            <Typography variant="subtitle2">
                              Roger Scott
                            </Typography>
                            <Typography
                              sx={{ color: "#1876d1" }}
                              variant="subtitle2"
                            >
                              System Analyst
                            </Typography>
                          </Box>
                        </Paper>
                      </Grid>
                    </Stack>
                  </CardContent>
                </Card>
                <Card>
                  <CardContent>
                    <Stack
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingTop: 7,
                      }}
                      direction="row"
                      spacing={2}
                    >
                      <Box>
                        <Typography
                          sx={{ color: "#1876d1", pb: 2 }}
                          variant="h6"
                        >
                          Location
                        </Typography>
                        <Typography sx={{ fontWeight: 600 }} variant="h2">
                          Where To Find Us?
                        </Typography>
                      </Box>
                    </Stack>
                    <Stack
                      direction="row"
                      spacing={2}
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                        paddingTop: 10,
                        paddingBottom: 3,
                      }}
                    >
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../hanoi.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Ha Noi
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                80+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../saigon.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Ho Chi Minh City
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                100+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../danang.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Da Nang - Hoi An
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                90+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                    </Stack>
                    <Stack
                      direction="row"
                      spacing={2}
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        textAlign: "center",
                      }}
                    >
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../nhatrang.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Nha Trang
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                50+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../quangninh.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Quang Ninh
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                70+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                      <Grid items xs={4}>
                        <Paper elevation={5}>
                          <Box
                            component="div"
                            sx={{
                              backgroundImage: 'url("../dalat.jpg")',
                              backgroundSize: "cover",
                              width: "100%",
                              height: "300px",
                            }}
                          >
                            <Box>
                              <Typography
                                sx={{ fontSize: "20px" }}
                                variant="subtitle2"
                              >
                                Da Lat
                              </Typography>
                              <Typography
                                sx={{ color: "#1876d1" }}
                                variant="subtitle2"
                              >
                                80+ cars
                              </Typography>
                            </Box>
                          </Box>
                        </Paper>
                      </Grid>
                    </Stack>
                  </CardContent>
                </Card>
              </Stack>
            </Container>
            <Footer />
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default HomeGuest;
