import { useState, useEffect } from "react";
import TrashRequestForm from "../components/TrashReqForm";
import RequestList from "../components/RequestList";
import { ClipboardList, Calendar } from "lucide-react";
import axios from "axios";
import NavBar from "../components/Navbar";

const TrashRequestPage = () => {
  const [requests, setRequests] = useState([]);
  const [sector, setSector] = useState();
  const userRole = localStorage.getItem("role");

  const fetchRequests = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(`http://localhost:8080/api/requests`, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
      setRequests(response.data);
      setSector(response.data[0].sector);
    } catch (error) {
      console.error(
        "Error fetching requests:",
        error.response?.data || error.message
      );
    }
  };

  useEffect(() => {
    fetchRequests();
  }, []);

  const AdminStats = () => (
    <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6 space-y-6">
      <h2 className="text-2xl font-bold text-[#4A8DAB] mb-4">
        Request Overview
      </h2>
      <div className="grid grid-cols-2 gap-4">
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center gap-3">
            <ClipboardList className="w-8 h-8 text-[#F96E2A]" />
            <div>
              <p className="text-sm text-[#78B3CE]">Total Requests</p>
              <p className="text-2xl font-bold text-[#4A8DAB]">
                {requests.length}
              </p>
            </div>
          </div>
        </div>
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center gap-3">
            <Calendar className="w-8 h-8 text-[#F96E2A]" />
            <div>
              <p className="text-sm text-[#78B3CE]">Pending Service</p>
              <p className="text-2xl font-bold text-[#4A8DAB]">
                {requests.filter((req) => !req.serviceDate).length}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  return (
    <>
      <NavBar />
      <div className="min-h-screen w-full bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
        <div className="container mx-auto py-8 h-full">
          <h1 className="text-3xl font-bold text-[#FBF8EF] text-center mb-8">
            Trash Pickups {sector ? `in ${sector}` : ""}
          </h1>
          <div className="grid md:grid-cols-5 p-2 md:p-0 gap-8 h-full">
            <div className="md:col-span-2 h-full">
              {userRole === "ROLE_ADMIN" ? (
                <AdminStats />
              ) : (
                <TrashRequestForm onSuccess={fetchRequests} />
              )}
            </div>
            <div className="md:col-span-3 h-full">
              <RequestList requests={requests} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default TrashRequestPage;
