import { useState } from "react";
import RegisterEventForm from "../components/RegisterEventForm";

import Navbar from "../components/Navbar";
import EventsList from "../components/EventsList";

const EventsPage = () => {
  const [showRegisterForm, setShowRegisterForm] = useState(false);

  return (
    <div className="min-h-screen bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <Navbar />
      <div className="max-w-7xl mx-auto px-4 py-8">
        <div className="flex justify-end mb-6">
          <button
            onClick={() => setShowRegisterForm(!showRegisterForm)}
            className="px-6 py-2 bg-[#F96E2A] text-white rounded-lg hover:bg-opacity-90 transition-all"
          >
            {showRegisterForm ? "View All Events" : "Register New Event"}
          </button>
        </div>

        {showRegisterForm ? (
          <div className="max-w-3xl mx-auto">
            <RegisterEventForm
              onSuccess={() => {
                setShowRegisterForm(false);
              }}
            />
          </div>
        ) : (
          <EventsList />
        )}
      </div>
    </div>
  );
};

export default EventsPage;
