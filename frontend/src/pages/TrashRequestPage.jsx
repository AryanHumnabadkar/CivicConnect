import TrashRequestForm from "../components/TrashReqForm";
import RequestList from "../components/RequestList";

const TrashRequestPage = () => {
  return (
    <div className="min-h-screen w-full bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <div className="container mx-auto px-4 py-8 h-full">
        <h1 className="text-3xl font-bold text-[#FBF8EF] text-center mb-8">
          Trash Pickup Requests
        </h1>
        <div className="grid md:grid-cols-2 gap-8 h-[calc(100vh-12rem)]">
          <div className="md:col-span-1 h-full overflow-y-auto">
            <TrashRequestForm />
          </div>
          <div className="md:col-span-1 h-full overflow-y-auto">
            <RequestList />
          </div>
        </div>
      </div>
    </div>
  );
};

export default TrashRequestPage;
