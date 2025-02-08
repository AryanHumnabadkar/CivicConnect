import { User, Settings, Trash2, LogOut } from "lucide-react";
import { useEffect, useState } from "react";
import { EventsList } from "../components/EventsList";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export const UserDashBoard = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const [user, setUser] = useState({
    id: "",
    name: "",
    email: "",
    role: "",
    address: null,
  });
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("token") === null) {
      navigate("/signin");
    } else {
      const userId = localStorage.getItem("userId");
      axios
        .get(`http://localhost:8080/api/citizen/profile/${userId}`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          setUser(response.data);
          console.log(response.data);
        });
    }
  });

  return (
    <div className="min-h-screen bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <header className="bg-[#FBF8EF] shadow-lg">
        <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
          <h1 className="text-2xl font-bold text-[#4A8DAB]">
            Hey there, {user.name.split(" ")[0]}
          </h1>

          <div className="relative">
            <button
              onClick={() => setShowDropdown(!showDropdown)}
              className="flex items-center space-x-2 text-[#4A8DAB] hover:text-[#F96E2A] transition-colors duration-300"
            >
              <div className="w-10 h-10 rounded-full bg-[#78B3CE] flex items-center justify-center hover:bg-[#4A8DAB] transition-colors duration-300">
                <User className="w-6 h-6 text-[#FBF8EF]" />
              </div>
            </button>

            {showDropdown && <DropDownMenu />}
          </div>
        </div>
      </header>

      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="grid grid-cols-1 md:grid-cols-5 gap-6">
          <div className="md:col-span-2">
            <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6 transform transition-all hover:scale-102">
              <h2 className="text-xl font-bold text-[#4A8DAB] mb-4">
                Newsletters
              </h2>
              {/* Newsletter content here */}
            </div>
          </div>

          <div className="md:col-span-3">
            <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6 transform transition-all hover:scale-102">
              <h2 className="text-xl font-bold text-[#4A8DAB] mb-4">
                Upcoming Events
              </h2>
              <div className="flex justify-center space-x-4 mb-6">
                <button
                  className="bg-[#F96E2A] text-[#FBF8EF] px-6 py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
                  onClick={() => navigate("/home")}
                >
                  Show all events
                </button>
                <button
                  className="bg-[#F96E2A] text-[#FBF8EF] px-6 py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
                  onClick={() => navigate("/home")}
                >
                  Register an event
                </button>
              </div>
              <EventsList />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

function DropDownMenu() {
  const navigate = useNavigate();
  return (
    <div className="absolute right-0 mt-2 w-48 bg-[#FBF8EF] rounded-lg shadow-lg py-1 z-10">
      <button className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300">
        <Settings className="w-4 h-4 mr-2" />
        Profile Settings
      </button>
      <button
        className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300"
        onClick={() => navigate("/trash/create")}
      >
        <Trash2 className="w-4 h-4 mr-2" />
        Create Trash Pickup
      </button>
      <button
        className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300"
        onClick={() => {
          axios
            .post(`http://localhost:8080/api/auth/logout`, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            })
            .then((response) => {
              console.log("Logout successful:", response.data);
              localStorage.removeItem("token");
              localStorage.removeItem("userId");
              navigate("/signin");
            })
            .catch((err) => {
              console.error("Logout error:", err);
              alert("Failed to logout. Please try again.");
            });
        }}
      >
        <LogOut className="w-4 h-4 mr-2" />
        Logout
      </button>
    </div>
  );
}
