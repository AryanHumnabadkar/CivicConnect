/* eslint-disable react/prop-types */
export const EventCard = ({ eventId, event }) => {
  return (
    <>
      <div
        key={eventId}
        className="bg-amber-50 p-4 rounded-lg shadow-sm hover:shadow transition-shadow m-2"
      >
        <h3 className="text-lg font-medium text-gray-800">{event.title}</h3>
        <p className="text-gray-600 mt-1">{event.description}</p>
        <div className="mt-2 text-sm text-gray-500">
          <p>{event.datetime}</p>
          <p>{event.location}</p>
        </div>
      </div>
    </>
  );
};
