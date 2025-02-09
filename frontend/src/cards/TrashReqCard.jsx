/* eslint-disable react/prop-types */
import { CalendarDays, User, MapPin } from "lucide-react";

const TrashReqCard = ({ request }) => {
  return (
    <div className="border border-[#78B3CE] rounded-lg p-4 hover:shadow-md transition-all duration-300">
      <div className="flex justify-between items-start mb-3">
        <p className="text-[#4A8DAB] font-medium text-xl">
          {request.description}
        </p>
        <span className="text-sm text-[#F96E2A] px-2 py-1 bg-[#F96E2A]/10 rounded">
          {request.status || "PENDING"}
        </span>
      </div>

      <div className="space-y-2 text-sm">
        <div className="flex items-center text-[#4A8DAB]">
          <User className="w-4 h-4 mr-2" />
          <span>{request.user}</span>
        </div>

        <div className="flex items-center text-[#4A8DAB]">
          <MapPin className="w-4 h-4 mr-2" />
          <span>{request.sector}</span>
        </div>

        <div className="flex items-center text-[#4A8DAB]">
          <CalendarDays className="w-4 h-4 mr-2" />
          <span>
            Requested: {new Date(request.requestDate).toLocaleDateString()}
          </span>
        </div>

        {request.serviceDate && (
          <div className="flex items-center text-[#F96E2A]">
            <CalendarDays className="w-4 h-4 mr-2" />
            <span>
              Service Date: {new Date(request.serviceDate).toLocaleDateString()}
            </span>
          </div>
        )}
      </div>
    </div>
  );
};

export default TrashReqCard;
