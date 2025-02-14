/* eslint-disable react/prop-types */
import { useState } from "react";
import { ChevronLeft, ChevronRight, Trash2, Users } from "lucide-react";
import HomeNavbar from "../components/HomeNavbar";

const images = [
  "/civic1.webp", // Remove ../../public as public is the root for static files
  "/civic2.webp",
  "/civic3.jpg",
];

const Homepage = () => {
  const [currentImage, setCurrentImage] = useState(0);

  const nextImage = () => {
    setCurrentImage((prev) => (prev + 1) % images.length);
  };

  const prevImage = () => {
    setCurrentImage((prev) => (prev - 1 + images.length) % images.length);
  };

  return (
    <div className="min-h-screen bg-[#FBF8EF]">
      <HomeNavbar />

      {/* Update carousel container and image styles */}
      <div className="relative h-[500px] bg-[#4A8DAB] overflow-hidden">
        <div className="absolute inset-0">
          <img
            src={images[currentImage]}
            alt={`banner-${currentImage + 1}`}
            className="w-full h-full object-cover object-center"
            style={{
              // maxHeight: "500px",
              height: "100%",
              width: "100%",
            }}
          />
          {/* Add overlay for better text visibility if needed */}
          <div className="absolute inset-0 bg-black/20"></div>
        </div>

        {/* Update button positions and styling */}
        <button
          onClick={prevImage}
          className="absolute left-8 top-1/2 -translate-y-1/2 p-3 bg-white/50 rounded-full hover:bg-white/75 transition-all z-10"
        >
          <ChevronLeft className="w-6 h-6 text-[#4A8DAB]" />
        </button>
        <button
          onClick={nextImage}
          className="absolute right-8 top-1/2 -translate-y-1/2 p-3 bg-white/50 rounded-full hover:bg-white/75 transition-all z-10"
        >
          <ChevronRight className="w-6 h-6 text-[#4A8DAB]" />
        </button>
      </div>

      {/* Main Content */}
      <div className="max-w-7xl mx-auto px-4 py-16 space-y-16">
        {/* Hero Section */}
        <div className="text-center space-y-4">
          <h1 className="text-4xl font-bold text-[#4A8DAB]">
            Welcome to CivicConnect
          </h1>
          <p className="text-xl text-[#78B3CE] max-w-2xl mx-auto">
            Your digital platform to connect with local government
          </p>
        </div>

        {/* Features Grid */}
        <div className="grid md:grid-cols-3 gap-8">
          <FeatureCard
            title="Trash Pickup"
            description="Schedule and track waste collection services with ease"
            icon={<Trash2 className="w-8 h-8 text-[#F96E2A]" />}
          />
          <FeatureCard
            title="Community Events"
            description="Stay updated with local events and participate in community activities"
            icon={<Users className="w-8 h-8 text-[#F96E2A]" />}
          />
          <FeatureCard
            title="Digital Management"
            description="Manage all your civic needs through a single platform"
            icon={<Users className="w-8 h-8 text-[#F96E2A]" />}
          />
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-[#4A8DAB] text-white py-8">
        <div className="max-w-7xl mx-auto px-4 text-center">
          <p>© 2025 CivicConnect. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

const FeatureCard = ({ title, description, icon }) => (
  <div className="bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow">
    <div className="flex flex-col items-center text-center space-y-4">
      {icon}
      <h3 className="text-xl font-semibold text-[#4A8DAB]">{title}</h3>
      <p className="text-[#78B3CE]">{description}</p>
    </div>
  </div>
);

export default Homepage;
