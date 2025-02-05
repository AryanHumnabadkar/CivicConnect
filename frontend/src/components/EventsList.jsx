import { useEffect, useState } from "react";
import { EventCard } from "../cards/EventCard";
import axios from "axios";

/* eslint-disable react/prop-types */
export const EventsList = () => {
  const [events, setEvents] = useState(null);

  //TODO: add a button to fetch events
  //add pagination

  useEffect(() => {
    fetchEvents();
  }, []);

  async function fetchEvents() {
    const response = await axios.get(`http://localhost:8080/api/events`);

    console.log(response.data);
  }

  return (
    <div>
      <div className="p-2 m-1">
        {events ? (
          events.map((event, index) => <EventCard key={index} event={event} />)
        ) : (
          <p>No events</p>
        )}
      </div>

      {}
    </div>
  );
};
