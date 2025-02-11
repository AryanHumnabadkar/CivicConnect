// src/components/admin/TrashRequests.jsx
import { useState, useEffect } from "react";
import { CalendarRange, Check, X } from "lucide-react";
import axios from "axios";

const TrashRequests = () => {
  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [errors, setErrors] = useState({
    fetch: null, // for fetch errors
    updates: {}, // for individual update errors
  });
  const [pendingDates, setPendingDates] = useState({});

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get("http://localhost:8080/api/requests", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setRequests(response.data);
      setLoading(false);
      // Clear fetch error if successful
      setErrors((prev) => ({ ...prev, fetch: null }));
    } catch (error) {
      console.log(error);
      setErrors((prev) => ({ ...prev, fetch: "Failed to fetch requests" }));
      setLoading(false);
    }
  };

  const handleDateSelection = (requestId, date) => {
    setPendingDates((prev) => ({
      ...prev,
      [requestId]: date,
    }));
  };

  const handleServiceDateUpdate = async (requestId) => {
    try {
      const token = localStorage.getItem("token");
      await axios.put(
        `http://localhost:8080/api/requests/${requestId}`,
        { serviceDate: pendingDates[requestId] },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setPendingDates((prev) => {
        const newPending = { ...prev };
        delete newPending[requestId];
        return newPending;
      });
      // Clear update error for this request if successful
      setErrors((prev) => ({
        ...prev,
        updates: {
          ...prev.updates,
          [requestId]: null,
        },
      }));
      fetchRequests();
    } catch (error) {
      console.error("Failed to update service date:", error);
      setErrors((prev) => ({
        ...prev,
        updates: {
          ...prev.updates,
          [requestId]: "Failed to update service date",
        },
      }));
    }
  };

  const cancelDateUpdate = (requestId) => {
    setPendingDates((prev) => {
      const newPending = { ...prev };
      delete newPending[requestId];
      return newPending;
    });
  };

  return (
    <div className="space-y-4">
      <h2 className="text-xl font-bold text-[#4A8DAB] mb-6">
        Trash Pickup Requests
      </h2>
      {loading ? (
        <div>Loading...</div>
      ) : errors.fetch ? (
        <div className="text-red-500">{errors.fetch}</div>
      ) : (
        <div className="grid gap-4">
          {requests.map((request) => (
            <div key={request.id} className="bg-white p-4 rounded-lg shadow">
              <div className="flex justify-between items-start">
                <div className="space-y-2">
                  <p className="text-[#4A8DAB] font-medium">
                    {request.description}
                  </p>
                  <p className="text-sm text-[#78B3CE]">From: {request.user}</p>
                  <p className="text-sm text-[#F96E2A]">
                    Sector: {request.sector}
                  </p>
                  <div className="flex items-center gap-2 text-sm text-[#4A8DAB]">
                    <CalendarRange className="w-4 h-4" />
                    <span>
                      Request Date:{" "}
                      {new Date(request.requestDate).toLocaleDateString()}
                    </span>
                  </div>
                  <div className="flex items-center gap-2 text-sm">
                    <CalendarRange className="w-4 h-4" />
                    <span>
                      Service Date:{" "}
                      {request.serviceDate ? (
                        new Date(request.serviceDate).toLocaleDateString()
                      ) : (
                        <span className="text-orange-500">Not scheduled</span>
                      )}
                    </span>
                  </div>
                </div>
                <div className="space-y-2">
                  <input
                    type="date"
                    className="px-3 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                    min={new Date().toISOString().split("T")[0]}
                    value={pendingDates[request.id] || ""}
                    onChange={(e) =>
                      handleDateSelection(request.id, e.target.value)
                    }
                  />
                  {pendingDates[request.id] && (
                    <div className="space-y-2">
                      <div className="flex gap-2">
                        <button
                          onClick={() => handleServiceDateUpdate(request.id)}
                          className="flex items-center gap-1 px-3 py-1 bg-green-500 text-white rounded-lg hover:bg-green-600"
                        >
                          <Check className="w-4 h-4" />
                          Update Date
                        </button>
                        <button
                          onClick={() => cancelDateUpdate(request.id)}
                          className="flex items-center gap-1 px-3 py-1 bg-[#78B3CE] text-white rounded-lg hover:bg-opacity-90"
                        >
                          <X className="w-4 h-4" />
                          Cancel
                        </button>
                      </div>
                      {errors.updates[request.id] && (
                        <p className="text-red-500 text-sm mt-1">
                          {errors.updates[request.id]}
                        </p>
                      )}
                    </div>
                  )}
                  {request.serviceDate && !pendingDates[request.id] && (
                    <button
                      onClick={() => handleServiceDateUpdate(request.id, null)}
                      className="px-4 py-2 bg-[#78B3CE] text-white rounded-lg hover:bg-opacity-90"
                    >
                      Clear Date
                    </button>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default TrashRequests;
