import {
  User,
  Settings,
  Trash2,
  LogOut,
  Calendar,
  ClipboardList,
  Home,
} from "lucide-react";
import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";

const NavBar = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const [user, setUser] = useState({ name: "" });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const userId = localStorage.getItem("userId");
        const token = localStorage.getItem("token");
        const role = localStorage.getItem("role");

        if (!userId || !token) {
          navigate("/signin");
          return;
        }

        const response = await axios.get(
          `http://localhost:8080/api/${
            role === "ROLE_ADMIN" ? "admin" : "citizen"
          }/profile/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setUser(response.data);
      } catch (error) {
        console.error("Error fetching user data:", error);
        if (error.response?.status === 401) {
          navigate("/");
        }
      }
    };

    fetchUserData();
  }, [navigate]);

  const handleHomeClick = () => {
    const userRole = localStorage.getItem("role");
    navigate(userRole === "ROLE_ADMIN" ? "/admin" : "/user");
  };

  return (
    <header className="bg-[#FBF8EF] shadow-lg">
      <div className="max-w-7xl mx-auto px-4 py-4 flex items-center justify-between">
        <div className="flex items-center space-x-6">
          <button
            onClick={handleHomeClick}
            className="flex items-center space-x-2 text-[#4A8DAB] hover:text-[#F96E2A] transition-colors duration-300"
          >
            <Home className="w-5 h-5" />
            <span>Home</span>
          </button>

          <h1 className="text-2xl font-bold text-[#4A8DAB]">
            Welcome, {user.name?.split(" ")[0]}
          </h1>
        </div>

        <div className="flex items-center space-x-6">
          <Link
            to="/events"
            className="flex items-center space-x-2 text-[#4A8DAB] hover:text-[#F96E2A] transition-colors duration-300"
          >
            <Calendar className="w-5 h-5" />
            <span>Events</span>
          </Link>

          <Link
            to="/trash"
            className="flex items-center space-x-2 text-[#4A8DAB] hover:text-[#F96E2A] transition-colors duration-300"
          >
            <ClipboardList className="w-5 h-5" />
            <span>Trash Requests</span>
          </Link>

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
      </div>
    </header>
  );
};

export default NavBar;

function DropDownMenu() {
  const navigate = useNavigate();
  return (
    <div className="absolute right-0 mt-2 w-48 bg-[#FBF8EF] rounded-lg shadow-lg py-1 z-10">
      <button
        className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300"
        onClick={() => navigate("/updateProfile")}
      >
        <Settings className="w-4 h-4 mr-2" />
        Profile Settings
      </button>
      <button
        className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300"
        onClick={() => navigate("/trash")}
      >
        <Trash2 className="w-4 h-4 mr-2" />
        Create Trash Pickup
      </button>
      <button
        className="flex items-center px-4 py-3 text-sm text-[#4A8DAB] hover:bg-[#78B3CE] hover:text-[#FBF8EF] w-full transition-colors duration-300"
        onClick={() => {
          axios
            .post(
              `http://localhost:8080/api/auth/logout/${localStorage.getItem(
                "userId"
              )}`,
              {
                headers: {
                  Authorization: "Bearer " + localStorage.getItem("token"),
                },
              }
            )
            .then((response) => {
              console.log("Logout successful:", response.data);
              localStorage.removeItem("token");
              localStorage.removeItem("userId");
              navigate("/");
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
