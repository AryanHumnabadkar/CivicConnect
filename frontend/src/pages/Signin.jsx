import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Signin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError("");

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        {
          email,
          password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      // Store user data in localStorage
      localStorage.setItem("userId", response.data.userId);
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("role", response.data.role); // Store role for future use

      // Redirect based on role
      if (response.data.role === "ROLE_ADMIN") {
        navigate("/admin");
      } else {
        navigate("/user");
      }
    } catch (err) {
      setError("Invalid email or password. Please try again.");
      console.error("Login error:", err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <div className="bg-[#FBF8EF] p-8 rounded-lg shadow-2xl transform transition-all hover:scale-105 w-full max-w-md">
        <h1 className="text-3xl font-bold text-[#4A8DAB] text-center mb-6">
          Welcome Back!
        </h1>
        <form onSubmit={handleSubmit}>
          <div className="mb-6">
            <label
              htmlFor="email"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Enter your email"
              required
            />
          </div>
          <div className="mb-6">
            <label
              htmlFor="password"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Enter your password"
              required
            />
            <div className="flex justify-end mt-1">
              <button
                type="button"
                onClick={() => navigate("/forgot-password")}
                className="text-sm text-[#4A8DAB] hover:text-[#F96E2A]"
              >
                Forgot Password?
              </button>
            </div>
          </div>
          {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
          <div className="flex justify-center">
            <button
              type="submit"
              disabled={isLoading}
              className="px-8 bg-[#F96E2A] text-[#FBF8EF] py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
            >
              {isLoading ? "Logging in..." : "Login"}
            </button>
          </div>
        </form>
        <p className="text-center text-[#4A8DAB] mt-6">
          Dont have an account?{" "}
          <a href="/signup" className="text-[#F96E2A] hover:underline">
            Sign up
          </a>
        </p>
      </div>
    </div>
  );
}

export default Signin;
