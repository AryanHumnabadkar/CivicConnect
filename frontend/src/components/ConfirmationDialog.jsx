/* eslint-disable react/prop-types */
const ConfirmationDialog = ({ onClose, onConfirm }) => {
  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
      <div className="bg-[#FBF8EF] rounded-lg p-6 max-w-md w-full">
        <h3 className="text-xl font-semibold text-[#4A8DAB] mb-2">
          Confirm Trash Pickup Request
        </h3>
        <p className="text-[#4A8DAB] mb-6">
          Are you sure you want to submit this trash pickup request?
        </p>
        <div className="flex justify-end gap-3">
          <button
            onClick={onClose}
            className="px-6 bg-[#78B3CE] text-[#FBF8EF] py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300"
          >
            Cancel
          </button>
          <button
            onClick={onConfirm}
            className="px-6 bg-[#F96E2A] text-[#FBF8EF] py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300"
          >
            Confirm
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmationDialog;
