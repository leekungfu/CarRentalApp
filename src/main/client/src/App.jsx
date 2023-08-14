import "./App.css";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import { Layout } from "./components/Layout";
import HomeGuest from "./containers/Home/HomeGuest";
import HomeCustomer from "./containers/Home/HomeCustomer";

import "rsuite/dist/rsuite.min.css";
import { createPack } from "react-component-pack";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { ThemeProvider as StyledThemeProvider } from "styled-components";
import DefaultTheme from "./shared/DefaultTheme";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import HomeOwner from "./containers/Home/HomeOwner";
import AddCar from "./components/Dialogs/AddCar";
import ProfileTabs from "./containers/Account/Profile";
import MyCars from "./containers/Account/Car";
import RentNow from "./components/RentNow";

const ProviderPack = createPack(
  (props) => <ThemeProvider theme={DefaultTheme} {...props} />,
  (props) => <StyledThemeProvider theme={DefaultTheme} {...props} />,
  (props) => (
    <LocalizationProvider
      dateAdapter={AdapterDayjs}
      adapterLocale="vn"
      {...props}
    />
  )
);

function App() {
  return (
    <ProviderPack>
      <CssBaseline />
      <Router>
        <Layout>
          <Routes>
            <Route index={true} element={<HomeGuest />} />
            <Route path="/homecustomer" element={<HomeCustomer />} />
            <Route path="/homeowner" element={<HomeOwner/>} />
            <Route path="/addcar" element={<AddCar/>} />
            <Route path="/profile" element={<ProfileTabs/>} />
            <Route path="/booking"/>
            <Route path="/wallet" />
            <Route path="/cars" element={<MyCars />} />
            <Route path="/reports" />
            <Route path="/rentnow" element={<RentNow />} />
          </Routes>
        </Layout>
      </Router>
    </ProviderPack>
  );
}

export default App;
