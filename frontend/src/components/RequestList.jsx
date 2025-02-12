/* eslint-disable react/prop-types */
import { useState, useEffect } from "react";
import { Filter } from "lucide-react";
import TrashReqCard from "../cards/TrashReqCard";

const RequestList = ({ requests }) => {
  const [selectedSector, setSelectedSector] = useState("all");
  const [filteredRequests, setFilteredRequests] = useState(requests);

  // Get unique sectors from requests
  const sectors = ["all", ...new Set(requests.map((req) => req.sector))];

  useEffect(() => {
    if (selectedSector === "all") {
      setFilteredRequests(requests);
    } else {
      setFilteredRequests(
        requests.filter((req) => req.sector === selectedSector)
      );
    }
  }, [selectedSector, requests]);

  return (
    <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl text-[#4A8DAB] font-bold">
          Trash-Pickup Requests in your locality
        </h2>

        <div className="flex items-center gap-2">
          <Filter className="w-5 h-5 text-[#F96E2A]" />
          <select
            value={selectedSector}
            onChange={(e) => setSelectedSector(e.target.value)}
            className="px-3 py-1 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] text-[#4A8DAB]"
          >
            {sectors.map((sector) => (
              <option key={sector} value={sector}>
                {sector === "all" ? "All Sectors" : sector}
              </option>
            ))}
          </select>
        </div>
      </div>

      {filteredRequests.length === 0 ? (
        <p className="text-[#4A8DAB]">No requests found in this sector.</p>
      ) : (
        <div className="space-y-4">
          {filteredRequests.map((request) => (
            <TrashReqCard key={request.id} request={request} />
          ))}
        </div>
      )}
    </div>
  );
};

export default RequestList;
