import { Box, Container, Typography } from "@mui/material";
import NavMenuCustomer from "../../components/NavMenuCustomer";

const HomeCustomer = () => {
  return (
    <Box>
      <NavMenuCustomer />
      <Container maxWidth="lg">

        <Typography sx={{ pt: 5 }} variant="h5">SEARCH FOR RENT NOW</Typography>
      </Container>
    </Box>
  );
};

export default HomeCustomer;
