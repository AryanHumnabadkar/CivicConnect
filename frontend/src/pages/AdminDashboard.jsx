/* eslint-disable react/prop-types */
// src/pages/AdminDashboard.jsx
import { useState } from "react";
import UserManagement from "../components/UserManagement";
import TrashRequests from "../components/TrashRequests";
import NavBar from "../components/Navbar";

export const AdminDashBoard = () => {
  const [activeTab, setActiveTab] = useState("users");

  return (
    <div className="min-h-screen bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <NavBar />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
          <div className="flex space-x-4 mb-6">
            <TabButton
              active={activeTab === "users"}
              onClick={() => setActiveTab("users")}
            >
              User Management
            </TabButton>
            <TabButton
              active={activeTab === "events"}
              onClick={() => setActiveTab("events")}
            >
              Event Permits
            </TabButton>
            <TabButton
              active={activeTab === "trash"}
              onClick={() => setActiveTab("trash")}
            >
              Trash Requests
            </TabButton>
          </div>

          {activeTab === "users" && <UserManagement />}
          {activeTab === "events" && <h1>Events</h1>}
          {activeTab === "trash" && <TrashRequests />}
        </div>
      </div>
    </div>
  );
};

const TabButton = ({ children, active, onClick }) => (
  <button
    onClick={onClick}
    className={`px-6 py-2 rounded-lg transition-all duration-300 ${
      active
        ? "bg-[#F96E2A] text-[#FBF8EF]"
        : "bg-[#78B3CE] text-[#FBF8EF] hover:bg-opacity-90"
    }`}
  >
    {children}
  </button>
);
