import "./App.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signup from "./pages/Signup";
import Signin from "./pages/Signin";
import Homepage from "./pages/Homepage";
import { UserDashBoard } from "./pages/UserDashboard";
import { AdminDashBoard } from "./pages/AdminDashboard";
import { UpdateProfile } from "./pages/UpdateProfile";
import TrashRequestPage from "./pages/TrashRequestPage";
import EventsPage from "./pages/EventsPage";
function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/signin" element={<Signin />} />
          <Route path="/user" element={<UserDashBoard />} />
          <Route path="/admin" element={<AdminDashBoard />} />
          <Route path="/updateProfile" element={<UpdateProfile />} />
          <Route path="/trash" element={<TrashRequestPage />} />
          <Route path="events" element={<EventsPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
