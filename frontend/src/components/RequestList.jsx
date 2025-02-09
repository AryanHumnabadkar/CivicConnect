/* eslint-disable react/prop-types */
import TrashReqCard from "../cards/TrashReqCard";

const RequestList = ({ requests }) => {
  return (
    <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
      <h2 className="text-xl text-[#4A8DAB] mb-4 font-bold">
        Trash-Pickup Requests in your locality
      </h2>
      {requests.length === 0 ? (
        <p className="text-[#4A8DAB]">No requests found.</p>
      ) : (
        <div className="space-y-4">
          {requests.map((request) => (
            <TrashReqCard key={request.id} request={request} />
          ))}
        </div>
      )}
    </div>
  );
};

export default RequestList;
