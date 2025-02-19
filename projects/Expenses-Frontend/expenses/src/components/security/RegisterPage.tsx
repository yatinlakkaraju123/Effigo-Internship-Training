import { registerUser } from "../api/AuthenticationApiService";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import LoginNavbar from "../Navbars/LoginNavbar";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function RegisterPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const navigate = useNavigate();

  // Validation Function
  const validate = () => {
    if (!username) {
      toast.error("Please provide a username", { position: "top-center" });
      return false;
    }
    if (username.length < 6) {
      toast.error("Username must be at least 6 characters long", {
        position: "top-center",
      });
      return false;
    }
    if (!password) {
      toast.error("Please provide a password", { position: "top-center" });
      return false;
    }
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(password)) {
      toast.error(
        "Password must be at least 8 characters long and contain letters, numbers, and special characters.",
        { position: "top-center" }
      );
      return false;
    }
    if (!repeatPassword) {
      toast.error("Please confirm your password", {
        position: "top-center",
      });
      return false;
    }
    if (password !== repeatPassword) {
      toast.error("Passwords do not match", { position: "top-center" });
      return false;
    }
    return true;
  };

  // Handle Form Submission
  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (validate()) {
      try {
        const response = await registerUser(username, password);
        if (response.status === 201) {
          toast.success("User created successfully!", {
            position: "top-center",
            onClose: () => navigate("/login"),
          });
        }
      } catch (error: any) {
        if (error.response?.status === 400) {
          toast.error("User already exists", { position: "top-center" });
        } else {
          toast.error("An error occurred. Please try again.", {
            position: "top-center",
          });
          console.error(error);
        }
      }
    }
  };

  return (
    <div>
      <LoginNavbar />
      <ToastContainer />
      <div className="container d-flex justify-content-center align-items-center vh-100">
        <div className="card shadow-lg p-4" style={{ maxWidth: "500px", width: "100%" }}>
          <h3 className="text-center mb-4 text-primary fw-bold">Register</h3>

          <form onSubmit={submit}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label fw-semibold">
                <i className="bi bi-person-fill me-2"></i> Username
              </label>
              <input
                type="text"
                className="form-control"
                id="username"
                placeholder="Enter username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="password" className="form-label fw-semibold">
                <i className="bi bi-lock-fill me-2"></i> Password
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder="Enter password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <small className="text-muted">
                Must contain at least 8 characters, a number, and a special character.
              </small>
            </div>

            <div className="mb-3">
              <label htmlFor="passwordrepeat" className="form-label fw-semibold">
                <i className="bi bi-check-circle me-2"></i> Confirm Password
              </label>
              <input
                type="password"
                className="form-control"
                id="passwordrepeat"
                placeholder="Re-enter password"
                value={repeatPassword}
                onChange={(e) => setRepeatPassword(e.target.value)}
              />
            </div>

            <div className="text-center">
              <button type="submit" className="btn btn-primary w-100">
                Register
              </button>
            </div>

            <div className="text-center mt-3">
              <p>
                Already have an account?{" "}
                <a href="/login" className="text-decoration-none text-primary fw-bold">
                  Login here
                </a>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;
