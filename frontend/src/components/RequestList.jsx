import { useState, useEffect } from "react";
import axios from "axios";

const RequestList = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = async () => {
    try {
      //   const userId = localStorage.getItem("userId");
      const token = localStorage.getItem("token");

      const response = await axios.get(`http://localhost:8080/api/requests`, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });
      console.log(response.data);
      setRequests(response.data);
    } catch (error) {
      console.error(
        "Error fetching requests:",
        error.response?.data || error.message
      );
    }
  };

  return (
    <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
      <h2 className="text-xl text-[#4A8DAB] mb-4">Your Requests</h2>
      {requests.length === 0 ? (
        <p className="text-[#4A8DAB]">No requests found.</p>
      ) : (
        <div className="space-y-4">
          {requests.map((request) => (
            <div
              key={request.id}
              className="border border-[#78B3CE] rounded-lg p-4"
            >
              <p className="text-[#4A8DAB]">{request.description}</p>
              <p className="text-sm text-[#F96E2A] mt-2">
                Status: {request.status}
              </p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default RequestList;
