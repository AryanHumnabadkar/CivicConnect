import { useState, useEffect } from "react";
import TrashRequestForm from "../components/TrashReqForm";
import RequestList from "../components/RequestList";
import axios from "axios";

const TrashRequestPage = () => {
  const [requests, setRequests] = useState([]);
  const [sector, setSector] = useState();

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

  return (
    <div className="min-h-screen w-full bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <div className="container mx-auto py-8 h-full">
        <h1 className="text-3xl font-bold text-[#FBF8EF] text-center mb-8">
          Trash Pickups {sector ? `in ${sector}` : ""}
        </h1>
        <div className="grid md:grid-cols-5 p-2 md:p-0 gap-8 h-full">
          <div className="md:col-span-2 h-full">
            <TrashRequestForm onSuccess={fetchRequests} />
          </div>
          <div className="md:col-span-3 h-full">
            <RequestList requests={requests} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default TrashRequestPage;
