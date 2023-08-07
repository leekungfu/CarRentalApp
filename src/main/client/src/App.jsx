import "./App.css";
import {
  Route,
  Routes,
  BrowserRouter as Router,
} from "react-router-dom";
import { Layout } from "./components/Layout";
import HomeGuest from "./containers/Home/HomeGuest";

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/homeguest" element={<HomeGuest />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;