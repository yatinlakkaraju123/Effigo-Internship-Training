import { useEffect } from "react";
import { useAuth } from "./Auth";
import LoginNavbar from "../Navbars/LoginNavbar";
import { Link } from "react-router-dom";
import "./Logout.css"
function LogoutPage() {
  const auth = useAuth();

  useEffect(() => {
    auth.logout();
  }, []);

  return (
    <div className="logout-page">
      <LoginNavbar />
      <div className="container d-flex justify-content-center align-items-center" style={{ height: "80vh" }}>
        <div className="card shadow-lg p-4 text-center logout-card">
          <i className="bi bi-check-circle text-success" style={{ fontSize: "4rem" }}></i>
          <h2 className="mt-3 text-dark">You have been logged out successfully.</h2>
          <p className="text-muted">Thank you for using Expense Tracker. We hope to see you again soon!</p>
          <Link to="/login" className="btn btn-primary mt-3">
            <i className="bi bi-box-arrow-in-right me-2"></i> Return to Login
          </Link>
        </div>
      </div>
    </div>
  );
}

export default LogoutPage;

