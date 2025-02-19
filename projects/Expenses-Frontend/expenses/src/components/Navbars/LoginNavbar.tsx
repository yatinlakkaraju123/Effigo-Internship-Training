import { Link } from "react-router-dom";
import { useAuth } from "../security/Auth";
import "./UserStyle.css";

function LoginNavbar() {
  const auth = useAuth();
  const isAuthenticated = auth.isAuthenticated;

  return (
    <header className="border-bottom shadow-sm bg-white">
      <div className="container">
        <nav className="navbar navbar-expand-lg navbar-light">
          <Link className="navbar-brand fs-3 fw-bold text-primary" to="/">
            <i className="bi bi-wallet2 me-2"></i> Expense Tracker
          </Link>

          {/* Toggle Button for Mobile View */}
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto">
              {/* Show Home Link only if logged in */}
              {isAuthenticated && (
                <li className="nav-item">
                  <Link className="nav-link fs-5 text-dark" to="/">
                    <i className="bi bi-house-door me-1"></i> Home
                  </Link>
                </li>
              )}
            </ul>

            {/* Authentication Buttons */}
            <ul className="navbar-nav ms-auto">
              {!isAuthenticated ? (
                <li className="nav-item">
                  <Link className="nav-link fs-5 btn btn-primary px-3 text-white" to="/login">
                    <i className="bi bi-box-arrow-in-right me-1"></i> Login
                  </Link>
                </li>
              ) : (
                <li className="nav-item ms-3">
                  <Link className="nav-link fs-5 btn btn-danger px-3 text-white" to="/logout">
                    <i className="bi bi-box-arrow-right me-1"></i> Logout
                  </Link>
                </li>
              )}
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
}

export default LoginNavbar;

