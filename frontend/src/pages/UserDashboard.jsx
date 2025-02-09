import { EventsList } from "../components/EventsList";
import { useNavigate } from "react-router-dom";
import NavBar from "../components/NavBar";

export const UserDashBoard = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <NavBar />
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

export default UserDashBoard;
