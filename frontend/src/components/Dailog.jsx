/* eslint-disable react/prop-types */
import { Check, AlertTriangle, X, Info } from "lucide-react";

const Dialog = ({
  isOpen,
  onClose,
  onConfirm,
  title,
  message,
  type = "confirm", // confirm, success, error, info
  confirmText = "Confirm",
  cancelText = "Cancel",
}) => {
  if (!isOpen) return null;

  const icons = {
    confirm: <AlertTriangle className="w-6 h-6 text-[#F96E2A]" />,
    success: <Check className="w-6 h-6 text-green-500" />,
    error: <X className="w-6 h-6 text-red-500" />,
    info: <Info className="w-6 h-6 text-[#4A8DAB]" />,
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
      <div className="bg-[#FBF8EF] rounded-lg p-6 max-w-md w-full">
        <div className="flex items-center gap-3 mb-4">
          {icons[type]}
          <h3 className="text-xl font-semibold text-[#4A8DAB]">{title}</h3>
        </div>

        <p className="text-[#4A8DAB] mb-6">{message}</p>

        <div className="flex justify-end gap-3">
          {type === "confirm" && (
            <button
              onClick={onClose}
              className="px-6 py-2 bg-[#78B3CE] text-[#FBF8EF] rounded-lg hover:bg-opacity-90 transition-all duration-300"
            >
              {cancelText}
            </button>
          )}
          <button
            onClick={type === "confirm" ? onConfirm : onClose}
            className="px-6 py-2 bg-[#F96E2A] text-[#FBF8EF] rounded-lg hover:bg-opacity-90 transition-all duration-300"
          >
            {confirmText}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Dialog;
