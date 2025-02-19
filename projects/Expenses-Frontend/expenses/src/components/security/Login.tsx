import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "./Auth";
import LoginNavbar from "../Navbars/LoginNavbar";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./AuthStyle.css"; // Import a CSS file for additional styling

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [allow, setAllow] = useState(false);
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  const navigate = useNavigate();
  const auth = useAuth();

  useEffect(() => {
    if (auth.isAuthenticated && allow) {
      toast.success("Login successful!", { position: "top-center" });
      navigate("/");
    }
  }, [auth.isAuthenticated, allow, navigate]);

  const submit = async (e:any) => {
    e.preventDefault();

    if (await auth.login(username, password)) {
      toast.success("Logged in successfully", {
        position: "top-center",
        autoClose: 1500,
        onClose: () => setAllow(true),
      });
      setShowErrorMessage(false);
    } else {
      toast.error("Invalid credentials! Please try again.", {
        position: "top-center",
      });
      setShowErrorMessage(true);
    }
  };

  return (
    <>
      <ToastContainer />
      <LoginNavbar />
      <div className="d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4 shadow-lg border-0" style={{ width: "400px" }}>
          <h3 className="text-center text-primary fw-bold">Login</h3>
          <hr />
          {showErrorMessage && (
            <div className="alert alert-danger text-center">
              Invalid credentials! Please try again.
            </div>
          )}
          <form>
            <div className="mb-3">
              <label htmlFor="username" className="form-label fw-semibold">
                <i className="bi bi-person-fill me-2"></i> Username
              </label>
              <input
                type="text"
                className="form-control"
                id="username"
                placeholder="Enter your username"
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
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>

            <button type="submit" className="btn btn-primary w-100" onClick={submit}>
              <i className="bi bi-box-arrow-in-right me-2"></i> Login
            </button>
          </form>
          <div className="text-center mt-3">
            <p className="text-muted">
              Don't have an account?{" "}
              <Link to="/register" className="text-decoration-none fw-bold text-primary">
                Register
              </Link>
            </p>
          </div>
        </div>
      </div>
    </>
  );
}

export default LoginPage;
