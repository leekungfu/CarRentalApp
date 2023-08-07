import "./App.css";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import { Layout } from "./components/Layout";
import HomeGuest from "./containers/Home/HomeGuest";
import HomeCustomer from "./containers/Home/HomeCustomer";

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/homeguest" element={<HomeGuest />} />
          <Route path="/homecustomer" element={<HomeCustomer />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
