import { Link } from "react-router-dom";
import { Users } from "lucide-react";

const HomeNavbar = () => {
  return (
    <nav className="bg-[#FBF8EF] shadow-md">
      <div className="max-w-7xl mx-auto px-4 py-3">
        <div className="flex justify-between items-center">
          <div className="flex items-center space-x-2">
            <Users className="text-[#F96E2A] w-8 h-8" />
            <span className="text-2xl font-bold text-[#4A8DAB]">
              CivicConnect
            </span>
          </div>
          <div className="space-x-4">
            <Link
              to="/signin"
              className="px-6 py-2 text-[#4A8DAB] hover:text-[#F96E2A] transition-colors font-bold"
            >
              Sign In
            </Link>
            <Link
              to="/signup"
              className="px-6 py-2 bg-[#F96E2A] text-white rounded-lg hover:bg-opacity-90 transition-all font-bold"
            >
              Register
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default HomeNavbar;
