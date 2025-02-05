import { User, Settings, Trash2, LogOut } from "lucide-react";
import { useState } from "react";
import { EventsList } from "../components/EventsList";
import { useNavigate } from "react-router-dom";

export const UserDashBoard = () => {
  const [showDropdown, setShowDropdown] = useState(false);

  const navigate = useNavigate();

  return (
    <>
      <div className="bg-green-200">
        <header className="bg-blue-400 shadow-sm ">
          <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
            <h1 className="text-2xl font-semibold text-gray-800">Hey User</h1>

            {/* User options  */}
            <div className="relative">
              <button
                onClick={() => setShowDropdown(!showDropdown)}
                className="flex items-center space-x-2 text-gray-700 hover:text-gray-900"
              >
                <div className="w-10 h-10 rounded-full bg-gray-200 flex items-center justify-center">
                  <User className="w-6 h-6" />
                </div>
              </button>

              {/* Dropdown Compo */}
              {showDropdown && <DropDownMenu />}
            </div>
          </div>
        </header>

        <div className="max-w-7xl mx-auto px-4 py-6">
          <div className="grid grid-cols-1 md:grid-cols-5 gap-6">
            <div className="md:col-span-2 bg-red-400">NewsLetters</div>
            <div className="md:col-span-3 bg-yellow-200">
              <h2 className="text-xl font-semibold text-gray-800">
                Upcoming Events
              </h2>
              {/* Here buttons to add a events and show all events means go to separate events page */}
              <div className="flex justify-center">
                <button
                  className="bg-amber-200 mx-2"
                  onClick={() => navigate("/home")}
                >
                  Show all events
                </button>
                <button
                  className="bg-amber-200 mx-2"
                  onClick={() => navigate("/home")}
                >
                  Register a event
                </button>
              </div>
              <EventsList />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

function DropDownMenu() {
  return (
    <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-10">
      <button className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 w-full">
        <Settings className="w-4 h-4 mr-2" />
        Profile Settings
      </button>
      <button className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 w-full">
        <Trash2 className="w-4 h-4 mr-2" />
        Create Trash Pickup
      </button>
      <button className="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 w-full">
        <LogOut className="w-4 h-4 mr-2" />
        Logout
      </button>
    </div>
  );
}
