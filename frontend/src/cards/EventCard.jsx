/* eslint-disable react/prop-types */
import { Calendar, Clock, MapPin, User, Tag } from "lucide-react";

const EventCard = ({ event }) => {
  return (
    <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6 transform transition-all hover:scale-102">
      <div className="space-y-4">
        <div className="flex justify-between items-start">
          <h3 className="text-xl font-bold text-[#4A8DAB]">{event.name}</h3>
          <span className="px-3 py-1 bg-[#F96E2A] text-white rounded-full text-sm">
            {event.status}
          </span>
        </div>

        <p className="text-gray-600">{event.description}</p>

        <div className="grid grid-cols-2 gap-4">
          <div className="flex items-center gap-2 text-[#78B3CE]">
            <Calendar className="w-4 h-4" />
            <span>{new Date(event.date).toLocaleDateString()}</span>
          </div>
          <div className="flex items-center gap-2 text-[#78B3CE]">
            <Clock className="w-4 h-4" />
            <span>{event.time}</span>
          </div>
          <div className="flex items-center gap-2 text-[#78B3CE]">
            <User className="w-4 h-4" />
            <span>{event.userName}</span>
          </div>
          <div className="flex items-center gap-2 text-[#78B3CE]">
            <MapPin className="w-4 h-4" />
            <span>{event.sectorName}</span>
          </div>
          <div className="flex items-center gap-2 text-[#78B3CE]">
            <Tag className="w-4 h-4" />
            <span>{event.eventType}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EventCard;
